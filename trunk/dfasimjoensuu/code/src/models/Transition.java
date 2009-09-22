package models;


import java.util.ArrayList;
import javax.print.DocFlavor.INPUT_STREAM;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.87C11C34-F942-9444-FDCC-B025A77DB87B]
// </editor-fold> 
public class Transition {

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
    public Transition (State s1, State s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.input = new ArrayList<String>();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.29356564-A500-AF08-A0D0-B663255DBC55]
    // </editor-fold> 
    public ArrayList<String> getInput () {
        return input;
    }

    public int getCaptionOffsetX() {
        return captionOffsetX;
    }

    public void setCaptionOffsetX(int captionOffsetX) {
        this.captionOffsetX = captionOffsetX;
    }

    public int getCaptionOffsetY() {
        return captionOffsetY;
    }

    public void setCaptionOffsetY(int captionOffsetY) {
        this.captionOffsetY = captionOffsetY;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.520A2AF8-8659-E1E6-923A-70D72E8DCF7F]
    // </editor-fold> 
    public void setInput (ArrayList<String> val) {
        if(val.size() == 0)
            this.input = val;
        else
            for(String s:val)
                addToInput(s);
    }

    void addToInput(String input) {
        if(!this.input.contains(input))
            this.input.add(input);
    }

    public State getFromState() {
        return s1;
    }

    public State getToState() {
        return s2;
    }

    public boolean isHasBackTransition() {
        return hasBackTransition;
    }

    public void setHasBackTransition(boolean hasBackTransition) {
        this.hasBackTransition = hasBackTransition;
    }

}

