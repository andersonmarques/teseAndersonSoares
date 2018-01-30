package doutorado.tese.visualizacao.glyph;

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
        g2d.drawRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);

        g2d.dispose();
    }

    private void montarRetangulo() {
        int width = (int) Math.round(rect.width) - 1;
        int height = (int) Math.round(rect.height) - 1;
        
        width += rect.x;
        height += rect.y;
        
        xPoints = new int[2];
        yPoints = new int[2];
        
        xPoints[0] = rect.x;
        yPoints[0] = rect.x;
        
        xPoints[1] = width;
        yPoints[1] = width;
    }

}
