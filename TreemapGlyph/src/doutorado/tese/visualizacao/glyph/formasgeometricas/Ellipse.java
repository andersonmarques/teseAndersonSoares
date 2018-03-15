package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Ellipse {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private int numberColor;
    private Color cor;

    public Ellipse(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
        this.cor = cor;
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
        montarEllipse();
       
        g2d.setColor(Color.white);
        g2d.fillOval(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        g2d.setColor(Color.black);
        g2d.drawOval(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
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

    
    private void montarEllipse() {
        int[] points = new int[2];

        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = (int) Math.round(points[0] * 0.65);
        int height = (int) Math.round(points[1] * 0.4);


        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[0] = (int) (rect.x + rect.width/2 - width/2);
        yPoints[0] = (int) (rect.y + rect.height/2 - height/2);

        xPoints[1] = (int) (width);
        yPoints[1] = (int) (height);
    }

}
