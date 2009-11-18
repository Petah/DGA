/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PServer.java
 *
 * Created on 23/03/2009, 9:42:05 AM
 */
package dataanalyzer.gui;

import dataanalyzer.DGA;
import dataanalyzer.entity.Node;
import dataanalyzer.entity.Setting;
import dataanalyzer.listeners.ManagerUpdateListener;
import dataanalyzer.manager.GUIManager;
import dataanalyzer.manager.Manager;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Student
 */
public class PServer extends javax.swing.JPanel implements ManagerUpdateListener {

    private GUIManager gui;

    /** Creates new form PServer */
    public PServer(GUIManager gui) {
        this.gui = gui;
        initComponents();
        Setting<Integer> port = gui.system.settingsManager.find("serverPort");
        if (port != null) {
            tfPort.setText(port.value.toString());
        }
        gui.system.nodeManager.addUpdateListener(this);
    }

    public void update(Manager m) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                lClients.updateUI();
            }
        });
    }

    private class ClientListModel extends AbstractListModel {

        public Object getElementAt(int index) {
            return gui.system.nodeManager.get(index);
        }

        public int getSize() {
            return gui.system.nodeManager.size();
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

        bStart = new javax.swing.JButton();
        tfPort = new javax.swing.JTextField();
        lPort = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lClients = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        bStart.setText("Start");
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });

        tfPort.setText("4055");

        lPort.setText("Port:");

        lClients.setModel(new ClientListModel());
        lClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lClientsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lClients);

        jLabel1.setText("Clients:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lPort)
                        .addGap(18, 18, 18)
                        .addComponent(tfPort, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bStart))
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPort)
                    .addComponent(bStart)
                    .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
        try {
            if (gui.system.server.start(Integer.parseInt(tfPort.getText()))) {
                bStart.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            gui.system.userInterface.handleError("Port number was invalid.");
        }
}//GEN-LAST:event_bStartActionPerformed

private void lClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lClientsMouseClicked
    if (evt.getClickCount() == 2) {
        int index = lClients.locationToIndex(evt.getPoint());
        ListModel model = lClients.getModel();
        Node n = (Node) model.getElementAt(index);
        lClients.ensureIndexIsVisible(index);
        gui.fmain.addIFrame(n.toString(), new PNode(gui, n), -1, -1);
    }
}//GEN-LAST:event_lClientsMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lClients;
    private javax.swing.JLabel lPort;
    private javax.swing.JTextField tfPort;
    // End of variables declaration//GEN-END:variables
}
