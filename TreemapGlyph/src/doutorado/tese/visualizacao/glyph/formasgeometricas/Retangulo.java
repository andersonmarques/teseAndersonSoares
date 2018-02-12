package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Retangulo {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;

    public Retangulo(Rectangle r) {
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

        g2d.setPaint(Color.BLUE);

        montarRetangulo();
        g2d.setColor(new Color(70, 67, 123));
        g2d.drawRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);

    }
    //função para deixar os glyphs quadrados
        private float verificarRetangulo(float width, float height){
        if(width > height){
            width = height;
            
             return width;
        }
        else if(width < height){
            height = width;
             return height;
        }
        return 0;
    }

    private void montarRetangulo() {
        int width = (int) Math.round(rect.width / 2);
        int height = (int) Math.round(rect.height / 2);        
        
//        float ponto = verificarRetangulo(width,height);
//
//        
//        if(width > height){
//          //xPoints[0] = (int) (rect.x + (width / 2));
//          //yPoints[0] = rect.y + (rect.y / 2);
//
//        }
//        else if(width < height){
//            //xPoints[0] = rect.x + (width / 2);
//            //yPoints[0] = (int) (rect.y + (rect.y / 2));
//        
//        
//        
//            xPoints[0] = rect.x + (width / 2);
//            yPoints[0] = rect.y + (height / 2);
//       
//        
//        xPoints[1] = (int) ponto;
//        yPoints[1] = (int) ponto;
//        
//    }

        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[0] = rect.x + (width / 2);
        yPoints[0] = rect.y + (height / 2);

        xPoints[1] = width;
        yPoints[1] = height;
    }

}
