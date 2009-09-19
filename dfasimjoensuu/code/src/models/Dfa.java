package models;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.CCC2B68F-8475-9ECB-2126-E50681B0C4B0]
// </editor-fold> 
public class Dfa {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.71083E97-6397-D2D6-853D-97DBD63CFF6A]
    // </editor-fold> 
    private String input;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B5A933E2-233B-A1A4-EDA6-51D88CB7AD62]
    // </editor-fold> 
    private int currentPosition;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.605BE7FE-5F6E-ADC8-1805-4DB235B9D755]
    // </editor-fold> 
    private State currentState;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EC7BEFBE-E5DE-0F26-2936-4860283E2109]
    // </editor-fold> 
    public Dfa () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3DB78021-72EE-B158-BB53-455BE680A212]
    // </editor-fold> 
    public State addState () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DE235C0F-584F-4591-F990-DBB9F923B5E7]
    // </editor-fold> 
    public void removeState () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.36246B2B-BC85-4EC0-7F8C-4EE51CC8FB99]
    // </editor-fold> 
    public Transition addTransition (State s1, State s2) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4F6EFCDF-1AEE-5853-F440-BE54E9622042]
    // </editor-fold> 
    public void removeTransition (Transition t) {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.11C95992-99D2-11F9-A67C-47EE7952DD2D]
    // </editor-fold> 
    public Transition changeTransition (Transition t) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CA9EABF6-A357-AF6E-683E-53A7818F164B]
    // </editor-fold> 
    public int getCurrentPosition () {
        return currentPosition;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A4D9AE94-C539-78F0-1740-7A8D5FFF3404]
    // </editor-fold> 
    public void setCurrentPosition (int val) {
        this.currentPosition = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F3D95C55-DEF9-7C3D-4B2D-A334F3DB4EB0]
    // </editor-fold> 
    public State getCurrentState () {
        return currentState;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8AEB9F4E-43E6-4450-7CFB-CC87B81852BF]
    // </editor-fold> 
    public void setCurrentState (State val) {
        this.currentState = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4E4D456F-822D-37C2-EDC3-AB7C014DC48F]
    // </editor-fold> 
    public String getInput () {
        return input;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F23FD5A5-CC69-E8F3-605D-98669A577B79]
    // </editor-fold> 
    public void setInput (String val) {
        this.input = val;
    }

}

