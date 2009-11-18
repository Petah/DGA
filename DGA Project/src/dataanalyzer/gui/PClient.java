/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PClient.java
 *
 * Created on 23/03/2009, 9:41:41 AM
 */
package dataanalyzer.gui;

import dataanalyzer.DGA;
import dataanalyzer.entity.Setting;
// FIXME: Dissconnect button not working
/**
 *
 * @author Student
 */
public class PClient extends javax.swing.JPanel {

    private DGA system;

    /** Creates new form PClient */
    public PClient(DGA system) {
        this.system = system;
        initComponents();
        Setting<String> host = system.settingsManager.find("hostName");
        if (host != null) {
            tfPort.setText(host.value);
        }
        Setting<Integer> port = system.settingsManager.find("serverPort");
        if (port != null) {
            tfPort.setText(port.value.toString());
        }
        Setting<Integer> returnPort = system.settingsManager.find("returnPort");
        if (returnPort != null) {
            tfReturnPort.setText(returnPort.value.toString());
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

        jTextField1 = new javax.swing.JTextField();
        bConnect = new javax.swing.JButton();
        bDisconnect = new javax.swing.JButton();
        lServerAddress = new javax.swing.JLabel();
        tfServerAddress = new javax.swing.JTextField();
        lPort = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        tfReturnPort = new javax.swing.JTextField();
        lReturnPort = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        bConnect.setText("Connect");
        bConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConnectActionPerformed(evt);
            }
        });

        bDisconnect.setText("Disconnect");
        bDisconnect.setEnabled(false);
        bDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDisconnectActionPerformed(evt);
            }
        });

        lServerAddress.setText("Server address:");

        tfServerAddress.setText("localhost");

        lPort.setText("Port:");

        tfPort.setText("4055");

        tfReturnPort.setText("4056");

        lReturnPort.setText("Return port:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lServerAddress)
                            .addComponent(lReturnPort)
                            .addComponent(lPort))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPort, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(tfServerAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(tfReturnPort, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bConnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDisconnect)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServerAddress)
                    .addComponent(tfServerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lPort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfReturnPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lReturnPort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConnect)
                    .addComponent(bDisconnect))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConnectActionPerformed
        try {
            if (system.client.connect(tfServerAddress.getText(), Integer.parseInt(tfPort.getText()), Integer.parseInt(tfReturnPort.getText()))) {
                bConnect.setEnabled(false);
                bDisconnect.setEnabled(true);
            }
        } catch (NumberFormatException ex) {
            system.userInterface.handleError("Port number was invalid.");
        }
    }//GEN-LAST:event_bConnectActionPerformed

private void bDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDisconnectActionPerformed
    system.client.disconnect();
    bConnect.setEnabled(true);
    bDisconnect.setEnabled(false);
}//GEN-LAST:event_bDisconnectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConnect;
    private javax.swing.JButton bDisconnect;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lPort;
    private javax.swing.JLabel lReturnPort;
    private javax.swing.JLabel lServerAddress;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfReturnPort;
    private javax.swing.JTextField tfServerAddress;
    // End of variables declaration//GEN-END:variables
}