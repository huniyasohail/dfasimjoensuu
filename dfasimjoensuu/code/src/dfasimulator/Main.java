/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dfasimulator;

import gui.DFAMainWin;

/**
 * The main function where the main window is created
 * @author Fabian
 */
public class Main {


    public static void main(String[] args) {
       System.out.println("Welcome to the DFA Simulator");
       DFAMainWin mainwin = new DFAMainWin();

       mainwin.setVisible(true);

    }

}
