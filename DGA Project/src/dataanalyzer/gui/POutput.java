/*
 * PGraph.java
 *
 * Created on 22 March 2009, 22:27
 */
package dataanalyzer.gui;

import dataanalyzer.UserInterface;
import dataanalyzer.chart.CArea;
import dataanalyzer.chart.CBar;
import dataanalyzer.chart.CLine;
import dataanalyzer.chart.CPie;
import dataanalyzer.chart.IChart;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.OutputCollection;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import dataanalyzer.manager.GUIManager;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.TransferHandler;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author  Petah
 */
public class POutput extends javax.swing.JPanel implements IInternalFrameMenu {

    private GUIManager gui;
    private Output output;
    private VisulisationPanel visulisationPanel;
    private IChart chart;
    private Thread chartLoadThread;
    private int step = 1;
    private int scale = 30;

    /** Creates new form PGraph */
    public POutput(GUIManager gui, Output output) {
        this.gui = gui;
        this.output = output;
        initComponents();
        visulisationPanel = new VisulisationPanel();
        iBarActionPerformed(null);
        pGraph.setLayout(new BorderLayout());
        pGraph.add(visulisationPanel, BorderLayout.CENTER);
        scaleChanged(output);
    }

    @Override
    public void setTransferHandler(TransferHandler newHandler) {
        visulisationPanel.setTransferHandler(newHandler);
    }

    public void addOutput(Output o) {
        if (output.getOutput() instanceof OutputCollection) {
            OutputCollection outputCollection = (OutputCollection) output.getOutput();
            outputCollection.add(o);
        } else {
            OutputCollection outputCollection = new OutputCollection();
            outputCollection.add(output);
            outputCollection.add(o);

            output = new Output(outputCollection, "Collection");
        }
        visulisationPanel.update();
    }

