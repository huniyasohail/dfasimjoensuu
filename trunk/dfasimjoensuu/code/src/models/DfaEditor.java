package models;

import controller.DFAPainter;
import controller.Simulator;
import gui.DFAMainWin;
import javax.swing.SwingUtilities;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.2B0808A6-6365-E921-E748-22BEC69D6EAD]
// </editor-fold> 






//--------------------  STATES ---------------------------

public class DfaEditor{

    private boolean isEditable;
    private double zoomfactor = 1;
    private int offsetX = 0;
    private int offsetY = 0;
    private int oldoffsetX = 0;
    private int oldoffsetY = 0;
    private int panStartX = 0;
    private int panStartY = 0;

    private int dragOffsetX = 0;
    private int dragOffsetY = 0;
    
    private DFAMainWin dFAMainWin = null;

    private State currentStateSelected = null;
    private Transition currentTransSelected = null;

    private State transitionAddFrom = null;
    private State transitionAddTo = null;

    //-- dummies for userinteraction --
    private State dummyState = new State("new");
    private Transition dummyTransition = new Transition(null,null);

    //-- LineToucher --
    private TouchButton touchButton = new TouchButton();

    //-- states --
    private EditorSelectionStates selectionState;
    private EditorToolStates toolState;
    private EditorTransitionStates transitionState;

    private Dfa dfa = null;
    private DFAPainter dFAPainter;
    private Simulator dfaSim = null;

    public Dfa getDfa() {
        return dfa;
    }

    public void setDfa(Dfa dfa) {
        this.dfa = dfa;
    }

    public DFAPainter getdFAPainter() {
        return dFAPainter;
    }

    public TouchButton getTouchButton() {
        return touchButton;
    }

    public void setTouchButton(TouchButton touchButton) {
        this.touchButton = touchButton;
    }

    public State getDummyState() {
        return dummyState;
    }

    public void setDummyState(State dummyState) {
        this.dummyState = dummyState;
    }


    public void setdFAPainter(DFAPainter dFAPainter) {
        this.dFAPainter = dFAPainter;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public DFAMainWin getdFAMainWin() {
        return dFAMainWin;
    }

    public void setdFAMainWin(DFAMainWin dFAMainWin) {
        this.dFAMainWin = dFAMainWin;
    }

    public Transition getDummyTransition() {
        return dummyTransition;
    }

    public void setDummyTransition(Transition dummyTransition) {
        this.dummyTransition = dummyTransition;
    }


    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.07B7D0A4-8D42-06F0-1C6A-6AB91D0ED769]
    // </editor-fold>
    public DfaEditor (Simulator dfaSimulator) {
        dFAPainter = new DFAPainter(dfaSimulator);
        dfaSim = dfaSimulator;
        dFAPainter.setDfaEditor(this);
        initEditor();
    }


    /**
     * Init all things needed for the editor 
     */
    private void initEditor()
    {
        this.resetEditor();
    }


    /**
     * reset state variables and
     */
    public void resetEditor()
    {
        this.isEditable = true;
        this.selectionState = EditorSelectionStates.selectNothing;
        this.toolState = EditorToolStates.handTool;
        this.transitionState = EditorTransitionStates.selectFromState;
        this.offsetX = 0;
        this.offsetY = 0;
        this.currentStateSelected = null;
        this.currentTransSelected = null;
        this.panStartX = 0;
        this.panStartY = 0;
        touchButton.hideAndReset();
        setToolEnvirionmentOptions();
    }

    /**
     * Tool options settings must be correct after changing tools.
     */
    public void setToolEnvirionmentOptions()
    {
        if (toolState == EditorToolStates.handTool)
        {
            dummyState.getState_Properties().setVisible(false);
            dummyTransition.setVisible(false);
        }
        if (toolState == EditorToolStates.addState)
        {
             dummyState.getState_Properties().setVisible(true);
             dummyTransition.setVisible(false);
        }
        if (toolState == EditorToolStates.addTransition)
        {
             dummyState.getState_Properties().setVisible(false);
             dummyTransition.setVisible(false);
             this.transitionState = EditorTransitionStates.selectFromState;
        }
    }

    public void removeAllSelections()
    {
        this.currentStateSelected = null;
        this.currentTransSelected = null;
        touchButton.hideAndReset();
        
        for (int i=0;i<getDfa().getStates().size();i++)
        {
            State st = getDfa().getStates().get(i);
            for (int j=0;j<st.getTransitions().size();j++)
            {
                Transition tt = st.getTransitions().get(j);
                tt.setSelected(false);
                tt.setHighlightStatus(HighlightTypes.NoHighlight);
            }
            st.getState_Properties().setSelected(false);
            st.getState_Properties().setHighlightIndex(HighlightTypes.NoHighlight);
            
        }
    }

/**
 * handle the mouse
 * @param evt
 */
    public void handleMousePressed(java.awt.event.MouseEvent evt)
    {
        if (toolState == EditorToolStates.handTool)
        {
            handleObjectSelection(evt);            
        }
        //-- mouse pan --
        panStartX = evt.getX();
        panStartY = evt.getY();
        oldoffsetX = offsetX;
        oldoffsetY = offsetY;
    }

