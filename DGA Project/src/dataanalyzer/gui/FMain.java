package dataanalyzer.gui;

import dataanalyzer.DGA;
import dataanalyzer.data.DSettings;
import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Node;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Setting;
import dataanalyzer.gui.PAlgorithm;
import dataanalyzer.listeners.ManagerUpdateListener;
import dataanalyzer.manager.GUIManager;
import dataanalyzer.manager.Manager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
// TODO: Add right click menus for tree
// TODO: Add load plugin to file menu
// TODO: Document internal classes

/**
 * This is the main JFrame used as the base for the GUI user interface.
 * @author David Neilsen
 */
public class FMain extends javax.swing.JFrame implements ManagerUpdateListener {

    /** Main GUI and system referance object */
    private GUIManager gui;
    private PConsole pConsole;
    private PResourceMonitor pResourceMonitor;
    private PServer pServer;
    private PClient pClient;
    private PSettings pSettings;
    private PUpdate pUpdate;
    private JInternalFrame ifConsole;
    private JInternalFrame ifServer;
    private JInternalFrame ifClient;
    private JInternalFrame ifResourceMonitor;
    private JInternalFrame ifSettings;
    private JInternalFrame ifUpdate;
    /** Handler for drag a drop actions from the tree */
    private TreeTransferHandler treeTransferHandler;
    /** Stores the Object that has been right clicked in the tree */
    private Object treeRightClick;
    /** X offset to cascade new internal frames */
    private int xOffset = 0;
    /** Y offset to cascade new internal frames */
    private int yOffset = 0;

    /**
     * Creates and initializes the frame. Sets the frame position, sizze and state
     * based on settings save from the last time the application was run.
     * @param gui the main GUI and system referance
     */
    public FMain(final GUIManager gui) {
        this.gui = gui;
        initComponents();
        //Position
        Setting<Point> pos = gui.system.settingsManager.find("windowPosition");
        if (pos != null) {
            setLocation(pos.value.x, pos.value.y);
        }
        //Resize
        Setting<Point> size = gui.system.settingsManager.find("windowSize");
        if (size != null) {
            setSize(size.value.x, size.value.y);
        }
        //Set state
        Setting<Integer> state = gui.system.settingsManager.find("windowState");
        if (state != null) {
            setExtendedState(state.value);
        }
        //Add internal frames
        openDefaultFrames();
        // Add window listenera to adjust and save settings 
        addListeners();
        //Expand all tree nodes
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }

        treeTransferHandler = new TreeTransferHandler();
        tree.setDragEnabled(true);
        tree.setTransferHandler(treeTransferHandler);
        //desktopPane.setTransferHandler(treeTransferHandler);

