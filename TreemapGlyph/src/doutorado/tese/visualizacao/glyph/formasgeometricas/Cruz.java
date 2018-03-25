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
import java.awt.geom.AffineTransform;

public class Cruz extends FormaGeometrica{

    private int[] xPoints;
    private int[] yPoints;
    private Polygon p;
    private Color cor;
    private float tam;
    private String position;


    public Cruz(Rectangle r,Color cor,float tam,String position) {
        super(r, "CRUZ");
        this.cor = cor;
        this.tam = tam;
        this.position= position;
        montarCruz();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        g2d.setColor(cor);
        g2d.fillPolygon(p);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(p);
    }
    
        //função para deixar os glyphs quadrados
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


    private void montarCruz() {
        
        int[] points = new int[2];
        
        Rectangle rect = getBounds();

        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = (int) Math.round(points[0] * tam);
        int height = (int) Math.round(points[1] * tam);

      
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int innerWidth = width / 4;
        int innerHeight = height / 4;

        halfWidth += rect.x ;
        halfHeight += rect.y ;
        
        
        xPoints = new int[12];
        yPoints = new int[12];

        xPoints[0] = halfWidth - innerWidth;
        yPoints[0] = (int) Math.round(rect.y);

        xPoints[1] = halfWidth - innerWidth;
        yPoints[1] = halfHeight - innerHeight;

        xPoints[2] = (int) Math.round(rect.x);
        yPoints[2] = halfHeight - innerHeight;

        xPoints[3] = (int) Math.round(rect.x);
        yPoints[3] = halfHeight + innerHeight;

        xPoints[4] = halfWidth - innerWidth;
        yPoints[4] = halfHeight + innerHeight;

        xPoints[5] = halfWidth - innerWidth;
        yPoints[5] = height + (int) Math.round(rect.y);

        xPoints[6] = halfWidth + innerWidth;
        yPoints[6] = height + (int) Math.round(rect.y );

        xPoints[7] = halfWidth + innerWidth;
        yPoints[7] = halfHeight + innerHeight;

        xPoints[8] = width + (int) Math.round(rect.x );
        yPoints[8] = halfHeight + innerHeight;

        xPoints[9] = width + (int) Math.round(rect.x );
        yPoints[9] = halfHeight - innerHeight;

        xPoints[10] = halfWidth + innerWidth;
        yPoints[10] = halfHeight - innerHeight;

        xPoints[11] = halfWidth + innerWidth;
        yPoints[11] = (int) Math.round(rect.y );
        
        
        p = new Polygon();

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
        
        int [] result = definirPosicao(rect,width,height,position); 
        p.translate(result[0],result[1]);

    }
    
        private int[] definirPosicao(Rectangle rect,int width,int height,String position){
        int [] result = new int[2];
        switch(position){
            case "1":
                result[0] = rect.width/2-rect.width/4-width/2;
                result[1] = rect.height/4 -height/2;
                return result;
            case "2":
                result[0] = rect.width/2-rect.width/4-width/2;
                result[1] = rect.height-2*height;
                return result;
            case "3":
                result[0] = rect.width-rect.width/4-width;
                result[1] = rect.height-2*height;
                break;
            case "4":
                result[0] = rect.width-rect.width/4-width;
                result[1] = rect.height/4 -height/2;                
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
        return (xPoints[8]-xPoints[2])*(yPoints[5]-yPoints[0]);
    }
}
