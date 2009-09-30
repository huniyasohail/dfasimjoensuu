package controller;

import java.util.ArrayList;
import java.util.Collections;
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
    private boolean simulationModeActive = false;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.265879AE-72DD-068D-26F6-2EA61AD07845]
    // </editor-fold> 
    public Simulator () {
        dfa = new Dfa();
        dfaEditor = new DfaEditor(this);
        dfaEditor.setDfa(dfa);
        isRunning = false;
    }

    public Dfa getDfa() {
        return dfa;
    }

    public void setDfa(Dfa dfa) {
        if(dfa != null) {
            this.dfa = dfa;
            this.dfaEditor.setDfa(dfa);
       
        }
    }

    public boolean isSimulationModeActive() {
        return simulationModeActive;
    }

    public void setSimulationModeActive(boolean simulationModeActive) {
        this.simulationModeActive = simulationModeActive;
    }

    public DfaEditor getDfaEditor() {
        return dfaEditor;
    }

    public void setDfaEditor(DfaEditor dfaEditor) {
        this.dfaEditor = dfaEditor;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
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

    private boolean alphabetsAreEqual(ArrayList<String> inputAlphabet, ArrayList<String> transitionsAlphabet) {
        if(inputAlphabet.size() > transitionsAlphabet.size())
            return false;
        Collections.sort(inputAlphabet);
        Collections.sort(transitionsAlphabet);
        for(int i=0; i<inputAlphabet.size(); i++) {
            if(!inputAlphabet.get(i).equals(transitionsAlphabet.get(i)))
                return false;
        }
        return true;
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
        return (currentPosition == input.length()-1 && currentSate.getIsFinalState());
    }

    private void checkPreconditions(String input) throws IncompleteAutomatonException{
        //check all pre-conditions
        //check for start state
        dfa.setInput(input);
        if(dfa.getStartState() == null)
            throw new IncompleteAutomatonException("No start state defined!");

        //Make sure the input alphabet equals the dfa's alphabet
        ArrayList<String> alphabetFromInput = getAlphabetFromInput(input);
        ArrayList<String> alphabetFromTransitions = getAlphabetFromTransitions();
        if(!alphabetsAreEqual(alphabetFromInput, alphabetFromTransitions)) {
            String msg = "Error: The input alphabet does not match the alphabet derived from the transitions!";
            throw new IncompleteAutomatonException(msg);
        }
        
        //check for completeness of the transition function
        ArrayList<String> alphabet = getAlphabetFromInput(input);

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

    public ArrayList<String> getAlphabetFromTransitions() {
        ArrayList<String> alphabet = new ArrayList<String>();
        ArrayList<State> states = dfa.getStates();

        for(State s:states) {
            for(Transition t:s.getTransitions()) {
                for(String c:t.getInput()) {
                    if(!alphabet.contains(c))
                        alphabet.add(c);
                }
            }
        }
        return alphabet;
    }

    private ArrayList<String> getAlphabetFromInput(String input) {
        ArrayList<String> alphabet = new ArrayList<String>();
        for(int i=0; i<input.length(); i++) {
            String c = input.substring(i, i+1);
            if(!alphabet.contains(c))
                alphabet.add(c);
        }
        return alphabet;
    }

    private void print_automaton() {
        System.out.println("Starting a simulation with the following automaton:");
        for(State s:dfa.getStates()) {
            String stateMsg = "State "+s.getState_Properties().getName();
            if(s.getIsStartState() && s.getIsFinalState())
                stateMsg += " (start, final):";
            else
                if(s.getIsStartState())
                    stateMsg += " (start):";
                else
                    if(s.getIsFinalState())
                        stateMsg += " (final):";
                    else
                        stateMsg += ":";
            System.out.println(stateMsg);
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
                        if(nextposition < input.length()) {
                            dfa.setCurrentPosition(nextposition);
                        } else {
                            //all input has been read
                            isRunning = false;
                        }
                    }
                }
            }
        }
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void resetDfa() {
        dfa.setCurrentPosition(0);
        dfa.setCurrentState(dfa.getStartState());
        dfa.setInput(null);
    }

    /**
     * Calculates dfa^2, that is the automaton that works on QxQ
     * @param Dfa to square.
     * @return Squared Automaton.
     */
    private Dfa calculateSquareAutomaton(Dfa inputDfa) {
        return null;
        //remove isolated states out of inputDfa
    }

}