        //Set the window title
        this.setTitle("DGA");
    }

    /**
     * Sets up listeners to:
     *      - Save setting on exit
     *      - Change setting when the frame is moved, resized or the state has changed
     */
    public void addListeners() {
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                DSettings.save(gui.system, new File("settings.xml"));

            }
        });
        this.addWindowStateListener(new WindowAdapter() {

            @Override
            public void windowStateChanged(WindowEvent e) {
                gui.system.settingsManager.add(new Setting<Integer>("windowState", e.getNewState(), "Integer"));
            }
        });
        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentMoved(ComponentEvent e) {
                gui.system.settingsManager.add(new Setting<Point>("windowPosition", new Point(getX(), getY()), "Point"));
            }

            @Override
            public void componentResized(ComponentEvent evt) {
                gui.system.settingsManager.add(new Setting<Point>("windowSize", new Point(getWidth(), getHeight()), "Point"));
            }
        });
    }

    /**
     * Calls the refresh tree action when a manager has been updated.
     * @param m the manager that was updated (not used)
     */
    public void update(Manager m) {
        bRefreshActionPerformed(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Transfer Handler Code">
    /**
     * This class handles drag and drop of objects from the tree.
     */
    private class TreeTransferHandler extends TransferHandler {

        Object draggedObject;

        @Override
        public void exportAsDrag(JComponent comp, InputEvent e, int action) {
//            gui.system.userInterface.out("Export");
            TreePath selPath = tree.getSelectionPath();
            draggedObject = selPath.getLastPathComponent();
            super.exportAsDrag(comp, e, action);
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport info) {
            return true;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new StringSelection(draggedObject.toString());
        }

        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY_OR_MOVE;
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport info) {
//            gui.system.userInterface.out("Import " + draggedObject);
            if (draggedObject instanceof Output) {
                if (info.getComponent() instanceof JInternalFrame) {
                    JInternalFrame frame = (JInternalFrame) info.getComponent();
                    if (frame.getContentPane() instanceof POutput) {
                        POutput pGraph = (POutput) frame.getContentPane();
                        pGraph.addOutput((Output) draggedObject);
                    }
                }
                if (info.getComponent() instanceof JTextPane) {
                    JTextPane textPane = (JTextPane) info.getComponent();
                    Container container = textPane.getParent();
                    while (true) {
                        if (container == null) {
                            break;
                        }
                        if (container instanceof POutput) {
                            POutput pGraph = (POutput) container;
                            pGraph.addOutput((Output) draggedObject);
                        }
                        container = container.getParent();
                    }
                }
            }
            return true;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            draggedObject = null;
        }
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Internal Frame Management Code">
    /**
     * Opens the default frames when the program is started if they where open
     * when the program last closed (according to settings).
     */
    public void openDefaultFrames() {
        Setting<Boolean> s;
        s = gui.system.settingsManager.find("frameOpen:Console");
        if (s != null && s.value) {
            iConsoleActionPerformed(null);
        }
        s = gui.system.settingsManager.find("frameOpen:Resource Monitor");
        if (s != null && s.value) {
            iResourceMonitorActionPerformed(null);
        }
        s = gui.system.settingsManager.find("frameOpen:Server");
        if (s != null && s.value) {
            iServerActionPerformed(null);
        }
        s = gui.system.settingsManager.find("frameOpen:Client");
        if (s != null && s.value) {
            iClientActionPerformed(null);
        }
        s = gui.system.settingsManager.find("frameOpen:Settings");
        if (s != null && s.value) {
            iSettingsActionPerformed(null);
        }
    }

    /**
     * Create a new JInternalFrame and adds it to the frames desktop area.
     * @param title the title of the JInternalFrame
     * @param panel the the JPanel to place on the JInternalFrame
     * @param x the x position of the JInternalFrame (or -1 for default placment)
     * @param y the y position of the JInternalFrame (or -1 for default placment)
     * @return the JInternalFrame that was created
     */
    public JInternalFrame addIFrame(String title, JPanel panel, int x, int y) {
        return addIFrame(title, panel, x, y, -1, -1);
    }

    /**
     * Create a new JInternalFrame and adds it to the frames desktop area.
     * @param title the title of the JInternalFrame
     * @param panel the the JPanel to place on the JInternalFrame
     * @param x the x position of the JInternalFrame (or -1 for default placment)
     * @param y the y position of the JInternalFrame (or -1 for default placment)
     * @param width if there is no setting for the JInternalFrame size this parameter
     * is used. If this parameter is -1 as well then a called to made to the pack method.
     * @param height if there is no setting for the JInternalFrame size this parameter
     * is used. If this parameter is -1 as well then a called to made to the pack method.
     * @return the JInternalFrame that was created
     */
    public JInternalFrame addIFrame(String title, JPanel panel, int x, int y, int width, int height) {
        //Create the frame
        final JInternalFrame frame = new JInternalFrame(title, true, true, true, true);

        //Automaticly position frame
        if (x == -1 && y == -1) {
            //Check for saved position
            Setting<Point> pos = gui.system.settingsManager.find("framePosition:" + frame.getTitle());
            if (pos != null) {
                x = pos.value.x;
                y = pos.value.y;
            } else {
                //Default location (cascade)
                x = xOffset;
                xOffset += 25;
                y = yOffset;
                yOffset += 25;
            }
        }
        //Make sure frames arnt overlapping
        // FIXME: Make sure internal frames dont overlap
//        while (desktopPane.findComponentAt(x, y) != null) {
//            x += 25;
//            y += 25;
//        }
        frame.setLocation(x, y);
        //Add panel to frame
        frame.setContentPane(panel);
        //Resize and make frame visible
        Setting<Point> size = gui.system.settingsManager.find("frameSize:" + frame.getTitle());
        if (size != null) {
            frame.setSize(size.value.x, size.value.y);
        } else if (width != -1 && height != -1) {
            frame.setSize(width, height);
        } else {
            frame.pack();
        }
        frame.setVisible(true);

        //Add frame to desktop
        desktopPane.getDesktopManager().activateFrame(frame);
        desktopPane.add(frame);

        //Add toolbar button
        final JButton b = new JButton(title);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                bringFrameToTop(frame);
            }
        });
        toolBar.add(b);
        //Add listeners to frame
        frame.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentMoved(ComponentEvent e) {
                gui.system.settingsManager.add(new Setting<Point>("framePosition:" + frame.getTitle(), new Point(frame.getX(), frame.getY()), "Point"));
            }

            @Override
            public void componentResized(ComponentEvent e) {
                gui.system.settingsManager.add(new Setting<Point>("frameSize:" + frame.getTitle(), new Point(frame.getWidth(), frame.getHeight()), "Point"));
            }
        });
        frame.addInternalFrameListener(new InternalFrameAdapter() {

            //Remove the button when the frame is closed
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                toolBar.remove(b);
                toolBar.repaint();
            }
        });

        //Add menu bar if required
        if (panel instanceof IInternalFrameMenu) {
            frame.setJMenuBar(((IInternalFrameMenu) panel).createMenuBar());
        }

        //Put frame ontop
        bringFrameToTop(frame);

        return frame;
    }

    /**
     * Removes an internal frame based on what JPanel it contains.
     * @param panel the JPanel to match to the JInternalFrame's content pane
     */
    public void closeIFrame(JPanel panel) {
        for (JInternalFrame internalFrame : desktopPane.getAllFrames()) {
            if (panel == internalFrame.getContentPane()) {
                try {
                    internalFrame.setClosed(true);
                } catch (PropertyVetoException ex) {
                    gui.system.userInterface.handleException(ex);
                }
            }
        }
    }

    /**
     * Cascades all JInternalFrame on the frames desktop.
     */
    public void cascade() {
        int x = 0;
        int y = 0;
        for (JInternalFrame iFrame : desktopPane.getAllFrames()) {
            iFrame.setLocation(x, y);
            bringFrameToTop(iFrame);
            x += 25;
            y += 25;
        }
    }

    /**
     * Brings a JInternalFrame to the top.
     * @param frame the JInternalFrame to bring to the top
     */
    private void bringFrameToTop(JInternalFrame frame) {
        desktopPane.getDesktopManager().activateFrame(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            gui.system.userInterface.handleError(ex.getMessage());
        }
    }

    /**
     * Creates a JInternalFrame for an Output instance.
     * @param out the Output to display
     */
    public void addOutputFrame(Output out) {
        POutput pGraph = new POutput(gui, out);
        JInternalFrame frame = addIFrame(out.toString(), pGraph, -1, -1, 600, 400);
        frame.setTransferHandler(treeTransferHandler);
        pGraph.setTransferHandler(treeTransferHandler);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Tree Code">
    /**
     * This classes implements a TreeModel to display all Managers the DGA class
     * contains.
     */
    private class SystemTreeModel implements TreeModel {

        private Vector<TreeModelListener> treeModelListeners = new Vector<TreeModelListener>();

        public void addTreeModelListener(TreeModelListener l) {
            treeModelListeners.addElement(l);
        }

        public Object getChild(Object parent, int index) {
            if (parent instanceof DGA) {
                return gui.system.get().get(index);
            } else if (parent instanceof Manager) {
                Manager manager = (Manager) parent;
                Object container = manager.get();
                if (container instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) container;
                    return arrayList.get(index);
                }
            }
            if (true) {
                throw new UnsupportedOperationException("Child is null.");
            }
            return null;
        }

        public int getChildCount(Object parent) {
            if (parent instanceof DGA) {
                return gui.system.get().size();
            } else if (parent instanceof Manager) {
                Manager manager = (Manager) parent;
                Object container = manager.get();
                if (container instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) container;
                    return arrayList.size();
                }
            }
            return 0;
        }

        public int getIndexOfChild(Object parent, Object child) {
            if (parent instanceof DGA) {
                return gui.system.get().indexOf(child);
            } else if (parent instanceof Manager) {
                Manager manager = (Manager) parent;
                Object container = manager.get();
                if (container instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) container;
                    return arrayList.indexOf(child);
                }
            }
            return 0;
        }

        public Object getRoot() {
            if (gui.system == null) {
                throw new UnsupportedOperationException("System is null.");
            }
            return gui.system;
        }

        public boolean isLeaf(Object node) {
            if (node instanceof DGA) {
                return false;
            } else if (node instanceof Manager) {
                return false;
            }
            return true;
        }

        public void removeTreeModelListener(TreeModelListener l) {
            treeModelListeners.removeElement(l);
        }

        public void valueForPathChanged(TreePath path, Object newValue) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    // </editor-fold> 

    public PConsole getConsole() {
        return pConsole;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmOutput = new javax.swing.JPopupMenu();
        iRemoveOutput = new javax.swing.JMenuItem();
        splitPane = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        toolBar = new javax.swing.JToolBar();
        rightPanel = new javax.swing.JPanel();
        treeScrollPane = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        bRefresh = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        bConsole = new javax.swing.JButton();
        bResourceMonitor = new javax.swing.JButton();
        bServer = new javax.swing.JButton();
        bClient = new javax.swing.JButton();
        bSettings = new javax.swing.JButton();
        bUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        iExit = new javax.swing.JMenuItem();
        mEdit = new javax.swing.JMenu();
        iSettings = new javax.swing.JMenuItem();
        mWindow = new javax.swing.JMenu();
        iServer = new javax.swing.JMenuItem();
        iClient = new javax.swing.JMenuItem();
        iConsole = new javax.swing.JMenuItem();
        iResourceMonitor = new javax.swing.JMenuItem();
        iUpdate = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        iCascade = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        iHelp = new javax.swing.JMenuItem();
        iAbout = new javax.swing.JMenuItem();

        iRemoveOutput.setText("Remove");
        iRemoveOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iRemoveOutputActionPerformed(evt);
            }
        });
        pmOutput.add(iRemoveOutput);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        splitPane.setDividerLocation(150);

        desktopPane.setBackground(new java.awt.Color(204, 204, 204));

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
        );

        splitPane.setRightComponent(leftPanel);

        tree.setModel(new SystemTreeModel());
        tree.setToolTipText("System Objects");
        tree.setRootVisible(false);
        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                treeMousePressed(evt);
            }
        });
        treeScrollPane.setViewportView(tree);

        bRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view-refresh.png"))); // NOI18N
        bRefresh.setText("Refresh");
        bRefresh.setToolTipText("Refresh List");
        bRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bRefresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
            .addComponent(treeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(bRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(treeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
        );

        splitPane.setLeftComponent(rightPanel);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        bConsole.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/utilities-terminal.png"))); // NOI18N
        bConsole.setText("Console");
        bConsole.setToolTipText("System Console");
        bConsole.setFocusable(false);
        bConsole.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bConsole.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsoleActionPerformed(evt);
            }
        });
        jToolBar1.add(bConsole);

        bResourceMonitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/utilities-system-monitor.png"))); // NOI18N
        bResourceMonitor.setText("Resources");
        bResourceMonitor.setToolTipText("Resource Monitor");
        bResourceMonitor.setFocusable(false);
        bResourceMonitor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bResourceMonitor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bResourceMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResourceMonitorActionPerformed(evt);
            }
        });
        jToolBar1.add(bResourceMonitor);

        bServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/network-server.png"))); // NOI18N
        bServer.setText("Server");
        bServer.setToolTipText("Server GUI");
        bServer.setFocusable(false);
        bServer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bServer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bServerActionPerformed(evt);
            }
        });
        jToolBar1.add(bServer);

        bClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/network-workgroup.png"))); // NOI18N
        bClient.setText("Client");
        bClient.setToolTipText("Client GUI");
        bClient.setFocusable(false);
        bClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClientActionPerformed(evt);
            }
        });
        jToolBar1.add(bClient);

        bSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/preferences-system-session.png"))); // NOI18N
        bSettings.setText("Settings");
        bSettings.setToolTipText("Settings");
        bSettings.setFocusable(false);
        bSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSettingsActionPerformed(evt);
            }
        });
        jToolBar1.add(bSettings);

        bUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/software-update-available.png"))); // NOI18N
        bUpdate.setText("Update");
        bUpdate.setToolTipText("Search for Updates");
        bUpdate.setFocusable(false);
        bUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });
        jToolBar1.add(bUpdate);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help-browser.png"))); // NOI18N
        jButton1.setText("Help");
        jButton1.setToolTipText("About DGA");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        mFile.setText("File");

        jMenuItem2.setText("Load Plugin");
        mFile.add(jMenuItem2);
        mFile.add(jSeparator1);

        iExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        iExit.setText("Exit");
        iExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iExitActionPerformed(evt);
            }
        });
        mFile.add(iExit);

        menuBar.add(mFile);

        mEdit.setText("Edit");

        iSettings.setText("Settings");
        iSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iSettingsActionPerformed(evt);
            }
        });
        mEdit.add(iSettings);

        menuBar.add(mEdit);

        mWindow.setText("Window");

        iServer.setText("Server");
        iServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iServerActionPerformed(evt);
            }
        });
        mWindow.add(iServer);

        iClient.setText("Client");
        iClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iClientActionPerformed(evt);
            }
        });
        mWindow.add(iClient);

        iConsole.setText("Console");
        iConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iConsoleActionPerformed(evt);
            }
        });
        mWindow.add(iConsole);

        iResourceMonitor.setText("Resource Monitor");
        iResourceMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iResourceMonitorActionPerformed(evt);
            }
        });
        mWindow.add(iResourceMonitor);

        iUpdate.setText("Update");
        iUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iUpdateActionPerformed(evt);
            }
        });
        mWindow.add(iUpdate);
        mWindow.add(jSeparator2);

        iCascade.setText("Cascade");
        iCascade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iCascadeActionPerformed(evt);
            }
        });
        mWindow.add(iCascade);

        menuBar.add(mWindow);

        mHelp.setText("Help");

        iHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        iHelp.setText("Help");
        mHelp.add(iHelp);

        iAbout.setText("About");
        iAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iAboutActionPerformed(evt);
            }
        });
        mHelp.add(iAbout);

        menuBar.add(mHelp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Event Code">
    /**
     * If an item in the tree is double clicked then create and display the related
     * internal frame. If an item is right click display the related popup menu.
     * @param evt the MouseEvent from the mouse press
     */
private void treeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMousePressed
    int selRow = tree.getRowForLocation(evt.getX(), evt.getY());
    TreePath selPath = tree.getPathForLocation(evt.getX(), evt.getY());
    if (selRow != -1) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() == 2) {
                Object o = selPath.getLastPathComponent();
                if (o instanceof Algorithm) {
                    Algorithm a = (Algorithm) o;
                    addIFrame(a.getName(), new PAlgorithm(gui, a), -1, -1);
                } else if (o instanceof Output) {
                    Output out = (Output) o;
                    addOutputFrame(out);
                } else if (o instanceof Node) {
                    Node n = (Node) o;
                    addIFrame(n.toString(), new PNode(gui, n), -1, -1);
                } else if (o instanceof AlgorithmProcess) {
                    AlgorithmProcess p = (AlgorithmProcess) o;
                    PProgress progressPanel = new PProgress(gui, p);
                    p.addProgressListener(progressPanel);
                    p.addCompletionListener(progressPanel);
                    addIFrame(p + " progress", progressPanel, -1, -1);
                }
            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            Object o = selPath.getLastPathComponent();
            treeRightClick = o;
            // TODO: Add popup menus for plugin and algorithm
            if (o instanceof Algorithm) {
            } else if (o instanceof Output) {
                pmOutput.show((Component) evt.getSource(), evt.getX(), evt.getY());
            } else if (o instanceof Node) {
            } else if (o instanceof AlgorithmProcess) {
            }
        }
    }
}//GEN-LAST:event_treeMousePressed

    /**
     * Refresh tree event.
     * @param evt the ActionEvent from the button click
     */
