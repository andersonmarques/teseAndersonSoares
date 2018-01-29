package doutorado.tese.visualizacao.glyph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JLabel;


public class hexagono extends JLabel{
    public float widht;
    public float height;
    public float x;
    public float y;

    public hexagono(float widht, float height, float x, float y) {
    
        this.widht = widht;
        this.height = height;
        this.x = x;
        this.y = y;    
           
    }
    
       @Override
    public void paint(Graphics g) {
        AffineTransform tx1 = new AffineTransform();
        tx1.translate(widht, height);
        tx1.scale(0.5, 0.5);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(125, 167, 116));
        g2d.fillRect(10, 10, 90, 60);

        g2d.setColor(new Color(42, 179, 231));
        g2d.fillRect(130, 10, 90, 60);

        g2d.setColor(new Color(70, 67, 123));
        g2d.fillRect(250, 10, 90, 60);

        g2d.setColor(new Color(130, 100, 84));
        g2d.fillRect(10, 100, 90, 60);

        g2d.setColor(new Color(252, 211, 61));
        g2d.fillRect(130, 100, 90, 60);

        g2d.setColor(new Color(241, 98, 69));
        g2d.fillRect(250, 100, 90, 60);

        g2d.setColor(new Color(217, 146, 54));
        g2d.fillRect(10, 190, 90, 60);

        g2d.setColor(new Color(63, 121, 186));
        g2d.fillRect(130, 190, 90, 60);

        g2d.setColor(new Color(31, 21, 1));
        g2d.fillRect(250, 190, 90, 60);
         
        g2d.fillRect(70, 180, 120, 60);           
        g2d.setTransform(tx1);
        
        g2d.dispose();
    }
    
    public void DrawingCicle(Graphics g) {
        
    }
    
   
}
