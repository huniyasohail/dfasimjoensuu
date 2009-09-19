/*
 * DFAMainWIn.java
 *
 * Created on 17.09.2009, 20:01:41
 */

package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Fabian
 */
public class DFAMainWin extends javax.swing.JFrame {

    /** Creates new form DFAMainWIn */
    public DFAMainWin() {
        
        initComponents();
        this.setSize(new Dimension(600, 300));
        centreWindow(this);

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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DFA Simulator");
        setBounds(new java.awt.Rectangle(0, 0, 400, 400));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel2.setText("Deterministic Finite Automaton Simulator");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 70, 410, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel3.setText("GNU License");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 210, 440, 17);

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
        jButton1.setBounds(460, 200, 90, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("This program comes with ABSOLUTELY NO WARRANTY!");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 180, 440, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel7.setText("University of Joensuu, Finland");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 90, 440, 17);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/img/logo.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(-20, 0, 621, 141);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Student project of the Object Orientated Programming Course.");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 160, 440, 17);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel9.setText("Student project of the Object Orientated Programming Course");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 160, 440, 17);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Version 0.1 alpha, 2009");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(30, 230, 440, 17);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed



    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DFAMainWin().setVisible(true);
                
            }
        });
    }

public static void centreWindow(JFrame frame) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
}






    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables

}
