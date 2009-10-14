/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*

 *
 * Created on 20.09.2009, 18:33:33
 */

package gui;

import controller.Simulator;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Transition;

/**
 *
 * @author Fabian
 */
public class DFAAutoCompleteWin extends JFrame {

    private Simulator sim = null;
    private DFAMainWin dFAMainWin = null;


    /** Creates new form DFATransitionWin */
    public DFAAutoCompleteWin() {
        initComponents();
        centreWindow(this);
    }

   private void closeWin()
    {


    }

    public Simulator getSim() {
        return sim;
    }

    public void setSim(Simulator sim) {
        this.sim = sim;
    }


       private String getCommaStringFromArrayList(ArrayList<String> a)
    {
        if (a.size() == 0)
        {
            return "";
        } else
        {
            String s = "";
            for (int i=0;i<a.size();i++)
            {
                if (i != a.size()-1)
                    s = s + a.get(i) + ",";
                else
                    s = s + a.get(i);
            }
            return s;
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

        jLabel1 = new javax.swing.JLabel();
        textInput = new javax.swing.JTextField();
        buttonCancel = new javax.swing.JButton();
        buttonOK = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelStates = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DFA Autocomplete");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Alphabet");

        textInput.setText("0,1");
        textInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textInputActionPerformed(evt);
            }
        });
        textInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textInputKeyPressed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonOK.setText("Autocomplete");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        jLabel2.setText("Autocomplete DFA: Add missing transitions to states to a given alphabet.");

        labelStates.setText("   ");

        jLabel4.setText("Enter alphabet letters like 'abc' or 'a,b,c'");

        jLabel3.setText("All created transitions will not cause a state change when a missing letter is read.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStates)
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textInput, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelStates))
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


   

    public DFAMainWin getdFAMainWin() {
        return dFAMainWin;
    }

    public void setdFAMainWin(DFAMainWin dFAMainWin) {
        this.dFAMainWin = dFAMainWin;
    }

    void initTranstoForm()
    {
       textInput.setText(getCommaStringFromArrayList(sim.getAlphabetFromTransitions()));

    }

    void saveFormToTransition()
    {
       ArrayList<String> alphabet = getTransitionsInputArray(textInput.getText());
       if (alphabet.size() > 0)
       {
           int counter =  sim.autocompleteDFATransitions(alphabet);
           setVisible(false);
           dFAMainWin.repaint();
           String k = "";
           if (sim.getDfa().getStartState() == null)
               k = "\nNOTE: You must define a start state in order to start the simulation!";
           JOptionPane.showMessageDialog(this, "The transitions are complete now. "+ counter+ " transition label(s) were added."+k,"Autocomplete",JOptionPane.INFORMATION_MESSAGE);
           dispose();           
       } else
       {
          JOptionPane.showMessageDialog(this, "Please enter at least one letter","Autocomplete",JOptionPane.ERROR_MESSAGE);
 
       }

    }

    private ArrayList<String> getTransitionsInputArray(String commaSeparatedInput) {
        ArrayList<String> newArray = new ArrayList<String>(commaSeparatedInput.length());
        for(int i=0; i<commaSeparatedInput.length(); i++) {
            String c = commaSeparatedInput.substring(i, i+1);
            if(c.equals(","))
                continue;
            newArray.add(c);
        }
        return newArray;
    }


    private void textInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textInputActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_textInputActionPerformed

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        saveFormToTransition();
    }//GEN-LAST:event_buttonOKActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
       closeWin();
       setVisible(false);
       dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       initTranstoForm();
       textInput.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void textInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textInputKeyPressed
         int key = evt.getKeyCode();
         if (key == KeyEvent.VK_ENTER) {
            saveFormToTransition();

         }
    }//GEN-LAST:event_textInputKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        closeWin();
    }//GEN-LAST:event_formWindowClosed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
                new DFAAutoCompleteWin().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelStates;
    private javax.swing.JTextField textInput;
    // End of variables declaration//GEN-END:variables

}