    public void handleMouseDragged(java.awt.event.MouseEvent evt)
    {
         handleToolHandMouseDragged(evt);
    }


    /**
 * handle the mouse
 * @param evt
 */
    public void handleMouseMoved(java.awt.event.MouseEvent evt)
    {
        if (toolState == EditorToolStates.handTool)
        {
            handleObjectHighlighting(evt);
        }
        if (toolState == EditorToolStates.addState)
        {
            handleAddStatesMove(evt);
            updateGraphicsAll();
        }
        if (toolState == EditorToolStates.addTransition)
        {
            handleObjectHighlighting(evt);
            showPossibleTransitons(evt);
            updateGraphicsAll();
        }


    }


    public void showPossibleTransitons(java.awt.event.MouseEvent evt)
    {
        if (this.transitionState == EditorTransitionStates.selectToState)
        {
            if (transitionAddFrom != null)
            {
                 State stateMouseOver = getStateAtMouse(evt.getX(), evt.getY(), false, HighlightTypes.NoHighlight, false);
                 if (stateMouseOver != null)
                 {
                     //-- draw highlight transitions --
                     dummyTransition.setS1(transitionAddFrom);
                     dummyTransition.setS2(stateMouseOver);
                     dummyTransition.setVisible(true);
                 }

            }

        }
    }


/**
 * handle the mouse
 * @param evt
 */
    public void handleMouseReleased(java.awt.event.MouseEvent evt)
    {
        if (toolState == EditorToolStates.handTool)
        {
            handleHandToolMouseRelease(evt);
        }
        if (toolState == EditorToolStates.addState)
        {
             handleAddState(evt);
             updateGraphicsAll();
        }
        if (toolState == EditorToolStates.addTransition)
        {
            handleAddTransitionStep(evt);
        }
    }


    public void handleAddTransitionStep(java.awt.event.MouseEvent evt)
    {
        if (this.transitionState == EditorTransitionStates.selectFromState)
        {
            State stateHit = getStateAtMouse(evt.getX(), evt.getY(), true, HighlightTypes.NoHighlight, true);
            if (stateHit != null)
            {
                transitionAddFrom = stateHit;
                this.transitionState = EditorTransitionStates.selectToState;
            }
        } else
        {
            State stateHit = getStateAtMouse(evt.getX(), evt.getY(), true, HighlightTypes.NoHighlight, true);
            if (stateHit != null)
            {
                transitionAddTo = stateHit;
                stateHit.getState_Properties().setSelected(false);
                this.transitionState = EditorTransitionStates.selectFromState;
                if (transitionAddFrom != null)
                {
                    addNewTransition(transitionAddFrom,transitionAddTo);
                    dummyTransition.setVisible(false);
                    updateGraphicsAll();
                }
            } else
            {
                transitionAddTo = null;
                transitionAddFrom = null;
                this.transitionState = EditorTransitionStates.selectFromState;
                dummyTransition.setVisible(false);
            }


        }
    }

    private Transition addNewTransition(State from, State to)
    {
       Transition t =  dfa.addTransition(from, to);
       currentTransSelected = t;
       removeAllSelections();
       t.setSelected(true);
       dFAMainWin.showTransEditWin(t);
       updateGraphicsAll();
       return t;
    }


    public void handleDoubleClick(java.awt.event.MouseEvent evt)
    {
        if (toolState == EditorToolStates.handTool)
        {
            CheckOpenPropertiesWindows(evt);
        }
    }



    private void CheckOpenPropertiesWindows(java.awt.event.MouseEvent evt)
    {
        if (currentStateSelected != null)
        {
            dFAMainWin.showStateEditWin(currentStateSelected);
        } else
        if (currentTransSelected != null)
        {
            dFAMainWin.showTransEditWin(currentTransSelected);
        }

    }

    private void handleToolHandMouseDragged(java.awt.event.MouseEvent evt)
    {

        if (SwingUtilities.isLeftMouseButton(evt))
        {
            handleTouchStartDrag(evt);
             if (currentStateSelected == null)
             {
                if (touchButton.isSelected())
                {
                    handleTouchButtonMoveValue(evt);
                } else
                {
                    setOffsetX(oldoffsetX+evt.getX()-panStartX);
                    setOffsetY(oldoffsetY+evt.getY()-panStartY);                  
                    
                }


                 updateGraphicsAll();
                 //System.out.println("offsetX:"+offsetX+"   offsetx:"+offsetY+" panstartX"+panStartX+"  panstarty "+panStartY+"   MX"+evt.getX()+"   MY"+evt.getY()+"    dx"+(evt.getX()-panStartX));
             } else
             {
                if (currentStateSelected != null)
                {
                 handleStateMovement(evt);
                }

                 updateGraphicsAll();
             }
        }
       
    }

