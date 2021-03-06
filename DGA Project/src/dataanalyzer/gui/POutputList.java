package dataanalyzer.gui;

import dataanalyzer.manager.GUIManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author  Petah
 */
public class POutputList extends javax.swing.JPanel {

    private GUIManager gui;
    private FMain fmain;
    private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
    private Component parent;

    /** Creates new form POutputList */
    public POutputList(GUIManager gui, Component parent, ActionListener actionListener) {
        this.gui = gui;
        this.parent = parent;
        initComponents();
        addActionListener(actionListener);
    }

    private class OutputListModel extends AbstractListModel {

        public Object getElementAt(int index) {
            return gui.system.outputManager.get(index);
        }

        public int getSize() {
            return gui.system.outputManager.size();
        }
    }

    public void addActionListener(ActionListener actionListener) {
        actionListeners.add(actionListener);
    }

//    public static POutputList createIFrame(GUIManager gui) {
//        POutputList outputList = new POutputList(gui, null);
//        gui.fmain.addIFrame("Add Output", outputList, -1, -1);
//        return outputList;
//    }
    public static POutputList createDialog(GUIManager gui, ActionListener actionListener) {
        JDialog dialog = new JDialog(gui.fmain, "Select Output", true);
        POutputList outputList = new POutputList(gui, dialog, actionListener);
        dialog.setContentPane(outputList);
        dialog.pack();
        //Get the screen size  
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        //Set the new frame location
        dialog.setLocation(screenSize.width / 2 - dialog.getWidth() / 2, screenSize.height / 2 - dialog.getHeight() / 2);
        dialog.setVisible(true);
        return outputList;
    }

    public void close() {
        if (parent instanceof JDialog) {
            ((JDialog) parent).dispose();
        } else {
            gui.fmain.closeIFrame(this);
        }
    }

    public void sendOutput() {
        for (ActionListener actionListener : actionListeners) {
            actionListener.actionPerformed(new ActionEvent(lOutput.getSelectedValue(), lOutput.getSelectedIndex(), "Selected output"));
        }
        close();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lOutput = new javax.swing.JList();
        bOk = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();

        lOutput.setModel(new OutputListModel());
        lOutput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lOutputMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(lOutput);

        bOk.setText("Ok");
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });

        bCancel.setText("Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCancel)
                .addContainerGap(54, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bOk)
                    .addComponent(bCancel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void lOutputMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lOutputMousePressed
    if (evt.getClickCount() == 2) {
        sendOutput();
    }
}//GEN-LAST:event_lOutputMousePressed

private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
    if (lOutput.getSelectedValue() == null) {
        JOptionPane.showMessageDialog(this, "No output selected.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
    } else {
        sendOutput();
    }
}//GEN-LAST:event_bOkActionPerformed

private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
    close();
}//GEN-LAST:event_bCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lOutput;
    // End of variables declaration//GEN-END:variables
}
