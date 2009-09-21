package gui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import controller.DFAPainter;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Fabian
 */
public class PaintPanel extends JPanel {

DFAPainter dFAPainter;



    public DFAPainter getdFAPainter() {
        return dFAPainter;
    }

    public void setdFAPainter(DFAPainter dFAPainter) {
        this.dFAPainter = dFAPainter;
    }


@Override public void paint(Graphics g) {
         super.paintComponent(g);    // paints background
         if (dFAPainter != null)
             dFAPainter.updaterGraphics();

}

}
