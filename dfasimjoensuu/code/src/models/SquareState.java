/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author Kai
 */
public class SquareState extends State implements Comparable{

    private State state1;
    private State state2;

    public State getState1() {
        return state1;
    }

    public void setState1(State state1) throws IllegalArgumentException{
        if(state1 != null)
            this.state1 = state1;
        else
            throw new IllegalArgumentException();
    }

    public State getState2() {
        return state2;
    }

    public void setState2(State state2) throws IllegalArgumentException{
        if(state2 != null)
            this.state2 = state2;
        else
            throw new IllegalArgumentException();
    }

    public SquareState(String name, int id) throws IllegalArgumentException {
        super(name, id);
        state1 = null;
        state2 = null;
    }

    public int compareTo(Object o) {
        SquareState comp = (SquareState)o;
        String thisname1 = this.state1.getState_Properties().getName();
        String thisname2 = this.state2.getState_Properties().getName();
        String thatname1 = comp.getState1().getState_Properties().getName();
        String thatname2 = comp.getState2().getState_Properties().getName();

        if(thisname1.compareTo(thatname1) < 0) {
            return -1;
        } else if(thisname1.compareTo(thatname1) > 0) {
            return 1;
        } else if(thisname2.compareTo(thatname2) < 0) {
            return -1;
        } else if(thisname2.compareTo(thatname2) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
