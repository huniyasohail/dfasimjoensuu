
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
import models.EditorToolStates;
import models.HighlightTypes;
import models.NoSuchTransitionException;
import models.State;
import models.TouchButton;
import models.Transition;


/**
 *
 * @author Fabian
 */
public class DFAPainter {

    private final Color colorStateHighlight = new Color(197,222,255);
    private final Color colorStateSelected = new Color(0,0,255);
    private final Color colorStateNormal = Color.white;
    private final Color colorStateFontNormal = Color.black;
    private final Color colorStateFontSelected = Color.white;
    private final Color colorStateLineSeleced = Color.white;

    private final Color colorStateLinesNotSelected = Color.black;
    private final Color colorStateLinesSelected = Color.blue;
    private final Color colorStateLinesCurrent = Color.red;

    private final Color colorTransitionLineNormal = Color.BLACK;
    private final Color colorTransitionLineHighlighted = new Color(73,137,255);
    private final Color colorTransitionLineSelected = Color.blue;
    private final Color colorTransitionLabelHighlighted = new Color(197,222,255);
    private final Color colorTransitionLabelSelected = Color.blue;
    private final Color colorTransitionFontNormal = Color.black;
    private final Color colorTransitionFontSelected = Color.white;

    private final Color colorAddNewElement = new Color(130,130,130);
    private final Color colorAddNewElement2 = new Color(90,90,90);

    private final int stateDrawSize = 50;
    private final int textSize = 16;
    private final double arcDistance = 25;
    private final int virtualTransitionSite = 20;

    private boolean antialiasing = true;

    private Simulator dfaSim = null;
    private DfaEditor dfaEditor = null;
    private Graphics2D graphics = null;
    private PaintPanel paintPanel = null;
    private Font transitionFont = null;

    public DFAPainter(Simulator dfaSimulator) {
        this.dfaSim = dfaSimulator;
    }

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

    public int getVirtualTransitionSite() {
        return virtualTransitionSite;
    }

    public void requestRepaintAll()
    {
        if (this.paintPanel != null)
            this.paintPanel.repaint();
    }

    /**
     * paints the States and Transitions
     */
    public void updateGraphics(Graphics2D g)
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
            paintUserActions();
                   
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
         Font nameFont = new Font("Arial",Font.PLAIN, (int)(textSize*dfaEditor.getZoomfactor()));
         g.setFont(nameFont);