    private void handleTouchButtonMoveValue(java.awt.event.MouseEvent evt)
    {
       
        if (currentTransSelected != null)
        {
            
            //-- calc arc factor --
            int s1x = currentTransSelected.getFromState().getState_Properties().getXPos();
            int s1y = currentTransSelected.getFromState().getState_Properties().getYPos();

            int s2x = currentTransSelected.getToState().getState_Properties().getXPos();
            int s2y = currentTransSelected.getToState().getState_Properties().getYPos();

            double mx = (s1x+s2x)/2;
            double my = (s1y+s2y)/2;
            
            double dx = s2x - s1x;
            double dy = s2y - s1y;

            double l = 0;
            
            double vlength = calcVectorLength(dx,dy);

            if (vlength > 0)
            {
                double centerx = (s2x + s1x)/2;
                double centery = (s2y + s1y)/2;

                double normx = dx/vlength;
                double normy = dy/vlength;

                double turnedx = normy;
                double turnedy = -normx;

                double ddx = evt.getX()-offsetX-centerx;
                double ddy = evt.getY()-offsetY-centery;


                l = 6*(ddx*turnedx + ddy*turnedy)/vlength;
            }


            currentTransSelected.setCurveFactor(l);
            touchButton.setCurrentValue(l);
            touchButton.setVisible(true);

        }
        
    }

    private double calcVectorLength(double dx, double dy)
    {
        if (dx == 0 && dy == 0)
        {
            return 0;
        } else
        {
            return Math.sqrt(dx*dx+dy*dy);
        }
    }

    private void handleAddStatesMove(java.awt.event.MouseEvent evt)
    {
       dummyState.getState_Properties().setVisible(true);
       dummyState.getState_Properties().setXPos(evt.getX()-getOffsetX());
       dummyState.getState_Properties().setYPos(evt.getY()-getOffsetY());
    }

    private void handleAddState(java.awt.event.MouseEvent evt)
    {
        dummyState.getState_Properties().setVisible(false);

        removeAllSelections();
        State s = dfa.addState();
        s.getState_Properties().setName("q"+dfa.getStates().size());
        s.getState_Properties().setSelected(true);
        s.getState_Properties().setXPos(this.dummyState.getState_Properties().getXPos());
        s.getState_Properties().setYPos(this.dummyState.getState_Properties().getYPos());
        updateGraphicsAll();
        dFAMainWin.showStateEditWin(s);
    }


    private void handleObjectSelection(java.awt.event.MouseEvent evt)
    {
        State stateHit = currentStateSelected;
        Transition transHit = currentTransSelected;

        if (touchButton.isSelected())
        {
           touchButton.setMoving(handleTouchUpHighlight(evt));
        }


        if (!touchButton.isMoving())
        {
             stateHit = getStateAtMouse(evt.getX(), evt.getY(), false, HighlightTypes.NoHighlight, true);
             transHit = getTransitionatMouse(evt.getX(), evt.getY(), false, HighlightTypes.NoHighlight, true);
        }

        if (transHit != null)
        {
            System.out.println("Trans hit!");

        } else
        if (stateHit != null)
        {
            updateGraphicsAll();
            System.out.println("Hit state: "+stateHit.getState_Properties().getName());
            dragOffsetX = evt.getX()-offsetX-stateHit.getState_Properties().getXPos();
            dragOffsetY = evt.getY()-offsetY-stateHit.getState_Properties().getYPos();
        }
        this.currentStateSelected = stateHit;
        this.currentTransSelected = transHit;
    }



    private State getStateAtMouse(int px, int py, boolean changeHighlight, HighlightTypes highlightIndex, boolean selectOnHit)
    {
        State s = null;
        double tx = px - offsetX;
        double ty = py - offsetY;

        for (int i=0;i<getDfa().getStates().size();i++)
        {
            State st = getDfa().getStates().get(i);
            double dx = tx-st.getState_Properties().getXPos();
            double dy = ty-st.getState_Properties().getYPos();

            if ((dx*dx+dy*dy) < dFAPainter.getStateDrawSize()*dFAPainter.getStateDrawSize()/4)
            {
                s = st;
                if (changeHighlight)
                st.getState_Properties().setHighlightIndex(highlightIndex);
                if (selectOnHit)
                st.getState_Properties().setSelected(true);
            } else
            {
                if (changeHighlight)
                st.getState_Properties().setHighlightIndex(HighlightTypes.NoHighlight);
                if (selectOnHit)
                st.getState_Properties().setSelected(false);
            }
        }
        return s;
    }

