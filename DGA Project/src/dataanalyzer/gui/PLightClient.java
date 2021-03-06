/*
 * PLightClient.java
 *
 * Created on 22 March 2009, 22:06
 */
package dataanalyzer.gui;

import dataanalyzer.DGA;

/**
 *
 * @author  Petah
 */
public class PLightClient extends javax.swing.JPanel {

    private DGA system;

    /** Creates new form PLightClient */
    public PLightClient(DGA system) {
        this.system = system;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textPaneScroll = new javax.swing.JScrollPane();
        textPane = new javax.swing.JEditorPane();
        bConnect = new javax.swing.JButton();
        bDisconnect = new javax.swing.JButton();

        textPane.setEditorKit(new javax.swing.text.html.HTMLEditorKit());
        textPaneScroll.setViewportView(textPane);

        bConnect.setText("Connect");
        bConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConnectActionPerformed(evt);
            }
        });

        bDisconnect.setText("Disconnect");
        bDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDisconnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDisconnect)
                .addContainerGap(484, Short.MAX_VALUE))
            .addComponent(textPaneScroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConnect)
                    .addComponent(bDisconnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPaneScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void bConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConnectActionPerformed
    if (system.client.connect()) {
        bConnect.setEnabled(false);
        bDisconnect.setEnabled(true);
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
    public javax.swing.JEditorPane textPane;
    public javax.swing.JScrollPane textPaneScroll;
    // End of variables declaration//GEN-END:variables
}