private void bRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRefreshActionPerformed
    //Create a thread to execute invokeAndWait in order to not cause errors when updating the tree UI
    new Thread() {

        @Override
        public void run() {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {

                    public void run() {
                        SwingUtilities.updateComponentTreeUI(tree);
                    }
                });
            } catch (InterruptedException ex) {
                gui.system.userInterface.handleError(ex.getMessage());
            } catch (InvocationTargetException ex) {
                gui.system.userInterface.handleError(ex.getMessage());
            }
        }
    }.start();
}//GEN-LAST:event_bRefreshActionPerformed

    /**
     * Close the program.
     * @param evt menu item click ActionEvent
     */
private void iExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iExitActionPerformed
    System.exit(0);
}//GEN-LAST:event_iExitActionPerformed

    /**
     * Shows the client internal frame.
     * @param evt triggering ActionEvent
     */
private void iClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iClientActionPerformed
    if (ifClient == null) {
        gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Client", true, "Boolean"));
        ifClient = addIFrame("Client", pClient = new PClient(gui.system), -1, -1);
        ifClient.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Client", false, "Boolean"));
                pClient = null;
                ifClient = null;
            }
        });
    } else {
        bringFrameToTop(ifClient);
    }
}//GEN-LAST:event_iClientActionPerformed

    /**
     * Shows the console internal frame.
     * @param evt triggering ActionEvent
     */
