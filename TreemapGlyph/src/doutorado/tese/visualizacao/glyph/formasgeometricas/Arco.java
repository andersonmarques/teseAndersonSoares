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
    private Rectangle rect;
    private double width;
    private double height;

    private double[] xPoints;
    private double[] yPoints;
    private Path2D p;
    private Color cor;
    private String nomeAngulo;
    private double angle;

    public Arco(Rectangle r, String angulo) {
        super(r, "ARCO");
        this.nomeAngulo = angulo;
//        System.out.println("construtor: "+this.nomeAngulo);
        montarArco();
    }


      public void paint(Graphics g) {   
//        System.out.println("paint: "+ nomeAngulo);
        
//        System.out.println("eeeeeeeeeeeee agora:"+definirAnglulo(nomeAngulo));

        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);     
        g2d.setColor(getCor());
        Graphics2D g2 = (Graphics2D) g; 
       
        Graphics2D g2d2 = (Graphics2D)  g2d.create();

        Rectangle bounds = getBounds();   
        g2d2.transform(AffineTransform.getTranslateInstance(bounds.x + Math.round(bounds.width/2.f),bounds.y+Math.round(bounds.height/2.f)));
        p.transform(AffineTransform.getTranslateInstance(-bounds.x - Math.round(bounds.width/2.f),-bounds.y-Math.round(bounds.height/2.f)));
        g2d2.rotate(angle);
//        System.out.println("-----------angulo: "+ nomeAngulo);
        g2d2.setStroke(new BasicStroke(2.8f));
        g2d2.setColor(Color.BLACK);
        g2d2.draw(p);
        g2d2.setColor(cor);
        g2d2.fill(p);  
        g2d2.dispose(); 
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

        setRect(getBounds());
        points[0] = rect.width;
        points[1] = rect.height;
        verificarRetangulo(points);
        
        
        setWidth(points[0] * 0.4);
        setHeight(points[1] * 0.4);
        
        double halfWidth = width / 2;
        double halfHeight = height / 2;
        double innerWidth = width / 4;
        double innerHeight = height / 5;
        double innerWidth2 = width / 6;
        double innerHeight2 = height / 6;

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
        yPoints[3] = halfHeight + 2*innerHeight2;
        
        xPoints[4] = rect.x -innerWidth/2;
        yPoints[4] = halfHeight;
        
        xPoints[5] = halfWidth + innerWidth; 
        yPoints[5] = halfHeight - 2*innerHeight2;       
        
        p = new Path2D.Double();
  
        p.moveTo(xPoints[0], yPoints[0]);
        p.quadTo(xPoints[1], yPoints[1],xPoints[2], yPoints[2]); 
        p.lineTo(xPoints[3],yPoints[3]);
        p.quadTo(xPoints[4],yPoints[4] ,xPoints[5],yPoints[5] );
        p.closePath();
        
        p.transform(AffineTransform.getTranslateInstance(rect.width/2-width/2,rect.height/2-height/2));
    
        angle = definirAnglulo(nomeAngulo);
    }
    
    public double definirAnglulo(String angulo){
        double newAngle = 0;
        System.out.println("definir angulo: "+angulo);
        switch(angulo){
            case "1":
                newAngle = Math.PI;
                cor = Color.decode("#756bb1");//roxo
                break;
            case "2":
                newAngle = 0; 
                cor = Color.decode("#7FFFD4");//azul claro
                break;
            case "3":
                newAngle = Math.PI/2;
                cor = Color.decode("#31a354");//verde
                break;
             case "4":
                newAngle =  -Math.PI/2;
                cor = Color.decode("#cecece");//azul escuro
                break;
            case "5":
                newAngle =  Math.PI/4;                
                cor = Color.decode("#e377c2");//rosa

                break;
            case "6":
                newAngle = -Math.PI/4;
                cor = Color.decode("#d62728");//vermelho
                break;
            case "7":
                newAngle =  5*Math.PI/6;
                cor = Color.decode("#FFD700");//dourado
                break;
            case "8":
                newAngle =  -3*Math.PI/4;
                cor = Color.decode("#8c564b");//marrom
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

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setAngulo(String angulo) {
        this.nomeAngulo = angulo;
    }

    public String getAngulo() {
        return nomeAngulo;
    }
    
    
    @Override
    public int getArea() {
        return (int)(xPoints[1]*yPoints[1]);
    }
}