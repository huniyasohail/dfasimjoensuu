/*
 * DFAMainWIn.java
 *
 * Created on 17.09.2009, 20:01:41
 */
package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Fabian
 */
public class DFAInfoWin extends javax.swing.JDialog {

    /**
     * Width of the window
     */
    private static final int WINDOW_WIDTH = 540;
    /**
     * Height of the window
     */
    private static final int WINDOW_HEIGHT = 330;

    /** Project website URI */
    String websiteUri = "http://code.google.com/p/dfasimjoensuu/";

    /** Creates new form DFAMainWIn */
    public DFAInfoWin() {

        initComponents();
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        centreWindow(this);
        this.setModal(true);
        this.labelWebsiteUri.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelWebsiteUri = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DFA Simulator");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 0, 400, 400));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("DFA Simulator");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 10, 410, 50);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("GNU Gernal Public License v2/v3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 250, 200, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setText("Kai Winnekens, Fabian Bürger");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 110, 440, 20);

        jButton1.setText("close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(420, 250, 90, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("University of Joensuu, Finland");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 90, 440, 17);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel8.setText("Student project of the Object Orientated Programming Course.");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 160, 440, 17);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel9.setText("Student project of the Object Orientated Programming Course");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 160, 440, 17);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Version 1.0, October 2009");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(30, 270, 162, 17);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("This program comes with ABSOLUTELY NO WARRANTY!");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 180, 440, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setText("Deterministic Finite Automaton Simulator");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 70, 410, 17);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Visit the project website:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 210, 150, 17);

        labelWebsiteUri.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelWebsiteUri.setText(this.websiteUri);
        labelWebsiteUri.setToolTipText("Click here to visit the project website");
        labelWebsiteUri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelWebsiteUriMouseClicked(evt);
            }
        });
        getContentPane().add(labelWebsiteUri);
        labelWebsiteUri.setBounds(180, 210, 340, 17);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void labelWebsiteUriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelWebsiteUriMouseClicked
        try {
            URI website = new URI(this.websiteUri);
            openUriInBrowser(website);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_labelWebsiteUriMouseClicked

    /**
     * Opens an URI in the system default browser.
     * @param uri The URI to open.
     */
    private void openUriInBrowser(URI uri) {
        if (uri != null) {
            if (java.awt.Desktop.isDesktopSupported()) {
                try {
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DFAInfoWin().setVisible(true);

            }
        });
    }

    /**
     * Places the window in the middle of the screen.
     * @param frame The window to place.
     */
    public static void centreWindow(javax.swing.JDialog frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelWebsiteUri;
    // End of variables declaration//GEN-END:variables
}