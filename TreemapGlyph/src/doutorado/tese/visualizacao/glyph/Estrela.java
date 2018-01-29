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

/**
 *
 * @author Anderson
 */
public class Estrela extends JComponent {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;

    public Estrela(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(Color.BLACK);
        montarEstrela();
        g2d.drawPolygon(xPoints, yPoints, xPoints.length);
    }

    public void montarEstrela() {
//        int width = (int) Math.round(rect.width) - 1;
//        int height = (int) Math.round(rect.height) - 1;
//
//        int halfWidth = width / 2;
//        int halfHeight = height / 2;
//        int innerWidth = width / 8;
//        int innerHeight = height / 8;
//
//        halfWidth += rect.x;
//        halfHeight += rect.y;
//
//        xPoints = new int[9];
//        yPoints = new int[9];
//
//        xPoints[0] = halfWidth;
//        yPoints[0] = (int) Math.round(rect.y);
//
//        xPoints[1] = halfWidth - innerWidth;
//        yPoints[1] = halfHeight - innerHeight;
//
//        xPoints[2] = (int) Math.round(rect.x);
//        yPoints[2] = halfHeight;
//
//        xPoints[3] = halfWidth - innerWidth;
//        yPoints[3] = halfHeight + innerHeight;
//
//        xPoints[4] = halfWidth;
//        yPoints[4] = height + (int) Math.round(rect.y);
//
//        xPoints[5] = halfWidth + innerWidth;
//        yPoints[5] = halfHeight + innerHeight;
//
//        xPoints[6] = width + (int) Math.round(rect.x);
//        yPoints[6] = halfHeight;
//
//        xPoints[7] = halfWidth + innerWidth;
//        yPoints[7] = halfHeight - innerHeight;
//
//        xPoints[8] = halfWidth;
//        yPoints[8] = (int) Math.round(rect.y);
        int width = (int) Math.round(rect.width) - 1;
        int height = (int) Math.round(rect.height) - 1;

        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 8;
        int innerHeight = height / 8;

        xPoints = new int[9];
        yPoints = new int[9];

        xPoints[0] = halfWidth;
        yPoints[0] = 0;

        xPoints[1] = halfWidth - innerWidth;
        yPoints[1] = halfHeight - innerHeight;

        xPoints[2] = 0;
        yPoints[2] = halfHeight;

        xPoints[3] = halfWidth - innerWidth;
        yPoints[3] = halfHeight + innerHeight;

        xPoints[4] = halfWidth;
        yPoints[4] = height;

        xPoints[5] = halfWidth + innerWidth;
        yPoints[5] = halfHeight + innerHeight;

        xPoints[6] = width;
        yPoints[6] = halfHeight;

        xPoints[7] = halfWidth + innerWidth;
        yPoints[7] = halfHeight - innerHeight;

        xPoints[8] = halfWidth;
        yPoints[8] = 0;
    }
}