private void iConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iConsoleActionPerformed
    if (ifConsole == null) {
        gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Console", true, "Boolean"));
        ifConsole = addIFrame("Console", pConsole = new PConsole(gui.system), -1, -1);
        ifConsole.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Console", false, "Boolean"));
                pConsole = null;
                ifConsole = null;
            }
        });
        gui.system.userInterface.out(null);
    } else {
        bringFrameToTop(ifConsole);
    }
}//GEN-LAST:event_iConsoleActionPerformed

    /**
     * Shows the resource monitior internal frame.
     * @param evt triggering ActionEvent
     */
private void iResourceMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iResourceMonitorActionPerformed
    if (ifResourceMonitor == null) {
        gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Resource Monitor", true, "Boolean"));
        ifResourceMonitor = addIFrame("Resource Monitor", pResourceMonitor = new PResourceMonitor(gui), -1, -1);
        ifResourceMonitor.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Resource Monitor", false, "Boolean"));
                pResourceMonitor = null;
                ifResourceMonitor = null;
            }
        });
    } else {
        bringFrameToTop(ifResourceMonitor);
    }
}//GEN-LAST:event_iResourceMonitorActionPerformed

    /**
     * Calls the cascade method.
     * @param evt triggering ActionEvent
     * @see FMain#cascade()
     */
