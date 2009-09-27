/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DFAStatePropertiesWin.java
 *
 * Created on 20.09.2009, 18:12:04
 */

package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import models.State;

/**
 *
 * @author Fabian
 */
public class DFAStatePropertiesWin extends JFrame {

    private State state = null;
    private DFAMainWin dFAMainWin = null;
    private boolean newElement = false;
    private boolean editedOK = false;

    /** Creates new form DFAStatePropertiesWin */
    public DFAStatePropertiesWin() {
        initComponents();
        centreWindow(this);
    }

    public boolean isNewElement() {
        return newElement;
    }

    public void setNewElement(boolean newElement) {
        this.newElement = newElement;
    }

    public State getEditState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public DFAMainWin getdFAMainWin() {
        return dFAMainWin;
    }

    public void setdFAMainWin(DFAMainWin dFAMainWin) {
        this.dFAMainWin = dFAMainWin;
    }

    void initStatetoForm()
    {
        if (this.state != null)
        {
            textName.setText(state.getState_Properties().getName());
            checkAccept.setSelected(state.getIsFinalState());
            checkStart.setSelected(state.getIsStartState());
        }

    }

    void saveFormtoState()
    {
        state.getState_Properties().setName(textName.getText());
        state.setIsFinalState(checkAccept.isSelected());
        if(checkStart.isSelected())
            dFAMainWin.getDfaSim().getDfa().setStartState(state);
        editedOK = true;
        setVisible(false);
        dFAMainWin.repaint();
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        checkStart = new javax.swing.JCheckBox();
        checkAccept = new javax.swing.JCheckBox();
        buttonOK = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("State properties");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Name");

        textName.setText("q0");
        textName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNameActionPerformed(evt);
            }
        });
        textName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNameKeyPressed(evt);
            }
        });

        jLabel2.setText("Type");

        checkStart.setText("Start State");
        checkStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkStartActionPerformed(evt);
            }
        });

        checkAccept.setText("Accepting/Final State");
        checkAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAcceptActionPerformed(evt);
            }
        });

        buttonOK.setText("Ok");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkAccept)
                            .addComponent(checkStart))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(buttonCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkStart)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkAccept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNameActionPerformed

    private void checkStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkStartActionPerformed
        
    }//GEN-LAST:event_checkStartActionPerformed

    private void checkAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAcceptActionPerformed

    }//GEN-LAST:event_checkAcceptActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        initStatetoForm();
        textName.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        closeWin();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed


    private void closeWin()
    {
       if (!editedOK && newElement)
       {
            dFAMainWin.getDfaSim().getDfaEditor().deleteState(state, false);
       }
       dFAMainWin.handleCloseEditWindow();
    }

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        saveFormtoState();
    }//GEN-LAST:event_buttonOKActionPerformed

    private void textNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNameKeyPressed
         int key = evt.getKeyCode();
         if (key == KeyEvent.VK_ENTER) {
            saveFormtoState();
         }

    }//GEN-LAST:event_textNameKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        closeWin();
    }//GEN-LAST:event_formWindowClosed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DFAStatePropertiesWin().setVisible(true);
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
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonOK;
    private javax.swing.JCheckBox checkAccept;
    private javax.swing.JCheckBox checkStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField textName;
    // End of variables declaration//GEN-END:variables


}
