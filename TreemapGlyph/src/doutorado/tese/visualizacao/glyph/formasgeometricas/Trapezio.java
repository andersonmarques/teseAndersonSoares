/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;

//trapezio descendente
public class Trapezio {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private int[] xy;

    public Trapezio(Rectangle r) {
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
        montarTrapezio();
        g2d.drawPolygon(xPoints, yPoints, xPoints.length);
    }

    private void montarTrapezio() {
        
        //porcetagem do tamnaho
        int width = (int) ((int) Math.round(rect.width) - rect.width *0.62);
        int height = (int) ((int) Math.round(rect.height) - rect.height *0.62);

        xy = new int [2];
        xy[0] = rect.x;
        xy[1] = 13 ;
        
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 4;
        int innerHeight = height / 4;

        halfWidth += rect.x;
        halfHeight += rect.y;

        xPoints = new int[4];
        yPoints = new int[4];

        xPoints[0] = halfWidth+innerWidth;
        yPoints[0] = (int) Math.round(rect.y);

        xPoints[1] = halfWidth-innerWidth;
        yPoints[1] =  (int) Math.round(rect.y);

        xPoints[2] = (int) Math.round(rect.x);
        yPoints[2] = height + (int) Math.round(rect.y);

        xPoints[3] = width + (int) Math.round(rect.x);
        yPoints[3] = height + (int) Math.round(rect.y);

        
     

    }
}
