/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 *
 * @author Anderson
 */
public class Estrela{

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private int[] xy;

    public Estrela(Rectangle r) {
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(Color.BLACK);
        montarEstrela();        
        
        Polygon p = new Polygon();
        
        p.addPoint(xPoints[0], yPoints[0]);
        p.addPoint(xPoints[1], yPoints[1]);
        p.addPoint(xPoints[2], yPoints[2]);
        p.addPoint(xPoints[3], yPoints[3]);
        p.addPoint(xPoints[4], yPoints[4]);
        p.addPoint(xPoints[5], yPoints[5]);
        p.addPoint(xPoints[6], yPoints[6]);
        p.addPoint(xPoints[7], yPoints[7]);
        p.addPoint(xPoints[8], yPoints[8]);

        
        g2d.setColor(Color.white);
        g2d.fillPolygon(p);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(p);      
    }

    private void montarEstrela() {
        int width = (int) ((int) Math.round(rect.width)*0.6);
        int height = (int) ((int) Math.round(rect.height)*0.6);


//        xy = new int[2];
//        xy[0] =  (int) (Math.round(rect.width)+ rect.width/2+ width *0.6)/10;
//        xy[1] =  (int) (Math.round(rect.height) +rect.height/2 + height* 0.6) /10;
//        
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 8;
        int innerHeight = height / 8;

        halfWidth += rect.x + rect.width/2 - width/2;
        halfHeight += rect.y + rect.height/2 - height/2;

        xPoints = new int[9];
        yPoints = new int[9];

        xPoints[0] = halfWidth;
        yPoints[0] = (int) Math.round(rect.y + rect.height/2 - height/2);

        xPoints[1] = halfWidth - innerWidth;
        yPoints[1] = halfHeight - innerHeight;

        xPoints[2] = (int) Math.round(rect.x + rect.width/2 - width/2);
        yPoints[2] = halfHeight;

        xPoints[3] = halfWidth - innerWidth;
        yPoints[3] = halfHeight + innerHeight;

        xPoints[4] = halfWidth;
        yPoints[4] = height + (int) Math.round(rect.y + rect.height/2 - height/2);

        xPoints[5] = halfWidth + innerWidth;
        yPoints[5] = halfHeight + innerHeight;

        xPoints[6] = width + (int) Math.round(rect.x + rect.width/2 - width/2);
        yPoints[6] = halfHeight;

        xPoints[7] = halfWidth + innerWidth;
        yPoints[7] = halfHeight - innerHeight;

        xPoints[8] = halfWidth;
        yPoints[8] = (int) Math.round(rect.y + rect.height/2 - height/2);

    }
}
