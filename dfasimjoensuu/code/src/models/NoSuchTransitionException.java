/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author Kai
 */
public class NoSuchTransitionException extends Exception{

    public NoSuchTransitionException(String message) {
        super(message);
    }

    public NoSuchTransitionException() {
    }
    
}
