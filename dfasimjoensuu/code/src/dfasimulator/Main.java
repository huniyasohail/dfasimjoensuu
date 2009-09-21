/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dfasimulator;

import controller.Simulator;
import gui.DFAMainWin;
import javax.swing.UIManager;
import models.State;
import models.Transition;

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

       //Let's test the DFA
       Simulator simulator = new Simulator();
       State s1 = simulator.getDfa().addState();
       State s2 = simulator.getDfa().addState();
       simulator.getDfa().addTransition(s1, s2).addToInput("1");
       simulator.getDfa().addTransition(s1, s1).addToInput("0");
       simulator.getDfa().setStartState(s1);
       s2.setIsFinalState(true);

       //Set Input
       simulator.getDfa().setInput("0101");
    }

}