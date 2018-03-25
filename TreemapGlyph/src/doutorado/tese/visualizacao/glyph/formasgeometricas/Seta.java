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
import java.awt.geom.Rectangle2D;

public class Seta extends FormaGeometrica{

   private String angle;
    private int[] xPoints;
    private int[] yPoints;
    private Polygon p;
    private Color c;
    private float tam;
 
    public Seta(Rectangle r,String angle) {
        super(r, "SETA");
        this.angle = angle;
        montarSeta();
    }
    
    public void paint(Graphics g) {
        double newAngle;
        newAngle = definirAnglulo(angle);
        System.out.println(newAngle);
        System.out.println(angle);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        int [] center = new int[2];

        Graphics2D g2d2 = (Graphics2D)  g2d.create();
        Rectangle bounds = p.getBounds();   
        g2d2.translate(bounds.x+ Math.round(bounds.width/2.f),bounds.y+Math.round(bounds.height/2.f));
        p.translate(-bounds.x-Math.round(bounds.width/2.f),-bounds.y -Math.round(bounds.height/2.f));
        g2d2.setColor(Color.WHITE);
        g2d2.rotate(newAngle);
        g2d2.drawPolygon(p);
        g2d2.setColor(getCor());
        g2d2.fillPolygon(p);      
        

        g2d2.dispose();       
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
    
    private void montarSeta() {
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

        halfWidth += rect.x + rect.width/2 - width/2;
        halfHeight += rect.y + rect.height/2 - height/2;

       
        xPoints = new int[7];
        yPoints = new int[7];

        xPoints[0] = halfWidth;
        yPoints[0] = (int) Math.round(rect.y + rect.height/2 - height/2);

        xPoints[1] = halfWidth;
        yPoints[1] = halfHeight - innerHeight;

        xPoints[2] = halfWidth-2*innerWidth;
        yPoints[2] = halfHeight - innerHeight;

        xPoints[3] = halfWidth- 2*innerWidth;
        yPoints[3] = halfHeight+innerHeight;

        xPoints[4] = halfWidth;
        yPoints[4] = halfHeight+innerHeight;

        xPoints[5] = halfWidth;
        yPoints[5] = height+(int) Math.round(rect.y + rect.height/2 - height/2);
        
        xPoints[6] = halfWidth+2*innerHeight;
        yPoints[6] = halfHeight;
        
        p = new Polygon(); 

        
        p.addPoint(xPoints[0], yPoints[0]);
        p.addPoint(xPoints[1], yPoints[1]);
        p.addPoint(xPoints[2], yPoints[2]);
        p.addPoint(xPoints[3], yPoints[3]);
        p.addPoint(xPoints[4], yPoints[4]);
        p.addPoint(xPoints[5], yPoints[5]);
        p.addPoint(xPoints[6], yPoints[6]);

    }
    
    public double definirAnglulo(String angle){
        double newAngle = 0;
        switch(angle){
            case "1":
                newAngle = Math.PI;
                c = Color.BLACK;
                break;
            case "2":
                newAngle = 0;
                 c = Color.BLUE;
                break;
            case "3":
                newAngle = Math.PI/2;
                 c = Color.YELLOW;
                break;
             case "4":
                newAngle =  -Math.PI/2;
                break;
            case "5":
                newAngle =  Math.PI/4;
                break;
            case "6":
                newAngle = -Math.PI/4;
                break;
            case "7":
                newAngle =  5*Math.PI/6;
                break;
            case "8":
                newAngle =  -3*Math.PI/4;
                break;              
            default:
                break;
        }
        return newAngle;
    }

    public Color getCor() {
        return c;
    }

    public void setCor(Color c) {
        this.c = c;
    }
    

    @Override
    public int getArea() {
        return (xPoints[5]-xPoints[1])*(yPoints[3]-yPoints[0]);
    }
}