private void iCascadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iCascadeActionPerformed
    cascade();
}//GEN-LAST:event_iCascadeActionPerformed

    /**
     * Shows the server internal frame.
     * @param evt triggering ActionEvent
     */
private void iServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iServerActionPerformed
    if (ifServer == null) {
        gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Server", true, "Boolean"));
        ifServer = addIFrame("Server", pServer = new PServer(gui), -1, -1);
        ifServer.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Server", false, "Boolean"));
                pServer = null;
                ifServer = null;
            }
        });
    } else {
        bringFrameToTop(ifServer);
    }
}//GEN-LAST:event_iServerActionPerformed

private void bClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClientActionPerformed
    iClientActionPerformed(evt);
}//GEN-LAST:event_bClientActionPerformed

private void bConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsoleActionPerformed
    iConsoleActionPerformed(evt);
}//GEN-LAST:event_bConsoleActionPerformed

private void bResourceMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResourceMonitorActionPerformed
    iResourceMonitorActionPerformed(evt);
}//GEN-LAST:event_bResourceMonitorActionPerformed

private void bServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bServerActionPerformed
    iServerActionPerformed(evt);
}//GEN-LAST:event_bServerActionPerformed

    /**
     * Shows the settings internal frame.
     * @param evt triggering ActionEvent
     */
