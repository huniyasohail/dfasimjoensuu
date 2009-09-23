package models;

import controller.DFAPainter;
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

    private State dummyState = new State("new");
    private Transition dummyTransition = new Transition(null,null);

    //-- states --
    private EditorSelectionStates selectionState;
    private EditorToolStates toolState;
    private EditorTransitionStates transitionState;

    private Dfa dfa = null;
    private DFAPainter dFAPainter;

    public Dfa getDfa() {
        return dfa;
    }

    public void setDfa(Dfa dfa) {
        this.dfa = dfa;
    }

    public DFAPainter getdFAPainter() {
        return dFAPainter;
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
    public DfaEditor () {
        dFAPainter = new DFAPainter();
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

        for (int i=0;i<getDfa().getStates().size();i++)
        {
            State st = getDfa().getStates().get(i);
            for (int j=0;j<st.getTransitions().size();j++)
            {
                Transition tt = st.getTransitions().get(j);
                tt.setSelected(false);
                tt.setHighlightStatus(0);
            }
            st.getState_Properties().setSelected(false);
            st.getState_Properties().setHighlightIndex(0);
            
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
                 State stateMouseOver = getStateatMouse(evt.getX(), evt.getY(), false, 0, false);
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
            State stateHit = getStateatMouse(evt.getX(), evt.getY(), true, 0, true);
            if (stateHit != null)
            {
                transitionAddFrom = stateHit;
                this.transitionState = EditorTransitionStates.selectToState;
            }
        } else
        {
            State stateHit = getStateatMouse(evt.getX(), evt.getY(), true, 0, true);
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
             if (currentStateSelected == null)
             {
                 setOffsetX(oldoffsetX+evt.getX()-panStartX);
                 setOffsetY(oldoffsetY+evt.getY()-panStartY);
                 updateGraphicsAll();
                 //System.out.println("offsetX:"+offsetX+"   offsetx:"+offsetY+" panstartX"+panStartX+"  panstarty "+panStartY+"   MX"+evt.getX()+"   MY"+evt.getY()+"    dx"+(evt.getX()-panStartX));
             } else
             {
                 handleStateMovement(evt);
                 updateGraphicsAll();
             }
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
        State stateHit = getStateatMouse(evt.getX(), evt.getY(), false, 0, true);
        
        Transition transHit = getTransitionatMouse(evt.getX(), evt.getY(), false, 0, true);

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



    private State getStateatMouse(int px, int py, boolean changeHighlight, int highlightIndex, boolean selectOnHit)
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
                st.getState_Properties().setHighlightIndex(0);
                if (selectOnHit)
                st.getState_Properties().setSelected(false);
            }
        }
        return s;
    }

      private Transition getTransitionatMouse(int px, int py, boolean changeHighlight, int highlightIndex, boolean selectOnHit)
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
                    tt.setHighlightStatus(0);
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
            State s = getStateatMouse(evt.getX(), evt.getY(),true,1,false);
            Transition transHit = getTransitionatMouse(evt.getX(), evt.getY(), true, 1, false);


            updateGraphicsAll();
        }
        if (toolState == EditorToolStates.addTransition)
        {
            State s = getStateatMouse(evt.getX(), evt.getY(),true,1,false);
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



    private void handleHandToolMouseRelease(java.awt.event.MouseEvent evt)
    {
        handleStateMovement(evt);

        if (toolState == EditorToolStates.handTool)
        {
            State s = getStateatMouse(evt.getX(), evt.getY(),true,1,true);
            updateGraphicsAll();
        }
    }



    private void updateGraphicsAll()
    {
        dFAPainter.requestRepaintAll();
    }

    public boolean isIsEditable() {
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