        for (int i=0; i < dfa.getStates().size(); i++)
        {
            State s = dfa.getStates().get(i);
            int x = s.getState_Properties().getXPos();
            int y = s.getState_Properties().getYPos();

            Color backgroundColor = colorStateNormal;
            Color lineColor = colorStateLinesNotSelected;
            Color fontColor = colorStateFontNormal;
            
            
            if (s.getState_Properties().getHighlightIndex() == HighlightTypes.MouseOver)
            {
                 backgroundColor = colorStateHighlight;
            }

            if (s.getState_Properties().isSelected())
            {
                 backgroundColor = colorStateSelected;
                 lineColor = colorStateLinesSelected;
                 fontColor = colorStateFontSelected;
            }

            if(dfaSim.getIsRunning() && dfa.getCurrentState().equals(s)) {
                lineColor = colorStateLinesCurrent;
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
                if (s.getState_Properties().isSelected())
                    g.setColor(colorStateLineSeleced);
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

        g.setColor(fontColor);
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
         transitionFont = new Font("Arial", Font.PLAIN, (int)(0.9*textSize*dfaEditor.getZoomfactor()));
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

    public void paintTransition(State s1, State s2, Transition t, String caption, Color color, boolean fakeTrans) {
        if (s1 != null && s2 != null) {
            int captionPositionX = 0;
            int captionPositionY = 0;
            Color colorCaptionColor = Color.white;
            Color colorLineColor = colorTransitionLineNormal;
            Color colorFont = colorTransitionFontNormal;

            if (t.getHighlightStatus() == HighlightTypes.MouseOver) {
                colorCaptionColor = colorTransitionLabelHighlighted;
                colorLineColor = colorTransitionLineHighlighted;
            }

            if (t.isSelected()) {
                colorCaptionColor = colorTransitionLabelSelected;
                colorLineColor = colorTransitionLineSelected;
                colorFont = colorTransitionFontSelected;
            }

            if (fakeTrans) {
                colorCaptionColor = Color.white;
                colorLineColor = color;
                colorFont = Color.white;
            }

            boolean showTouchButton = (getDfaEditor().getToolState() == EditorToolStates.handTool) && t.isSelected();

            boolean paintLabelBackground = t.isSelected() || (t.getHighlightStatus() != HighlightTypes.NoHighlight || fakeTrans);

            Graphics2D g = this.graphics;
            int s1x = s1.getState_Properties().getXPos();
            int s1y = s1.getState_Properties().getYPos();

            int s2x = s2.getState_Properties().getXPos();
            int s2y = s2.getState_Properties().getYPos();

            g.setColor(color);

            //-- arc case or linear --
            boolean isBidirectional = false;
            if (!fakeTrans) {
                try {
                    isBidirectional = dfaEditor.getDfa().isBidirectionalTransition(s1, s2);
                } catch (NoSuchTransitionException ex) {
                    //TODO
                    System.out.println(ex.getMessage());
                }
            } else {
                isBidirectional = true;
            }

            if (s1 != s2 && isBidirectional) {
                //-- get control point --
                int dx = s2x - s1x;
                int dy = s2y - s1y;
                double vlength = calcVectorLength(dx, dy);

                if (vlength > 0) {
                    QuadCurve2D c = new QuadCurve2D.Double();

                    double centerx = (s2x + s1x) / 2;
                    double centery = (s2y + s1y) / 2;

                    double normx = dx / vlength;
                    double normy = dy / vlength;

                    double additionalArcDistance = vlength / 100;
                    //-- turn vector 90 degrees --
                    double turnedx = arcDistance * normy * additionalArcDistance * t.getCurveFactor();
                    double turnedy = -arcDistance * normx * additionalArcDistance * t.getCurveFactor();

                    int cpointx = (int) (centerx + turnedx);
                    int cpointy = (int) (centery + turnedy);

                    double textAdaption = 35;
                    double absCurveFactor = Math.abs(t.getCurveFactor());
                    double direction = 1;
                     if (t.getCurveFactor() < 0)
                    {
                        direction = -1;
                        textAdaption = 35;
                    }
                    if (absCurveFactor < 2)
                    {
                        textAdaption =  Math.max(20, textAdaption*curveAdoptionFactor(absCurveFactor));
                    }
                    textAdaption = textAdaption*direction;
                    //System.out.println(t.getCurveFactor());

                    int textpointx = (int) (centerx + turnedx / (2) + normy * textAdaption);
                    int textpointy = (int) (centery + turnedy / (2) - normx * textAdaption);

                    //-- tangential crossing with the circles (start and end of curve) --
                    Vector<Double> p1 = getIntersectionPoint(s1x, s1y, cpointx, cpointy, 1.2 * stateDrawSize / 2);
                    Vector<Double> p2 = getIntersectionPoint(s2x, s2y, cpointx, cpointy, 1.4 * stateDrawSize / 2);

                    int h1x = (int) Math.round(p1.get(0)) + dfaEditor.getOffsetX();
                    int h1y = (int) Math.round(p1.get(1)) + dfaEditor.getOffsetY();

                    int h2x = (int) Math.round(p2.get(0)) + dfaEditor.getOffsetX();
                    int h2y = (int) Math.round(p2.get(1)) + dfaEditor.getOffsetY();

                    g.setColor(colorLineColor);
                    //-- quadratic arc --
                    c.setCurve(h1x, h1y,
                            cpointx + dfaEditor.getOffsetX(), cpointy + dfaEditor.getOffsetY(),
                            h2x, h2y);
                    g.draw(c);
                    //-- draw text --
                    if (paintLabelBackground) {
                        Rectangle2D fbounds = getFontBounds(caption, textpointx + t.getCaptionOffsetX() + dfaEditor.getOffsetX(), textpointy + t.getCaptionOffsetY() + dfaEditor.getOffsetY(), transitionFont, g);
                        paintTransitionHighlightRectangle(fbounds, colorCaptionColor, (int) (4 * getDfaEditor().getZoomfactor()), g);
                    }

                    //-- arrow --
                    double ax = h2x - cpointx - dfaEditor.getOffsetX();
                    double ay = h2y - cpointy - dfaEditor.getOffsetY();
                    double arrowAngle = Math.atan2(ay, ax);
                    g.setColor(colorLineColor);
                    drawArrow(h2x, h2y, 4, arrowAngle, g);

                    g.setColor(colorFont);
                    drawCenteredText(caption, textpointx + t.getCaptionOffsetX() + dfaEditor.getOffsetX(), textpointy + t.getCaptionOffsetY() + dfaEditor.getOffsetY(), transitionFont, g);
                    captionPositionX = textpointx;
                    captionPositionY = textpointy;



                    //-- touchup button --

                    if (showTouchButton) {
                        drawTouchTransitionButton(t, normx, normy, additionalArcDistance, h1x, h1y, h2x, h2y);
                    }
                }

            } else if (s1 != s2) {
                //-- linear-case --
                Vector<Double> p1 = getIntersectionPoint(s1x, s1y, s2x, s2y, 1.2 * stateDrawSize / 2);
                Vector<Double> p2 = getIntersectionPoint(s2x, s2y, s1x, s1y, 1.4 * stateDrawSize / 2);
                int h1x = (int) Math.round(p1.get(0)) + dfaEditor.getOffsetX();
                int h1y = (int) Math.round(p1.get(1)) + dfaEditor.getOffsetY();

                int h2x = (int) Math.round(p2.get(0)) + dfaEditor.getOffsetX();
                int h2y = (int) Math.round(p2.get(1)) + dfaEditor.getOffsetY();
                g.setColor(colorLineColor);
                g.drawLine(h1x, h1y, h2x, h2y);

                double ax = s2x - s1x;
                double ay = s2y - s1y;


                double vlength = calcVectorLength(ax, ay);

                if (vlength > 0) {

                    double centerx = (s2x + s1x) / 2;
                    double centery = (s2y + s1y) / 2;

                    double normx = ax / vlength;
                    double normy = ay / vlength;

                    double arrowAngle = Math.atan2(ay, ax);
                    g.setColor(colorLineColor);
                    drawArrow(h2x, h2y, 4, arrowAngle, g);
                    // -- text --
                    int textX = (int) (centerx + 12 * normy * dfaEditor.getZoomfactor());
                    int textY = (int) (centery - 12 * normx * dfaEditor.getZoomfactor());


                    if (paintLabelBackground) {
                        Rectangle2D fbounds = getFontBounds(caption, textX + t.getCaptionOffsetX() + dfaEditor.getOffsetX(), textY + t.getCaptionOffsetY() + dfaEditor.getOffsetY(), transitionFont, g);
                        paintTransitionHighlightRectangle(fbounds, colorCaptionColor, (int) (4 * getDfaEditor().getZoomfactor()), g);
                    }
                    g.setColor(colorFont);
                    drawCenteredText(caption, textX + t.getCaptionOffsetX() + dfaEditor.getOffsetX(), textY + t.getCaptionOffsetY() + dfaEditor.getOffsetY(), transitionFont, g);
                    captionPositionX = textX;
                    captionPositionY = textY;
                }


            } else {

                //-- cirlce to state itself --
                double boxX = s1x - stateDrawSize * 0.3;
                double boxY = s1y - stateDrawSize * 0.95;
                double w = stateDrawSize * 0.6;
                double h = stateDrawSize * 0.6;

                g.setColor(colorLineColor);
                Arc2D arc = new Arc2D.Double(boxX + dfaEditor.getOffsetX(), boxY + dfaEditor.getOffsetY(), w, h, -20, 220, Arc2D.OPEN);
                g.draw(arc);

                // -- text --
                int textX = (int) s1x;
                int textY = (int) (s1y - stateDrawSize * 1.2);
                if (paintLabelBackground) {
                    Rectangle2D fbounds = getFontBounds(caption, textX + t.getCaptionOffsetX() + dfaEditor.getOffsetX(), textY + t.getCaptionOffsetY() + dfaEditor.getOffsetY(), transitionFont, g);
                    paintTransitionHighlightRectangle(fbounds, colorCaptionColor, (int) (4 * getDfaEditor().getZoomfactor()), g);
                }
                g.setColor(colorFont);
                drawCenteredText(caption, textX + t.getCaptionOffsetX() + dfaEditor.getOffsetX(), textY + t.getCaptionOffsetY() + dfaEditor.getOffsetY(), transitionFont, g);
                captionPositionX = textX;
                captionPositionY = textY;

                //-- arrow --
                double ax = s1x + 0.3 * stateDrawSize;
                double ay = s1y - 0.6 * stateDrawSize;
                double arrowAngle = 1.9D;
                g.setColor(colorLineColor);
                drawArrow((int) ax + dfaEditor.getOffsetX(), (int) ay + dfaEditor.getOffsetY(), 4, arrowAngle, g);
            }
            t.setClickPositionX(captionPositionX);
            t.setClickPositionY(captionPositionY);
            g.setColor(Color.black);
        }

    }

    private double curveAdoptionFactor(double input)
    {

        double f = (input-1);
        f = 1/(f*f*f*f+1);
        return 0.7*(input/2)*(input/2)+0.3*f;

    }

    private void drawTouchTransitionButton(Transition t,double normx, double normy, double additionalArcDistance, double h1x, double h1y,double h2x, double h2y)
    {
        TouchButton tb = getDfaEditor().getTouchButton();
        tb.setVisible(true);

        Graphics2D g = this.graphics;

        double centerx = (h2x + h1x)/2;
        double centery = (h2y + h1y)/2;

         //-- turn vector 90 degrees --
        double turnedx = arcDistance*normy*additionalArcDistance*t.getCurveFactor();
        double turnedy = -arcDistance*normx*additionalArcDistance*t.getCurveFactor();

        int cpointx = (int) (centerx + turnedx);
        int cpointy = (int) (centery + turnedy);

        int textpointx = (int) (centerx + turnedx/(2)*0.93*curvePointAdaptionFactor(t.getCurveFactor(),1));
        int textpointy = (int) (centery + turnedy/(2)*0.93*curvePointAdaptionFactor(t.getCurveFactor(),1));

        Color fcolor = tb.getColorNormal();

        if (tb.isSelected())
        {
            fcolor = tb.getColorHightlight();
        }
        if (tb.isMoving())
            fcolor = tb.getColorMove();

        tb.setPx(textpointx);
        tb.setPy(textpointy);

        g.setColor(fcolor);
        g.fillOval(textpointx-tb.getSize(),
                textpointy-tb.getSize(), 2*tb.getSize(), 2*tb.getSize());
         g.setColor(colorTransitionLineSelected);
        g.drawOval(textpointx-tb.getSize(),
                textpointy-tb.getSize(), 2*tb.getSize(), 2*tb.getSize());
    }

private double curvePointAdaptionFactor(double input,double min)
{
    double x = input - min;
    return 1-0.25/(x*x+1);
}



    private void paintTransitionHighlightRectangle(Rectangle2D r, Color c, int additionalBorder, Graphics2D g)
    {
       g.fillRoundRect((int)(r.getX()-additionalBorder), (int)( r.getY()-additionalBorder), (int)(r.getWidth()+2*additionalBorder),(int)( r.getHeight()+2*additionalBorder), 10, 10);
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

    private Rectangle2D getFontBounds(String s,int centerX, int centerY, Font f, Graphics2D g)
    {
         //-- center the string --
         FontMetrics fm   = g.getFontMetrics(f);
         Rectangle2D rect = fm.getStringBounds(s, g);

         int textHeight = (int)(rect.getHeight());
         int textWidth  = (int)(rect.getWidth());
         int textx = centerX  - textWidth/ 2;
         int texty = centerY - textHeight/ 2 ;

         rect.setRect(textx, texty, textWidth, textHeight);

         return rect;
    }


    private Rectangle2D drawCenteredText(String s, int centerX, int centerY , Font f, Graphics2D g)
    {
         //-- center the string --
         FontMetrics fm   = g.getFontMetrics(f);
         Rectangle2D rect = fm.getStringBounds(s, g);
         int textHeight = (int)(rect.getHeight());
         int textWidth  = (int)(rect.getWidth());
         int textx = centerX  - textWidth/ 2;
         int texty = centerY - textHeight/ 2 + fm.getAscent();

         //-- render text --
         g.drawString(s,textx,texty);
         return rect;
    }

    public Vector<Double> getIntersectionPoint(double fromX, double fromY, double toX, double toY, double distance)
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
        } else
        {

            v.add(0D);
            v.add(0D);
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


    private void paintUserActions() {
        if (dfaEditor.getDummyState().getState_Properties().isVisible())
        {
            paintDummyState(dfaEditor.getDummyState());

        }
       paintDummyTransition(dfaEditor.getDummyTransition());

    }


    public void paintDummyState(State s){
        Graphics2D g = this.graphics;

        int radius = (int)(dfaEditor.getZoomfactor()*stateDrawSize/2);
        
        int px = s.getState_Properties().getXPos()-radius + dfaEditor.getOffsetX();
        int py = s.getState_Properties().getYPos()-radius + dfaEditor.getOffsetY();

        g.setColor(colorAddNewElement);
        g.fillOval(px,py,stateDrawSize, stateDrawSize);
        g.setColor(colorAddNewElement2);
        g.drawOval(px,py,stateDrawSize, stateDrawSize);

        Font nameFont = new Font("Arial", Font.PLAIN, (int)(textSize*dfaEditor.getZoomfactor()));
        g.setColor(Color.white);
        drawCenteredText(s.getState_Properties().getName(),px+(int)(radius/1.2) ,py+(int)(radius/1.5),nameFont,g);
        g.setColor(Color.black);
    }

    public void paintDummyTransition(Transition t)
    {
        if (t.isVisible())
        {
            paintTransition(t.getFromState(), t.getToState(), t, "Add transition "+t.getFromState().getState_Properties().getName()+
                    " > "+ t.getToState().getState_Properties().getName(), colorAddNewElement, true);
        }

    }



}

