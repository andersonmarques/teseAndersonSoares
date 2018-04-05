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

public class Pentagono extends FormaGeometrica {

    private int[] xPoints;
    private int[] yPoints;
    private Polygon p;
    private Color cor;
    private float tam;
    private String position;

    public Pentagono(Rectangle r, Color cor, float tam, String position) {
        super(r, "PENTAGONO");
        this.cor = cor;
        this.tam = tam;
        this.position = position;
        montarPentagono();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        g2d.setColor(cor);
        g2d.fillPolygon(p);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(p);

    }

    private int[] verificarRetangulo(int[] point) {
        if (point[0] > point[1]) {
            point[0] = point[1];
            return point;
        } else if (point[0] < point[1]) {
            point[1] = point[0];
            return point;
        }
        return null;
    }

    private void montarPentagono() {
        int[] points = new int[2];

        Rectangle rect = getBounds();

        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);
        int innerRectX = (int) Math.round(points[0] * 0.7);
        int innerRectY = (int) Math.round(points[0] * 0.7);

        int width = (int) (innerRectX * tam);
        int height = (int) (innerRectY * tam);

        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 4;
        int innerHeight = height / 4;

        halfWidth += rect.x;
        halfHeight += rect.y;

        xPoints = new int[5];
        yPoints = new int[5];

        xPoints[0] = halfWidth;
        yPoints[0] = (int) Math.round(rect.y);

        xPoints[1] = (int) Math.round(rect.x);
        yPoints[1] = halfHeight;

        xPoints[2] = halfWidth - innerWidth;
        yPoints[2] = height + (int) Math.round(rect.y);

        xPoints[3] = halfWidth + innerWidth;
        yPoints[3] = height + (int) Math.round(rect.y);

        xPoints[4] = width + (int) Math.round(rect.x);
        yPoints[4] = halfHeight;
        p = new Polygon();

        p.addPoint(xPoints[0], yPoints[0]);
        p.addPoint(xPoints[1], yPoints[1]);
        p.addPoint(xPoints[2], yPoints[2]);
        p.addPoint(xPoints[3], yPoints[3]);
        p.addPoint(xPoints[4], yPoints[4]);

        int[] result = definirPosicao(rect, innerRectX, innerRectY, width, height, position);
        p.translate(result[0], result[1]);

    }

    private int[] definirPosicao(Rectangle rect, int innerRectX, int innerRectY, int width, int height, String position) {
        int[] result = new int[2];
        switch(position){
            case "1":
                result[0] = rect.width/2-innerRectX/2;
                result[1] = rect.height/2 -innerRectX/2;
                return result;
            case "2":
                result[0] = rect.width/2-innerRectX/2;
                result[1] = rect.height/2+innerRectX/2 - height;
                return result;
            case "3":
                result[0] =  rect.width/2+innerRectX/2-width;
                result[1] =  rect.height/2+innerRectX/2 - height;
                break;
            case "4":
                result[0] = rect.width/2+innerRectX/2-width ;
                result[1] = rect.height/2 -innerRectX/2;               
                break;
            case "5":
                result[0] = rect.width/2-height/2;
                result[1] = rect.height/2-height/2;
                return result;
                
        }
        return result;
    }

    @Override
    public int getArea() {
        return (xPoints[4] - xPoints[1]) * (yPoints[3] - yPoints[0]);
    }
}