      private Transition getTransitionatMouse(int px, int py, boolean changeHighlight, HighlightTypes highlightIndex, boolean selectOnHit)
    {
        Transition t = null;
        double tx = px - offsetX;
        double ty = py - offsetY;

        for (int i=0;i<getDfa().getStates().size();i++)
        {
            State st = getDfa().getStates().get(i);
            for (int j=0;j<st.getTransitions().size();j++)
            {
                Transition tt = st.getTransitions().get(j);
                
                double dx = tx-tt.getClickPositionX();
                double dy = ty-tt.getClickPositionY();

                if ((dx*dx+dy*dy) < dFAPainter.getVirtualTransitionSite()*dFAPainter.getVirtualTransitionSite()/4)
                {
                    t = tt;
                    if (changeHighlight)
                        tt.setHighlightStatus(highlightIndex);
                    if (selectOnHit)
                        tt.setSelected(selectOnHit);
                } else
                {
                
                 if (changeHighlight)
                    tt.setHighlightStatus(HighlightTypes.NoHighlight);
                 if (selectOnHit)
                     tt.setSelected(false);
                }
            }

        }

        return t;
      }



    /**
     * highlight objects
     */
    private void handleObjectHighlighting(java.awt.event.MouseEvent evt)
    {
        if (toolState == EditorToolStates.handTool)
        {
            State s = getStateAtMouse(evt.getX(), evt.getY(),true,HighlightTypes.MouseOver,false);
            Transition transHit = getTransitionatMouse(evt.getX(), evt.getY(), true, HighlightTypes.MouseOver, false);

            handleTouchUpHighlight(evt);
            updateGraphicsAll();
        }
        if (toolState == EditorToolStates.addTransition)
        {
            State s = getStateAtMouse(evt.getX(), evt.getY(),true,HighlightTypes.MouseOver,false);
        }
    }


    private void handleStateMovement(java.awt.event.MouseEvent evt)
    {
        if (currentStateSelected != null)
        {
            int px = evt.getX()-offsetX-dragOffsetX;
            int py = evt.getY()-offsetY-dragOffsetY ;
            currentStateSelected.getState_Properties().setXPos(px);
            currentStateSelected.getState_Properties().setYPos(py);
        }
    }

    private boolean handleTouchUpHighlight(java.awt.event.MouseEvent evt)
    {
        if (touchButton.isVisible())
        {
            int dx = touchButton.getPx() - evt.getX();
            int dy = touchButton.getPy() - evt.getY();
            boolean hit = dx*dx+dy*dy <= touchButton.getSize()*touchButton.getSize();
            touchButton.setSelected(hit);
            if (hit)
            {
                if (currentTransSelected != null)
                {
                    touchButton.setCurrentValue(currentTransSelected.getCurveFactor());
                } else
                    touchButton.hideAndReset();
            }
         
            return hit;
        } else
            return false;

    }


    private boolean handleTouchStartDrag(java.awt.event.MouseEvent evt)
    {
        if (touchButton.isVisible())
        {
            touchButton.setMoving(true);
            return true;
        } else
        {
             touchButton.setMoving(false);
             return false;
        }
        
    }


     private void handleTouchEndDrag(java.awt.event.MouseEvent evt)
    {
        if (touchButton.isMoving() && touchButton.isVisible())
        {
            touchButton.setMoving(false);
            
        } else
        {
             touchButton.setMoving(false);
            
        }

    }




    private void handleHandToolMouseRelease(java.awt.event.MouseEvent evt)
    {
        handleStateMovement(evt);

        if (toolState == EditorToolStates.handTool)
        {
            State s = getStateAtMouse(evt.getX(), evt.getY(),true,HighlightTypes.MouseOver,true);
             handleTouchEndDrag(evt);
            updateGraphicsAll();
        }
    }



    private void updateGraphicsAll()
    {
        dFAPainter.requestRepaintAll();
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public double getZoomfactor() {
        return zoomfactor;
    }

    public void setZoomfactor(double zoomfactor) {
        this.zoomfactor = zoomfactor;
    }

    public EditorSelectionStates getSelectionState() {
        return selectionState;
    }

    public void setSelectionState(EditorSelectionStates selectionState) {
        this.selectionState = selectionState;
    }

    public EditorToolStates getToolState() {
        return toolState;
    }

    public void setToolState(EditorToolStates toolState) {
        this.toolState = toolState;
        setToolEnvirionmentOptions();
    }

    public EditorTransitionStates getTransitionState() {
        return transitionState;
    }

    public void setTransitionState(EditorTransitionStates transitionState) {
        this.transitionState = transitionState;
    }

    
 enum EditorSelectionStates {
     selectNothing, selecetState, selectTransition, selectAll
 }


 enum EditorTransitionStates {
     selectFromState, selectToState
 }


}



