package models;

import controller.DFAPainter;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.2B0808A6-6365-E921-E748-22BEC69D6EAD]
// </editor-fold> 






//--------------------  STATES ---------------------------

public class DfaEditor {

    private boolean isEditable;
    private double zoomfactor = 1;
    private int offsetX = 0;
    private int offsetY = 0;

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
    private void resetEditor()
    {
        this.isEditable = true;
        this.selectionState = EditorSelectionStates.selectNothing;
        this.toolState = EditorToolStates.handTool;
        this.transitionState = EditorTransitionStates.selectFromState;
        this.offsetX = 0;
        this.offsetY = 0;
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
    }

    public EditorTransitionStates getTransitionState() {
        return transitionState;
    }

    public void setTransitionState(EditorTransitionStates transitionState) {
        this.transitionState = transitionState;
    }

    
    


}
 enum EditorToolStates {
    handTool, addState, addTransition
 }

 enum EditorSelectionStates {
     selectNothing, selecetState, selectTransition, selectAll
 }


 enum EditorTransitionStates {
     selectFromState, selectToState
 }


