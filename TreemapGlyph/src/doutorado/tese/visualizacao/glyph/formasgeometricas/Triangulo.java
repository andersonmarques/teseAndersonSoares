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

public class Triangulo extends FormaGeometrica{

    private int[] xPoints;
    private int[] yPoints;
    private Polygon p;
    private Color cor;
   

    public Triangulo(Rectangle r,Color cor) {
        super(r, "Triangulo");
        this.cor = cor;
        montarTriangulo();
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

    
    private int[] verificarRetangulo(int [] point){
        if(point[0] > point[1]){
            point[0] = point[1];
           return point;
        }
        else if(point[0] < point[1]){
            point[1] = point[0];
           return point;
        }
        return null;
    }
    
    private void montarTriangulo() {
        int[] points = new int[2];

        Rectangle rect = getBounds();
        
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = (int) Math.round(points[0] * 0.2);
        int height = (int) Math.round(points[1] * 0.2);

        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 4;
        int innerHeight = height / 4;

        halfWidth += rect.x;
        halfHeight += rect.y;

       
        xPoints = new int[3];
        yPoints = new int[3];

        xPoints[0] = halfWidth;
        yPoints[0] = (int) Math.round(rect.y);

        xPoints[1] = (int) Math.round(rect.x);
        yPoints[1] = height + (int) Math.round(rect.y);

        xPoints[2] = width + (int) Math.round(rect.x);
        yPoints[2] = height + (int) Math.round(rect.y);        
        p = new Polygon();

        p.addPoint(xPoints[0], yPoints[0]);
        p.addPoint(xPoints[1], yPoints[1]);
        p.addPoint(xPoints[2], yPoints[2]);   
        
        p.translate(0+width/2,rect.height-2*height);
 
    }

    @Override
    public int getArea() {
        return (xPoints[1]-xPoints[1])*(yPoints[2]-yPoints[2]);
    }
}
