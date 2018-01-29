package doutorado.tese.visualizacao.glyph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 *
 * @author Anderson
 */
public class Circulo extends JComponent {

    private Rectangle rect;
   
    public Circulo(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
    }

    @Override
    public void paint(Graphics g) {
        this.setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawOval(0, 0, 10, 10);
//        AffineTransform tx1 = new AffineTransform();
//        tx1.translate(widht, height);
//        tx1.scale(0.5, 0.5);

//        g.setColor(Color.blue);
//        g.fillOval(x, y, widht, height);
//        g2d.translate(x, y);
//        g2d.setTransform(tx1);

        g2d.dispose();
    }

}
