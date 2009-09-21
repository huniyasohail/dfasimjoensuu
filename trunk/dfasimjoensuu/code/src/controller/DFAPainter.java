
package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import models.Dfa;
import models.DfaEditor;
import models.State;
import models.Transition;

/**
 *
 * @author Fabian
 */
public class DFAPainter {


    private final int stateDrawSize = 50;
    private final int textSize = 16;

    private DfaEditor dfaEditor = null;
    private Graphics2D graphics = null;


    public DfaEditor getDfaEditor() {
        return dfaEditor;
    }

    public void setDfaEditor(DfaEditor dfaEditor) {
        this.dfaEditor = dfaEditor;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }


    /**
     * paints the States and Transitions
     */
    public void updaterGraphics()
    {
        if (this.graphics != null)
        {
            //-- nice rendering --
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintTransitions();
            paintStates();           
        }
        
    }

    /**
     * paint the states of the DFA
     */
    private void paintStates()
    {
        Graphics2D g = this.graphics;
        Dfa dfa = dfaEditor.getDfa();

        //-- set fonts --
         Font nameFont = new Font("Arial", Font.ITALIC|Font.PLAIN, (int)(textSize*dfaEditor.getZoomfactor()));
         g.setFont(nameFont);

        for (int i=0; i < dfa.getStates().size(); i++)
        {
            State s = dfa.getStates().get(i);
            int x = s.getState_Properties().getXPos();
            int y = s.getState_Properties().getYPos();


            int centerX = (int)(dfaEditor.getOffsetX() + dfaEditor.getZoomfactor()*x);
            int centerY = (int)(dfaEditor.getOffsetY() + dfaEditor.getZoomfactor()*y);

            int radius = (int)(dfaEditor.getZoomfactor()*stateDrawSize/2);

	


           if (s.getIsFinalState())
           {
                int additionalradius = (int) (dfaEditor.getZoomfactor()*5);
                g.setColor(Color.white);
                g.fillOval(centerX-(radius+additionalradius), centerY-(radius+additionalradius ),stateDrawSize+2*additionalradius, stateDrawSize+2*additionalradius);
                g.setColor(Color.black);
                g.drawOval(centerX-(radius+additionalradius), centerY-(radius+additionalradius ),stateDrawSize+2*additionalradius, stateDrawSize+2*additionalradius);
                g.drawOval(centerX-radius, centerY-radius,stateDrawSize, stateDrawSize);

           } else
           {
                g.setColor(Color.white);
                g.fillOval(centerX-radius, centerY-radius,stateDrawSize, stateDrawSize);
                g.setColor(Color.black);
                g.drawOval(centerX-radius, centerY-radius,stateDrawSize, stateDrawSize);
           }


          //-- center the string --
         FontMetrics fm   = g.getFontMetrics(nameFont);
         Rectangle2D rect = fm.getStringBounds(s.getState_Properties().getName(), g);
         int textHeight = (int)(rect.getHeight());
         int textWidth  = (int)(rect.getWidth());
         int textx = centerX  - textWidth/ 2;
         int texty = centerY - textHeight/ 2  + fm.getAscent();

         //-- render text --
         g.drawString(s.getState_Properties().getName(),textx,texty);
        }
    }

    /**
     * paint transition edges of the DFA
     */
    private void paintTransitions()
    {
        Graphics2D g = this.graphics;
        Dfa dfa = dfaEditor.getDfa();

        //-- set fonts --
         Font nameFont = new Font("Arial", Font.ITALIC|Font.PLAIN, (int)(textSize*dfaEditor.getZoomfactor()));
         g.setFont(nameFont);

        for (int i=0; i < dfa.getStates().size(); i++)
        {
            State s = dfa.getStates().get(i);
            for (int j=0; j<s.getTransitions().size();j++)
            {
                Transition t = s.getTransitions().get(j);
                State s1 = t.getFromState();
                State s2 = t.getToState();

                if (s1 != null && s2 != null)
                {
                    int s1x = s1.getState_Properties().getXPos();
                    int s1y = s1.getState_Properties().getYPos();

                    int s2x = s2.getState_Properties().getXPos();
                    int s2y = s2.getState_Properties().getYPos();

                    g.setColor(Color.black);
                    g.drawLine(s1x, s1y, s2x, s2y);
                }

                
            }
        }
    }


}
