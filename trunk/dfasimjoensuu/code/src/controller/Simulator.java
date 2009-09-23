package controller;

import java.util.ArrayList;
import models.Dfa;
import models.DfaEditor;
import models.State;
import models.Transition;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.245EA3C4-3790-7E8C-5593-89B2D60EAD85]
// </editor-fold> 
public class Simulator {



    private Dfa dfa;
    private DfaEditor dfaEditor;
    private boolean isRunning;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.265879AE-72DD-068D-26F6-2EA61AD07845]
    // </editor-fold> 
    public Simulator () {
        dfa = new Dfa();
        dfaEditor = new DfaEditor();
        dfaEditor.setDfa(dfa);
        isRunning = false;
    }

    public Dfa getDfa() {
        return dfa;
    }

    public DfaEditor getDfaEditor() {
        return dfaEditor;
    }

    public void setDfaEditor(DfaEditor dfaEditor) {
        this.dfaEditor = dfaEditor;
    }

    public void updateGraphics()
    {
        this.dfaEditor.getdFAPainter().updaterGraphics(null);
    }


    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8BEDC026-8469-BB20-3338-A1F54C9D9D44]
    // </editor-fold> 
    public void startSimulation (String input) throws IncompleteAutomatonException {
        //first check preconditions
        print_automaton();
        checkPreconditions(input);
        isRunning = true;
    }

    public void simulateAll () {
        int currentPosition = dfa.getCurrentPosition();
        String input = dfa.getInput();
        for(int i=0; i<input.length()-currentPosition && isRunning; i++)
            nextStep();
    }

    public boolean isAccepting() {
        int currentPosition = dfa.getCurrentPosition();
        State currentSate = dfa.getCurrentState();
        String input = dfa.getInput();

        return (currentPosition == input.length() && currentSate.getIsFinalState());
    }

    private void checkPreconditions(String input) throws IncompleteAutomatonException{
        //check all pre-conditions
        //check for start state
        dfa.setInput(input);
        if(dfa.getStartState() == null)
            throw new IncompleteAutomatonException("No start state defined!");
        //check for completeness of the transition function
        ArrayList<String> alphabet = getAlphabet(input);

        for(State s:dfa.getStates()) {
            ArrayList<Transition> transitions = s.getTransitions();
            for(String c:alphabet) {
                boolean found = false;
                for(Transition t:transitions) {
                    if(t.getInput().contains(c)) {
                        found = true;
                        break;
                    }
                }
                if(!found)
                    throw new IncompleteAutomatonException("Missing transition for character "+c+" in state "+s.getState_Properties().getName()+".");

            }
        }
    }

    private ArrayList<String> getAlphabet(String input) {
        ArrayList<String> alphabet = new ArrayList<String>();
        for(int i=0; i<input.length(); i++) {
            String c = input.substring(i, i+1);
            boolean found = false;
            int j = 0;
            while(j<alphabet.size() && !found) {
                found = alphabet.get(j).equals(c);
                j++;
            }
            if(!found)
                alphabet.add(c);
        }
        return alphabet;
    }

    private void print_automaton() {
        System.out.println("Starting a simulation with the following automaton:");
        for(State s:dfa.getStates()) {
            System.out.println("State "+s.getState_Properties().getName()+":");
            ArrayList<Transition> transitions = s.getTransitions();
            for(Transition t:transitions) {
                System.out.print("\tTransition from "+t.getFromState().getState_Properties().getName()+" to "+t.getToState().getState_Properties().getName()+" labled with: ");
                for(String c:t.getInput()) {
                    System.out.print(c);
                }
                System.out.print("\n");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C245F85F-9EBB-3228-73CF-853407C3F3DF]
    // </editor-fold> 
    public void nextStep () {
        String input = dfa.getInput();
        if(input.length() == 0)
            isRunning = false;
        if(isRunning) {
            int currentPosition = dfa.getCurrentPosition();
            int nextposition = currentPosition;
            if(currentPosition < input.length()) {
                State currentState = dfa.getCurrentState();
                String read = input.substring(currentPosition, currentPosition+1);
                ArrayList<Transition> transitions = currentState.getTransitions();
                for(int i=0; i<transitions.size() && nextposition == currentPosition; i++) {
                    Transition t = transitions.get(i);
                    if(t.getInput().contains(read)) {
                        //take transition and set new current state
                        nextposition = currentPosition+1;
                        dfa.setCurrentState(t.getToState());
                        String state1 = currentState.getState_Properties().getName();
                        String state2 = t.getToState().getState_Properties().getName();
                        dfa.setCurrentPosition(nextposition);
                        if(nextposition == input.length()) {
                            //all input has been read
                            isRunning = false;
                        }
                    }
                }
            }
        }
    }

}