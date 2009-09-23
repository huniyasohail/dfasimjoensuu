package models;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.3289D7DB-C89B-5F67-EDC9-7932FA63A9BB]
// </editor-fold> 
public class State_Properties {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.20CCD347-10B1-6597-1FCB-7359E09DBC7E]
    // </editor-fold> 
    private int xPos = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AC2F7C63-187B-B7F8-65CC-6904E9124D8B]
    // </editor-fold> 
    private int yPos = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.64AB935F-9911-49BB-8A4F-F2C5F29CB21B]
    // </editor-fold> 
    private String name;

    private boolean selected = false;

    private int HighlightIndex = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D426DFFE-9580-1E93-59AF-36423FBEE20E]
    // </editor-fold>
    /**
     * Returns new state properties.
     */
    public State_Properties () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B24CB749-9D17-113F-9C3B-A5AC8840D4AE]
    // </editor-fold>
    /**
     * Returns the state's name.
     * @return name
     */
    public String getName () {
        return name;
    }

    /**
     * Checkx wheather the state is selected.
     * @return true, iff the state is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets the selected status of the state.
     * @param selected true, iff the state is selected.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Returns the state's highlighting index.
     * @return 0 iff the state is not highlighted.
     * 1 iff the state is currently highlighted (e.g. mouseover).
     * Colors 2-n are for other reasons.
     */
    public int getHightlightIndex() {
        return HighlightIndex;
    }

    /**
     *Sets the highlighting Index.
     * @param selectionIndex TODO
     */
    public void setHighlightIndex(int selectionIndex) {
        this.HighlightIndex = selectionIndex;
    }

    

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3E219D5B-EEDB-5FFB-2D87-9A39F53CA3BD]
    // </editor-fold>
    /**
     * Sets the state's name.
     * @param val State's name.
     * @throws IllegalArgumentException name must not be null.
     */
    public void setName (String val) throws IllegalArgumentException{
        if (val == null)
            throw new IllegalArgumentException();
        //val != null
        this.name = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4EC8C8BE-5DC8-ECBB-9552-1E85C212C9E9]
    // </editor-fold>
    /**
     * Reurns the state's x-position in the editor pane.
     * @return x-position
     */
    public int getXPos () {
        return xPos;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2D8B2722-F34E-34B7-719D-748D0C0BE744]
    // </editor-fold>
    /**
     * Sets the state's x-position in the editor pane.
     * @param val x-position
     */
    public void setXPos (int val) {
        this.xPos = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0D198C83-374B-0A48-CCC4-DDAA503A880B]
    // </editor-fold>
    /**
     * Returns the state's y-position in the editor pane.
     * @return y-position
     */
    public int getYPos () {
        return yPos;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.BD51CF2E-763A-F837-E179-865EF60020C7]
    // </editor-fold>
    /**
     * Sets the state's y-position in the editor pane.
     * @param val y-position
     */
    public void setYPos (int val) {
        this.yPos = val;
    }

}

