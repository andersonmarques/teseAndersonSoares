package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Retangulo extends FormaGeometrica{

    private int[] xPoints;
    private int[] yPoints;
    private Color cor;
    private String angulo;

    public Retangulo(Rectangle r,Color cor,String angulo) {
        super(r, "RETANGULO");
       this.cor = cor;
       this.angulo = angulo;
        montarRetangulo();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(cor);

        
        g2d.setColor(cor);
        g2d.fillRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);

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
        
    private int[] definirPosicao(Rectangle rect,int width,int height,String angulo){
         int[] points = new int[2];
         
        switch(angulo){
            case "1":// Math.PI esquerda;
                xPoints[0] =   rect.x + rect.width/2 +width/2 ;
                yPoints[1] =   rect.y + rect.height/2 - height/2;
                break;
            case "2"://0 direita; 
                xPoints[0] =   rect.x + rect.width/2 - width ;
                yPoints[1] =   rect.y + rect.height/2 - height/2;
                break;
            case "3"://Math.PI/2 cima;
                xPoints[0] =   rect.x + rect.width/2 - width/2 ;
                yPoints[1] =   rect.y + rect.height/2 + height/2;
                break;
             case "4":// -Math.PI/2 baixo;
                xPoints[0] =   rect.x + rect.width/2 - width/2 ;
                yPoints[1] =   rect.y + rect.height/2 + height;
                break;
            case "5":// Math.PI/4 diagonal superior direita;                
                xPoints[0] =   rect.x + rect.width/2 - width ;
                yPoints[1] =   rect.y + rect.height/2 + height;
                break;
            case "6"://-Math.PI/4 diagonal inferior direita
                xPoints[0] =   rect.x + rect.width/2 - width ;
                yPoints[1] =   rect.y + rect.height/2 - height;
                break;
            case "7"://5*Math.PI/6 diagonal inferior esquerda
                xPoints[0] =   rect.x + rect.width/2 + width/2 ;
                yPoints[1] =   rect.y + rect.height/2 - height/2;
                break;
            case "8"://-3*Math.PI/4 diagonal superior esquerda
                xPoints[0] =   rect.x + rect.width/2 + width/2 ;
                yPoints[1] =   rect.y + rect.height/2 + height/2;
                break;              
            default:
                break;
        }
        return points;
    }
    
    private void montarRetangulo() {        
        int[] points = new int[2];

        Rectangle rect = getBounds();
        
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points); 
        int width = (int) Math.round(points[0] * 0.2);
        int height = (int) Math.round(points[1] * 0.2);
        
        xPoints = new int[2];
        yPoints = new int[2];
        
        int[] posicao = new int[2];
//      posicao = definirPosicao(rect,width,height,angulo);
//      xPoints[0] =   posicao[0] ;
//      yPoints[0] =   posicao[1];
//    
        xPoints[0] =   rect.x + rect.width - width;
        yPoints[0] =   rect.y ;

        xPoints[1] = width;
        yPoints[1] = height;
    }

    @Override
    public int getArea() {
        return xPoints[1]*yPoints[1];
    }

}
