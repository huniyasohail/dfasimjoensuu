
package controller;

import gui.PaintPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;
import models.Dfa;
import models.DfaEditor;
import models.NoSuchTransitionException;
import models.State;
import models.Transition;


/**
 *
 * @author Fabian
 */
public class DFAPainter {

    private final Color colorStateHighlight = new Color(197,222,255);
    private final Color colorStateSelected = new Color(197,222,255);
    private final Color colorStateNormal = Color.white;

    private final Color colorStateLinesNotSelected = Color.black;
    private final Color colorStateLinesSelected = Color.blue;


    private final int stateDrawSize = 50;
    private final int textSize = 16;
    private final double arcDistance = 40;

    private boolean antialiasing = true;

    private DfaEditor dfaEditor = null;
    private Graphics2D graphics = null;
    private PaintPanel paintPanel = null;
    private Font transitionFont = null;

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

    public PaintPanel getPaintPanel() {
        return paintPanel;
    }

    public void setPaintPanel(PaintPanel paintPanel) {
        this.paintPanel = paintPanel;
    }

    public void requestRepaintAll()
    {
        if (this.paintPanel != null)
            this.paintPanel.repaint();
    }

    /**
     * paints the States and Transitions
     */
    public void updaterGraphics(Graphics2D g)
    {
        if (g != null)
        {
            this.graphics = g;
            if (antialiasing)
            {
                //-- nice rendering --
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
            paintStates();
            paintTransitions();
                   
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

            Color backgroundColor = colorStateNormal;
            Color lineColor = colorStateLinesNotSelected;
            
            if (s.getState_Properties().getHightlightIndex() == 1)
            {
                 backgroundColor = colorStateHighlight;
            }

            if (s.getState_Properties().isSelected())
            {
                 backgroundColor = colorStateSelected;
                 lineColor = colorStateLinesSelected;
            }


            int centerX = (int)(dfaEditor.getOffsetX() + dfaEditor.getZoomfactor()*x);
            int centerY = (int)(dfaEditor.getOffsetY() + dfaEditor.getZoomfactor()*y);
            int radius = (int)(dfaEditor.getZoomfactor()*stateDrawSize/2);

	   if (s.getIsFinalState())
           {
                int additionalradius = (int) (dfaEditor.getZoomfactor()*4);
                g.setColor(backgroundColor);
                g.fillOval(centerX-radius, centerY-radius,stateDrawSize, stateDrawSize);
                g.setColor(lineColor);
                g.drawOval(centerX-radius, centerY-radius,stateDrawSize, stateDrawSize);
                g.drawOval(centerX-(radius-additionalradius), centerY-(radius-additionalradius),stateDrawSize-2*additionalradius, stateDrawSize-2*additionalradius);

           } else
           {
                g.setColor(backgroundColor);
                g.fillOval(centerX-radius, centerY-radius,stateDrawSize, stateDrawSize);
                g.setColor(lineColor);
                g.drawOval(centerX-radius, centerY-radius,stateDrawSize, stateDrawSize);
           }

            if (s.getIsStartState())
            {
                drawStartArrow(centerX-radius-10,centerY,lineColor,g);
            }


          //-- center the string --
         drawCenteredText(s.getState_Properties().getName(),centerX,centerY,nameFont,g);
        }
         g.setColor(Color.black);
    }

    /**
     * paint transition edges of the DFA
     */
    private void paintTransitions()
    {
        Graphics2D g = this.graphics;
        Dfa dfa = dfaEditor.getDfa();

        //-- set fonts --
         transitionFont = new Font("Arial", Font.ITALIC|Font.PLAIN, (int)(0.8*textSize*dfaEditor.getZoomfactor()));
         g.setFont(transitionFont);

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
                  paintTransition(s1,s2,t,getStringFromInputArray(t),Color.black, false);
                }

                
            }
        }
    }

    private String getStringFromInputArray(Transition t)
    {
        if (t != null)
        {
            String c = "";
            for (int i=0;i<t.getInput().size();i++)
            {
                if (i == t.getInput().size()-1)
                {
                    c = c + t.getInput().get(i);
                } else
                {
                    c = c + t.getInput().get(i) + ", ";
                }
            }
            return c;
        } else
        return "-";
    }

    public void paintTransition(State s1, State s2, Transition t, String caption, Color color, boolean fakeTrans)
    {
        Graphics2D g = this.graphics;
        int s1x = s1.getState_Properties().getXPos();
        int s1y = s1.getState_Properties().getYPos();

        int s2x = s2.getState_Properties().getXPos();
        int s2y = s2.getState_Properties().getYPos();

        g.setColor(color);

        //-- arc case or linear --
        boolean isBidirectional = false;
        try {
            isBidirectional = dfaEditor.getDfa().isBidirectionalTransition(s1, s2);
        } catch(NoSuchTransitionException ex) {
            //TODO
            System.out.println(ex.getMessage());
        }
        if (s1 != s2 && isBidirectional)
        {
            //-- get control point --
            int dx = s2x - s1x;
            int dy = s2y - s1y;
            double vlength = calcVectorLength(dx,dy);

            if (vlength > 0)
            {
                QuadCurve2D c = new QuadCurve2D.Double();

                double centerx = (s2x + s1x)/2;
                double centery = (s2y + s1y)/2;

                double normx = dx/vlength;
                double normy = dy/vlength;

                double additionalArcDistance = vlength/100;
                //-- turn vector 90 degrees --
                double turnedx = arcDistance*normy*additionalArcDistance;
                double turnedy = -arcDistance*normx*additionalArcDistance;

                int cpointx = (int) (centerx + turnedx);
                int cpointy = (int) (centery + turnedy);

                int textpointx = (int) (centerx + turnedx/(2)+normy*20);
                int textpointy = (int) (centery + turnedy/(2)-normx*20);

                //-- tangential crossing with the circles (start and end of curve) --
                Vector<Double> p1 = getIntersectionPoint(s1x,s1y,cpointx,cpointy,1.2*stateDrawSize/2);
                Vector<Double> p2 = getIntersectionPoint(s2x,s2y,cpointx,cpointy,1.4*stateDrawSize/2);

                int h1x = (int)Math.round(p1.get(0)) + dfaEditor.getOffsetX();
                int h1y = (int)Math.round(p1.get(1)) + dfaEditor.getOffsetY();

                int h2x = (int)Math.round(p2.get(0)) + dfaEditor.getOffsetX();
                int h2y = (int)Math.round(p2.get(1)) + dfaEditor.getOffsetY();


                //-- quadratic arc --
                c.setCurve(h1x,h1y,
                        cpointx+dfaEditor.getOffsetX(), cpointy+dfaEditor.getOffsetY(),
                        h2x, h2y);
                g.draw(c);
                //-- draw text --
                drawCenteredText(caption,textpointx+t.getCaptionOffsetX()+ dfaEditor.getOffsetX(),textpointy +t.getCaptionOffsetY()+ dfaEditor.getOffsetY(),transitionFont,g);

                //-- arrow --
                double ax = h2x - cpointx -dfaEditor.getOffsetX();
                double ay = h2y - cpointy -dfaEditor.getOffsetY();
                double arrowAngle = Math.atan2(ay, ax);
                drawArrow(h2x,h2y,4,arrowAngle,g);
            }

        } else if (s1 != s2)
        {
            //-- linear-case --
            Vector<Double> p1 = getIntersectionPoint(s1x,s1y,s2x,s2y,1.2*stateDrawSize/2);
            Vector<Double> p2 = getIntersectionPoint(s2x,s2y,s1x,s1y,1.4*stateDrawSize/2);
            int h1x = (int)Math.round(p1.get(0)) + dfaEditor.getOffsetX();
            int h1y = (int)Math.round(p1.get(1)) + dfaEditor.getOffsetY();

            int h2x = (int)Math.round(p2.get(0)) + dfaEditor.getOffsetX();
            int h2y = (int)Math.round(p2.get(1)) + dfaEditor.getOffsetY();
            
            g.drawLine(h1x, h1y, h2x, h2y);

            double ax = s2x - s1x;
            double ay = s2y - s1y;


            double vlength = calcVectorLength(ax,ay);

            if (vlength > 0)
            {

                double centerx = (s2x + s1x)/2;
                double centery = (s2y + s1y)/2;

                double normx = ax/vlength;
                double normy = ay/vlength;

                double arrowAngle = Math.atan2(ay, ax);
                drawArrow(h2x,h2y, 4,arrowAngle,g);
                  // -- text --
                int textX = (int) (centerx + 12*normy *dfaEditor.getZoomfactor());
                int textY = (int) (centery - 12*normx*dfaEditor.getZoomfactor());
                drawCenteredText(caption,textX+t.getCaptionOffsetX()+ dfaEditor.getOffsetX(),textY+t.getCaptionOffsetY()+ dfaEditor.getOffsetY(),transitionFont,g);
            }


        } else
        {
          
            //-- cirlce to state itself --
            double boxX = s1x-stateDrawSize*0.3;
            double boxY = s1y-stateDrawSize*0.95;
            double w = stateDrawSize*0.6;
            double h = stateDrawSize*0.6;
            
            Arc2D arc = new Arc2D.Double(boxX+dfaEditor.getOffsetX(), boxY+dfaEditor.getOffsetY(), w, h, -20, 220, Arc2D.OPEN);
            g.draw(arc);

            // -- text --
            int textX = (int) s1x;
            int textY = (int) (s1y-stateDrawSize*1.2);
            drawCenteredText(caption,textX+t.getCaptionOffsetX()+ dfaEditor.getOffsetX(),textY+t.getCaptionOffsetY()+ dfaEditor.getOffsetY(),transitionFont,g);

            //-- arrow --
            double ax = s1x+0.3*stateDrawSize;
            double ay = s1y - 0.6*stateDrawSize;
            double arrowAngle = 1.9D;
            drawArrow((int)ax+dfaEditor.getOffsetX(),(int)ay+dfaEditor.getOffsetY(),4,arrowAngle,g);
        }

    }


    private void drawStartArrow(int px, int py, Color c,Graphics2D g)
    {
        double s1x = px-20*getDfaEditor().getZoomfactor();
        double s1y = py;
        
        g.setColor(c);

        g.drawLine(px, py, (int)s1x, (int)s1y);
        drawArrow(px,py, 4,0,g);
    }

    private void drawArrow(int px, int py, double size, double angle, Graphics2D g)
    {
        double p1x = -0.5*size*dfaEditor.getZoomfactor();
        double p1y = -1.1*size*dfaEditor.getZoomfactor();
        double p2x = -0.5*size*dfaEditor.getZoomfactor();
        double p2y = 1.1*size*dfaEditor.getZoomfactor();
        double p3x = 2*size*dfaEditor.getZoomfactor();
        double p3y = 0;

        double t1x = px + turnXbyAngle(p1x,p1y, angle);
        double t1y = py + turnYbyAngle(p1x,p1y, angle);
        double t2x = px + turnXbyAngle(p2x,p2y, angle);
        double t2y = py + turnYbyAngle(p2x,p2y, angle);
        double t3x = px + turnXbyAngle(p3x,p3y, angle);
        double t3y = py + turnYbyAngle(p3x,p3y, angle);



        Polygon s = new Polygon();
        s.addPoint((int)t1x,(int)t1y);
        s.addPoint((int)t2x,(int)t2y);
        s.addPoint((int)t3x,(int)t3y);

        g.fillPolygon(s);
    }

    private double turnXbyAngle(double x, double y, double a)
    {
        return Math.cos(a)*x - Math.sin(a)*y;
    }

    private double turnYbyAngle(double x, double y, double a)
    {
        return Math.sin(a)*x + Math.cos(a)*y;
    }
    private void drawCenteredText(String s, int centerX, int centerY , Font f, Graphics2D g)
    {
         //-- center the string --
         FontMetrics fm   = g.getFontMetrics(f);
         Rectangle2D rect = fm.getStringBounds(s, g);
         int textHeight = (int)(rect.getHeight());
         int textWidth  = (int)(rect.getWidth());
         int textx = centerX  - textWidth/ 2;
         int texty = centerY - textHeight/ 2  + fm.getAscent();

         //-- render text --
         g.drawString(s,textx,texty);
    }

    private Vector<Double> getIntersectionPoint(double fromX, double fromY, double toX, double toY, double distance)
    {
        Vector<Double> v = new Vector<Double>();
        double dx = toX - fromX;
        double dy = toY - fromY;
        double l = calcVectorLength(dx,dy);

        if (l > 0)
        {
            double dnx = dx/l;
            double dny = dy/l;

            v.add(fromX+dnx*distance);
            v.add(fromY+dny*distance);
        }
        return v;
    }

    /**
     * get vector length
     * @param dx
     * @param dy
     * @return length of vector
     */
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

    public int getStateDrawSize() {
        return stateDrawSize;
    }


}

