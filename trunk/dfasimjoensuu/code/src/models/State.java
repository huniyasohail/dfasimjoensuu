package models;

import java.util.ArrayList;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.9670F4B5-80DA-DDDC-EF00-A0A4D206ADAD]
// </editor-fold> 
public class State {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4B631DDD-D7DD-EBBD-0264-5BC4D4D8A198]
    // </editor-fold> 
    private boolean isFinalState;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C0EE8059-23DE-B596-6A75-675F1615D3A7]
    // </editor-fold> 
    private boolean isStartState;
    private ArrayList<Transition> transitions;
    private State_Properties prop;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.440B62F6-A5F4-8219-24A6-DD573FD0A0A6]
    // </editor-fold> 
    public State (String name) throws IllegalArgumentException{
        transitions = new ArrayList<Transition>();
        prop = new State_Properties();
        if (name == null)
            throw new IllegalArgumentException();
        //name != null
        prop.setName(name);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7EE5376A-1D36-7D72-468D-57F087DA0423]
    // </editor-fold> 
    public boolean getIsFinalState () {
        return isFinalState;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4ECCB74E-5CE0-A317-B258-9B00928E446B]
    // </editor-fold> 
    public void setIsFinalState (boolean val) {
        this.isFinalState = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3ABC89EF-7B6D-E88A-4230-FEC75D030A8D]
    // </editor-fold> 
    boolean getIsStartState () {
        return isStartState;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.BB239EBA-CB09-0909-2D77-43099BE24A94]
    // </editor-fold> 
    void setIsStartState (boolean val) {
        this.isStartState = val;
    }

    public Transition addTransition(Transition t) {
        transitions.add(t);
        return t;
    }

    public void removeTransition(Transition t) {
        transitions.remove(t);
    }

    public State_Properties getState_Properties() {
        return prop;
    }

    public Transition getTransition (State toState) throws NoSuchTransitionException {
        Transition trans = null;
        for(Transition t:transitions) {
            if (t.getToState() == toState) {
                trans = t;
                break;
            }
        }
        if(trans == null)
            throw new NoSuchTransitionException();
        else
            return trans;
    }

}

class NoSuchTransitionException extends Exception {
    
}

