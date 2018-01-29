package doutorado.tese.visualizacao.glyph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class Retangulo extends JComponent {

    private Rectangle rect;
   
    public Retangulo(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
    }

    @Override
    public void paint(Graphics g) {
        this.setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.BLUE);

        g2d.drawRect(10, 10, 20, 20);
        
        g2d.dispose();
    }

}