private void iSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iSettingsActionPerformed
    if (ifSettings == null) {
        gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Settings", true, "Boolean"));
        ifSettings = addIFrame("Settings", pSettings = new PSettings(gui), -1, -1);
        gui.system.settingsManager.addUpdateListener(pSettings);
        ifSettings.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Settings", false, "Boolean"));
                gui.system.settingsManager.removeUpdateListener(pSettings);
                pSettings = null;
                ifSettings = null;
            }
        });
    } else {
        bringFrameToTop(ifSettings);
    }

}//GEN-LAST:event_iSettingsActionPerformed

private void bSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSettingsActionPerformed
    iSettingsActionPerformed(evt);
}//GEN-LAST:event_bSettingsActionPerformed

    /**
     * Shows the update internal frame.
     * @param evt triggering ActionEvent
     */
private void iUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iUpdateActionPerformed
    if (ifUpdate == null) {
        gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Update", true, "Boolean"));
        ifUpdate = addIFrame("Update", pUpdate = new PUpdate(gui), -1, -1);
        ifUpdate.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                gui.system.settingsManager.add(new Setting<Boolean>("frameOpen:Update", false, "Boolean"));
                pUpdate = null;
                ifUpdate = null;
            }
        });
    } else {
        bringFrameToTop(ifUpdate);
    }
}//GEN-LAST:event_iUpdateActionPerformed

