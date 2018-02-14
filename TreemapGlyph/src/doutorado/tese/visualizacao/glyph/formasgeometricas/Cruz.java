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

public class Cruz {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private int[] xy;

    public Cruz(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
    }

    public void setBounds(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getBounds() {
        return rect;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        montarCruz();

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
        p.addPoint(xPoints[9], yPoints[9]);
        p.addPoint(xPoints[10], yPoints[10]);
        p.addPoint(xPoints[11], yPoints[11]);

        //p.translate(xy[0], xy[1]);

        g2d.setColor(Color.white);
        g2d.fillPolygon(p);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(p);
    }

    private void montarCruz() {
        int width = (int) ((int) Math.round(rect.width) * 0.5);
        int height = (int) ((int) Math.round(rect.height) * 0.5);

//        xy = new int[2];
//        xy[0] = (int) (Math.round(rect.width) + rect.width / 2 + width * 0.6) / 10;
//        xy[1] = (int) (Math.round(rect.height) + rect.height / 2 + height * 0.6) / 10;

        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 4;
        int innerHeight = height / 4;

        halfWidth += rect.x + rect.width/2 - width/2;
        halfHeight += rect.y + rect.height/2 - height/2;
        
        
        xPoints = new int[12];
        yPoints = new int[12];

        xPoints[0] = halfWidth - innerWidth;
        yPoints[0] = (int) Math.round(rect.y + rect.height/2 - height/2);

        xPoints[1] = halfWidth - innerWidth;
        yPoints[1] = halfHeight - innerHeight;

        xPoints[2] = (int) Math.round(rect.x + rect.width/2 - width/2);
        yPoints[2] = halfHeight - innerHeight;

        xPoints[3] = (int) Math.round(rect.x + rect.width/2 - width/2);
        yPoints[3] = halfHeight + innerHeight;

        xPoints[4] = halfWidth - innerWidth;
        yPoints[4] = halfHeight + innerHeight;

        xPoints[5] = halfWidth - innerWidth;
        yPoints[5] = height + (int) Math.round(rect.y + rect.height/2 - height/2);

        xPoints[6] = halfWidth + innerWidth;
        yPoints[6] = height + (int) Math.round(rect.y + rect.height/2 - height/2);

        xPoints[7] = halfWidth + innerWidth;
        yPoints[7] = halfHeight + innerHeight;

        xPoints[8] = width + (int) Math.round(rect.x + rect.width/2 - width/2);
        yPoints[8] = halfHeight + innerHeight;

        xPoints[9] = width + (int) Math.round(rect.x + rect.width/2 - width/2);
        yPoints[9] = halfHeight - innerHeight;

        xPoints[10] = halfWidth + innerWidth;
        yPoints[10] = halfHeight - innerHeight;

        xPoints[11] = halfWidth + innerWidth;
        yPoints[11] = (int) Math.round(rect.y + rect.height/2 - height/2);

    }
}
