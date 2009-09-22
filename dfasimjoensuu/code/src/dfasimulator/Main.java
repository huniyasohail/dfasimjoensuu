/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dfasimulator;

import controller.IncompleteAutomatonException;
import controller.Simulator;
import gui.DFAMainWin;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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


       //Let's test the DFA
       Simulator simulator = new Simulator();
       Transition t;
       State s1 = simulator.getDfa().addState();
       State s2 = simulator.getDfa().addState();
       State s3 = simulator.getDfa().addState();
       s1.getState_Properties().setName("q0");
       s2.getState_Properties().setName("q1");
       s3.getState_Properties().setName("q2");
        try {
            t = simulator.getDfa().addTransition(s1, s2);
            s1.addLabelToTransition(t, "1");
            t = simulator.getDfa().addTransition(s1, s1);
            s1.addLabelToTransition(t, "0");
            t = simulator.getDfa().addTransition(s2, s1);
            s2.addLabelToTransition(t, "1");
            t = simulator.getDfa().addTransition(s2, s3);
            s2.addLabelToTransition(t, "0");
            //-- should be done automatically --
            t.setHasBackTransition(false);
            t = simulator.getDfa().addTransition(s3, s3);
            s3.addLabelToTransition(t, "0");
            s3.addLabelToTransition(t, "1");




        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       simulator.getDfa().setStartState(s1);
       s2.setIsFinalState(true);
        try {
            //Set Input
            simulator.startSimulation("0101");
        } catch (IncompleteAutomatonException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


       simulator.getDfa().autoArrangeDFA();
       
       //-- show the mainwindow --
       DFAMainWin mainwin = new DFAMainWin();
       mainwin.setDfaSim(simulator);
       mainwin.setVisible(true);


    }

}