private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
    iUpdateActionPerformed(evt);
}//GEN-LAST:event_bUpdateActionPerformed

    /**
     * Removes an Output when requested by the output popup menu.
     * @param evt triggering ActionEvent
     */
private void iRemoveOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iRemoveOutputActionPerformed
    if (treeRightClick instanceof Output) {
        gui.system.outputManager.remove((Output) treeRightClick);
        // TODO: Close any output windows here
    }
}//GEN-LAST:event_iRemoveOutputActionPerformed

private void iAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iAboutActionPerformed
    PAbout.createDialog(gui);
}//GEN-LAST:event_iAboutActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    PAbout.createDialog(gui);
}//GEN-LAST:event_jButton1ActionPerformed
    // </editor-fold> 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClient;
    private javax.swing.JButton bConsole;
    private javax.swing.JButton bRefresh;
    private javax.swing.JButton bResourceMonitor;
    private javax.swing.JButton bServer;
    private javax.swing.JButton bSettings;
    private javax.swing.JButton bUpdate;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem iAbout;
    private javax.swing.JMenuItem iCascade;
    private javax.swing.JMenuItem iClient;
    private javax.swing.JMenuItem iConsole;
    private javax.swing.JMenuItem iExit;
    private javax.swing.JMenuItem iHelp;
    private javax.swing.JMenuItem iRemoveOutput;
    private javax.swing.JMenuItem iResourceMonitor;
    private javax.swing.JMenuItem iServer;
    private javax.swing.JMenuItem iSettings;
    private javax.swing.JMenuItem iUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenu mEdit;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mHelp;
    private javax.swing.JMenu mWindow;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPopupMenu pmOutput;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JTree tree;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
}
