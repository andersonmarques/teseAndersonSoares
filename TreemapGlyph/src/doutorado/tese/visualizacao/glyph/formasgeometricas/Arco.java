/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;

public class Arco extends FormaGeometrica{

    private double[] xPoints;
    private double[] yPoints;
    private Path2D p;
    private Color cor;
    private String angulo = "1";

    public Arco(Rectangle r,Color cor,String angulo) {
        super(r, "ARCO");
        this.cor = cor;
        this.angulo = angulo;
        montarArco();
    }


      public void paint(Graphics g) {
        double angle; 
        //angle = definirAnglulo(this.angulo);          
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);     
        g2d.setColor(cor);
        Graphics2D g2 = (Graphics2D) g; 
       
        g2d.setPaint(Color.BLACK);
        //Graphics2D g2d2 = (Graphics2D)  g2d.create();
        //g2d2.rotate(Math.PI/4);
     
        g2d.fill(p);  
        //g2d.dispose(); 
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

    
    private void montarArco() {
        int[] points = new int[2];

        Rectangle rect = getBounds();
        points[0] = rect.width;
        points[1] = rect.height;
        verificarRetangulo(points);
        
        double width = points[0] * 0.5;
        double height = points[1] * 0.5;
        
        double halfWidth = width / 2.;
        double halfHeight = height / 2.;
        double innerWidth = width / 4.;
        double innerHeight = height / 4.;

        halfWidth += rect.x ;
        halfHeight += rect.y;
        
        xPoints = new double[8];
        yPoints = new double[8];
        
        xPoints[0] =  width + rect.x;
        yPoints[0] =  rect.y;
        
        xPoints[1] = -width + rect.x;
        yPoints[1] = halfHeight;
        
        xPoints[2] = width + rect.x;
        yPoints[2] = height + rect.y;
        
        xPoints[3] = halfWidth + innerWidth; 
        yPoints[3] = halfHeight + innerHeight;
        
        xPoints[4] = rect.x -innerWidth;
        yPoints[4] = halfHeight;
        
        xPoints[5] = halfWidth + innerWidth; 
        yPoints[5] = halfHeight - innerHeight;       
        
        p = new Path2D.Double();
  
        p.moveTo(xPoints[0], yPoints[0]);
        p.quadTo(xPoints[1], yPoints[1],xPoints[2], yPoints[2]); 
        p.lineTo(xPoints[3],yPoints[3]);
        p.quadTo(xPoints[4],yPoints[4] ,xPoints[5],yPoints[5] );
        p.closePath();
        
       // p.translate(rect.x+ width/2.f,rect.y+height/2.f);
       // p.translate(-rect.x-width/2.f,-rect.y -height/2.f);
       
        p.transform(AffineTransform.getTranslateInstance(rect.width/2-width/2,rect.height/2-height/2));
    }
    
    public double definirAnglulo(String angle){
        double newAngle = 0;
        switch(angle){
            case "1":
                newAngle = Math.PI;
                break;
            case "2":
                newAngle = 0;
                break;
            case "3":
                newAngle = Math.PI/2;
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
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    @Override
    public int getArea() {
        return (int)(xPoints[1]*yPoints[1]);
    }
}