/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.awt.Color;

/**
 *
 * @author Fabian
 */
public class TouchButton {

    private boolean visible = false;

    private int px = 0;
    private int py = 0;
    private double angle = 0;
    private double currentValue = 1;
    private boolean selected = false;
    private boolean moving = false;
    private Transition transition = null;

    private final Color colorNormal = Color.WHITE;
    private final Color colorHightlight = new Color(140,140,255);
    private final Color colorMove = Color.orange;

    private final int size = 6;


    //-- 0: linear, 1: circular --
    private int type = 0;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public void hideAndReset()
    {
        visible = false;
        currentValue = 1;
    }

    public void showAsLinearToucher(Transition t, int x, int y, double pangle, double value)
    {
        this.transition = t;
        this.px = x;
        this.py = y;
        this.angle = pangle;
        this.currentValue = value;
        this.type = 0;
    }

    public void showAsCircleToucher(Transition t, int x, int y, double pangle, double value)
    {
        this.transition = t;
        this.px = x;
        this.py = y;
        this.angle = pangle;
        this.currentValue = value;
        this.type = 1;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public Color getColorHightlight() {
        return colorHightlight;
    }

    public Color getColorNormal() {
        return colorNormal;
    }

    public int getSize() {
        return size;
    }

    public Color getColorMove() {
        return colorMove;
    }


}
