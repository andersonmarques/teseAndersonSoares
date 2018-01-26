package doutorado.tese.visualizacao.glyph;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Flags;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class Rect extends JLabel{
    public float widht;
    public float height;
    public float x;
    public float y;

    public Rect(float widht, float height, float x, float y) {
    
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

        g2d.setColor(new Color(300, 159, 116));
        g2d.fillRect(10, 10, 90, 60);
        g2d.setTransform(tx1);
        
        g2d.dispose();
    }
      public void DrawingRect(Graphics g) {

            
       
    }
    
    
    

}
