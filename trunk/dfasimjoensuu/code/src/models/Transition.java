package models;


import java.io.Serializable;
import java.util.ArrayList;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.87C11C34-F942-9444-FDCC-B025A77DB87B]
// </editor-fold> 
public class Transition implements Serializable{

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FFDC584F-92A7-C21D-8870-4B084854C2DA]
    // </editor-fold> 
    private ArrayList<String> input;
    private State s1;
    private State s2;

    private int captionOffsetX = 0;
    private int captionOffsetY = 0;

    //-- drawing issue --
    private boolean hasBackTransition = true;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4D334F29-4CB2-6FB2-A865-67910CCFD054]
    // </editor-fold>
    /**
     * Creates a new transition from state s1 to state s2.
     * @param s1 Start state.
     * @param s2 Target state.
     */
    public Transition (State s1, State s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.input = new ArrayList<String>();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.29356564-A500-AF08-A0D0-B663255DBC55]
    // </editor-fold>
    /**
     * Returns the transitions input (the labels).
     * @return ArrayList of label strings.
     */
    public ArrayList<String> getInput () {
        return input;
    }

    /**
     * Returns the x-coordinate of the label relative to its original position.
     * @return x-coordinate
     */
    public int getCaptionOffsetX() {
        return captionOffsetX;
    }

    /**
     * Sets the x-coordinate of the label relative to its original position.
     * @param captionOffsetX x-coordinate
     */
    public void setCaptionOffsetX(int captionOffsetX) {
        this.captionOffsetX = captionOffsetX;
    }

    /**
     * Returns the y-coordinate of the label relative to its original position.
     * @return y-coordinate
     */
    public int getCaptionOffsetY() {
        return captionOffsetY;
    }

    /**
     * Sets the y-coordinate of the label relative to its original position.
     * @param captionOffsetY y-coordinate
     */
    public void setCaptionOffsetY(int captionOffsetY) {
        this.captionOffsetY = captionOffsetY;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.520A2AF8-8659-E1E6-923A-70D72E8DCF7F]
    // </editor-fold>
    /**
     * Sets the transition's label.
     * @param val ArrayList of possible input characters. If null, the
     * transition's label list gets emptied.
     */
    public void setInput (ArrayList<String> val) {
        if(val.size() == 0 || val == null)
            this.input = val;
        else
            for(String s:val)
                addToInput(s);
    }

    /**
     * Adds a character to the transition's label list.
     * @param input Input character.
     */
    public void addToInput(String input) {
        if(!this.input.contains(input))
            this.input.add(input);
    }

    /**
     * Returns the transition's start state.
     * @return Start state.
     */
    public State getFromState() {
        return s1;
    }

    /**
     * Returns the transition's target state
     * @return Target state.
     */
    public State getToState() {
        return s2;
    }

    /**
     *  Checks wheather there is a transition in the opposite direction.
     * @return true, iff there is a transition in the opposite direction.
     */
    boolean isHasBackTransition() {
        return hasBackTransition;
    }

    /**
     * Sets wheather there is a transition in the opposite direction.
     * @param hasBackTransition true/false.
     */
    void setHasBackTransition(boolean hasBackTransition) {
        this.hasBackTransition = hasBackTransition;
    }

}