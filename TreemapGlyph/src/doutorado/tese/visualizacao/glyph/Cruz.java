package doutorado.tese.visualizacao.glyph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Anderson
 */
public class Cruz extends JComponent {

    private Rectangle rect;

    public Cruz(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.red);
        
        AffineTransform tx1 = new AffineTransform();
        tx1.translate(rect.width, rect.height);
        tx1.scale(0.5, 0.5);
        
        g2d.setColor(new Color(252, 211, 61));
        g2d.fillRect(10, 150, 60, 120);

        g2d.fillRect(10, 180, 120, 60);
        g2d.setTransform(tx1);

        g2d.dispose();
    }

}
