/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;


public class Losango{

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;

    public Losango(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
    }
    
    public void setBounds(Rectangle rect){
        this.rect = rect;
    }
    
    public Rectangle getBounds(){
        return rect;
    }

    public void paint(Graphics g) {
//        this.setOpaque(false);
//        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(Color.BLACK);
        montarLosango();
        g2d.drawPolygon(xPoints, yPoints, xPoints.length);
    }

    private void montarLosango() {
        int width = (int) ((int) Math.round(rect.width) - rect.width *0.5);
        int height = (int) ((int) Math.round(rect.height) - rect.height *0.5);


        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 2;
        int innerHeight = height /2;

        halfWidth += rect.x;
        halfHeight += rect.y;

        xPoints = new int[4];
        yPoints = new int[4];

        xPoints[0] = halfWidth;
        yPoints[0] = (int) Math.round(rect.y);

        xPoints[1] = halfWidth-innerWidth;
        yPoints[1] = halfHeight;

        xPoints[2] = halfWidth;
        yPoints[2] = height + (int) Math.round(rect.y);

        xPoints[3] = halfWidth + innerWidth;
        yPoints[3] = halfHeight;

        

     

    }
}