    // <editor-fold defaultstate="collapsed" desc="Menu Bar Code">  
    public JMenuBar createMenuBar() {

        JMenuBar menuBar = new javax.swing.JMenuBar();
        JMenu mData = new javax.swing.JMenu();
        JMenu mView = new javax.swing.JMenu();
        JMenuItem iAdd = new javax.swing.JMenuItem();
        JMenuItem iBar = new javax.swing.JMenuItem();
        JMenuItem iLine = new javax.swing.JMenuItem();
        JMenuItem iArea = new javax.swing.JMenuItem();
        JMenuItem iPie = new javax.swing.JMenuItem();
        JMenuItem iData = new javax.swing.JMenuItem();
        JMenuItem iScale = new javax.swing.JMenuItem();
        JMenuItem iInformation = new javax.swing.JMenuItem();
        JMenuItem iStep = new javax.swing.JMenuItem();

        mData.setText("Data");


        iAdd.setText("Add Output");
        iAdd.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iAddActionPerformed(evt);
            }
        });
        mData.add(iAdd);

        iScale.setText("Set Scale");
        iScale.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iScaleActionPerformed(evt);
            }
        });
        mData.add(iScale);

        iStep.setText("Set Step");
        iStep.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iStepActionPerformed(evt);
            }
        });
        mData.add(iStep);

        menuBar.add(mData);

        mView.setText("View");

        iBar.setText("Bar Chart");
        iBar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iBarActionPerformed(evt);
            }
        });
        mView.add(iBar);

        iLine.setText("Line Chart");
        iLine.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iLineActionPerformed(evt);
            }
        });
        mView.add(iLine);

        iArea.setText("Area Chart");
        iArea.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iAreaActionPerformed(evt);
            }
        });
        mView.add(iArea);

        iPie.setText("Pie Chart");
        iPie.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iPieActionPerformed(evt);
            }
        });
        mView.add(iPie);

        iData.setText("Data List");
        iData.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iDataActionPerformed(evt);
            }
        });
        mView.add(iData);

        iInformation.setText("Information");
        iInformation.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iInformationActionPerformed(evt);
            }
        });
        mView.add(iInformation);

        menuBar.add(mView);
        return menuBar;
    }

    private void iAddActionPerformed(java.awt.event.ActionEvent evt) {
        POutputList.createDialog(gui, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof Output) {
                    addOutput((Output) e.getSource());
                }
            }
        });
    }

    private void iBarActionPerformed(java.awt.event.ActionEvent evt) {
        chart = new CBar();
        createChart();
    }

    private void iLineActionPerformed(java.awt.event.ActionEvent evt) {
        chart = new CLine();
        createChart();
    }

    private void iAreaActionPerformed(java.awt.event.ActionEvent evt) {
        chart = new CArea();
        createChart();
    }

    private void iPieActionPerformed(java.awt.event.ActionEvent evt) {
        chart = new CPie();
        createChart();
    }

    private void iDataActionPerformed(java.awt.event.ActionEvent evt) {
        visulisationPanel.setText(output + "\n" + output.getOutput());
    }

    private void iScaleActionPerformed(java.awt.event.ActionEvent evt) {
        scale = 0;
        while (scale < 1) {
            String result = JOptionPane.showInputDialog(this, "Input new scale value (number > 0)");
            try {
                scale = Integer.parseInt(result);
                if (scale < 1) {
                    JOptionPane.showMessageDialog(this, "Input was not valid (must be > 0)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException numberFormatException) {
                scale = 0;
                JOptionPane.showMessageDialog(this, "Input was not a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        scaleChanged(output);
    }

    private void iInformationActionPerformed(java.awt.event.ActionEvent evt) {
        visulisationPanel.setText(output.getInformation());
    }

    private void iStepActionPerformed(java.awt.event.ActionEvent evt) {
        step = 0;
        while (step < 1) {
            String result = JOptionPane.showInputDialog(this, "Input new step value (number > 0)");
            try {
                step = Integer.parseInt(result);
                if (step < 1) {
                    JOptionPane.showMessageDialog(this, "Input was not valid (must be > 0)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException numberFormatException) {
                step = 0;
                JOptionPane.showMessageDialog(this, "Input was not a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        createChart();
    }
    // </editor-fold>

    private void createChart() {
        if (chartLoadThread != null && chartLoadThread.isAlive()) {
            gui.system.userInterface.handleError("Chart is still loading.", UserInterface.ALL);
        } else {
            chartLoadThread = new Thread(new ChartLoadProcess(), "Load chart");
            chartLoadThread.start();
        }
    }

    private void scaleChanged(Output output) {
        if (output.getOutput() instanceof SequenceData) {
            SequenceData sequence = (SequenceData) output.getOutput();
            if (scale > sequence.size()) {
                scale = sequence.size();
            }
            scrollBar.setMaximum(sequence.size() - scale);
            scrollBar.setMinimum(0);
            scrollBar.setValue(0);
        } else if (output.getOutput() instanceof OutputCollection) {
            OutputCollection outputCollection = (OutputCollection) output.getOutput();
            for (Output o : outputCollection.getAll()) {
                scaleChanged(o);
            }
        }
    }

    private class ChartLoadProcess implements Runnable {

        JFreeChart jFreeChart;

        @Override
        public void run() {
            try {
                if (chart != null) {
                    chart.setOffset(scrollBar.getValue());
                    chart.setOutput(output);
                    chart.setScale(scale);
                    chart.setStep(step);
                    jFreeChart = chart.create();
                } else {
                    gui.system.userInterface.handleError("Chart is null");
                }
            } catch (InvalidDataTypeException ex) {
                visulisationPanel.setMessage("Invalid data for " + chart + " visulisation<br>(" + ex.getMessage() + ")");
            }

            if (jFreeChart != null) {
                visulisationPanel.setChart(jFreeChart);
            } else {
                visulisationPanel.appendMessage("Could not create chart.");
            }
        }
    }

    private class VisulisationPanel extends JPanel {

        private ChartPanel chartPanel;
        private JTextPane textPane;
        private JLabel label;
        private JScrollPane scrollPane;
        private String message;

        public VisulisationPanel() {
            chartPanel = new ChartPanel(null);
            textPane = new JTextPane();
            textPane.setEditable(false);
            scrollPane = new JScrollPane(textPane);
            label = new JLabel();
            label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
            setMessage("No visulisation loaded.");
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            c.weightx = c.weighty = 1;
            add(scrollPane, c);
            c.gridy++;
            add(chartPanel, c);
            c.gridy++;
            add(label, c);
        }

        @Override
        public void setTransferHandler(TransferHandler newHandler) {
            textPane.setTransferHandler(newHandler);
        }

        private void showComponent(JComponent c) {
            chartPanel.setVisible(false);
            scrollPane.setVisible(false);
            label.setVisible(false);
            c.setVisible(true);
        }

        public void setChart(JFreeChart chart) {
            chartPanel.setChart(chart);
            showComponent(chartPanel);
        }

        public void setText(Object o) {
            textPane.setText(o.toString());
            showComponent(scrollPane);
        }

        public void setMessage(Object o) {
            message = o.toString();
            label.setText("<html>" + message + "</html>");
            showComponent(label);
        }

        public void appendMessage(Object o) {
            setMessage(message + "<br>" + o.toString());
            showComponent(label);
        }

        public void update() {
            if (chartPanel.isVisible()) {
                createChart();
            }
            if (scrollPane.isVisible()) {
                iDataActionPerformed(null);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pContainer = new javax.swing.JPanel();
        scrollBar = new javax.swing.JScrollBar();
        pGraph = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(300, 200));

        scrollBar.setMaximum(0);
        scrollBar.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        scrollBar.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollBarAdjustmentValueChanged(evt);
            }
        });

        pGraph.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pGraphLayout = new javax.swing.GroupLayout(pGraph);
        pGraph.setLayout(pGraphLayout);
        pGraphLayout.setHorizontalGroup(
            pGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        pGraphLayout.setVerticalGroup(
            pGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pContainerLayout = new javax.swing.GroupLayout(pContainer);
        pContainer.setLayout(pContainerLayout);
        pContainerLayout.setHorizontalGroup(
            pContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollBar, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(pGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pContainerLayout.setVerticalGroup(
            pContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pContainerLayout.createSequentialGroup()
                .addComponent(pGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void scrollBarAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollBarAdjustmentValueChanged
    createChart();
}//GEN-LAST:event_scrollBarAdjustmentValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pContainer;
    private javax.swing.JPanel pGraph;
    private javax.swing.JScrollBar scrollBar;
    // End of variables declaration//GEN-END:variables
}
