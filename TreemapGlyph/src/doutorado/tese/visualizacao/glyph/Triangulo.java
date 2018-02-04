package doutorado.tese.visualizacao.glyph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

public class Triangulo {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;

    public Triangulo(Rectangle r) {
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

        montarTriangulo();
        //g2d.dispose();

        System.out.println(rect + " x" + xPoints[1] + "y" + xPoints[1]);
        g2d.setColor(new Color(70, 130, 180));

        Polygon poligono = new Polygon();
//        // Adiciona o primeiro ponto, o de cima.  
//        poligono.addPoint(,);  
////        // Adiciona o segundo ponto, o do canto inferior esquerdo  
//        poligono.addPoint(,);  
////        // Adiciona o segundo ponto, o do canto inferior direito          
//        poligono.addPoint(,);  

        // Desenha o triângulo  
        //
        g.drawPolygon(poligono);

        System.out.println(xPoints[0] + " " + yPoints[0] + " " + xPoints[1] + " " + yPoints[1]);

    }

    private void montarTriangulo() {
        int width = (int) Math.round(rect.width / 2);
        int height = (int) Math.round((rect.height) / 2);

        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[0] = (int) (rect.x + (width * 0.62));
        yPoints[0] = (int) (rect.y + (height * 0.62));

        xPoints[1] = (int) (width * 0.8);
        yPoints[1] = (int) (height * 0.8);
    }

}
//
//    @Override
//    public void paint(Graphics g) {
//        this.setOpaque(false);
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setPaint(Color.red);
//        
//        Polygon p = new Polygon();
//        p.addPoint(0, 50);
//        p.addPoint(25, 0);
//        p.addPoint(50, 50);
//        g.drawPolygon(p);
//
//        g2d.dispose();
//    }

