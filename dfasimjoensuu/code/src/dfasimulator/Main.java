/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dfasimulator;

import gui.DFAMainWin;
import javax.swing.UIManager;

/**
 * The main function where the main window is created
 * @author Fabian
 */
public class Main {


    public static void main(String[] args) {

        //-- get system look and feel --
        try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {
        }

        DFAMainWin mainwin = new DFAMainWin();
         mainwin.setVisible(true);

    }

}
