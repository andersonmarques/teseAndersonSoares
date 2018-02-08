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
        //g2d.dispose();

//        System.out.println(rect + " x" + xPoints[1] + "y" + xPoints[1]);
        g2d.setColor(new Color(70, 67, 123));
        g2d.fillRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);

//        System.out.println(xPoints[0] + " " + yPoints[0] + " " + xPoints[1] + " " + yPoints[1]);

    }

    private void montarRetangulo() {
        int width = (int) Math.round(rect.width / 2);
        int height = (int) Math.round(rect.height / 2);

        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[0] = rect.x + (width / 2);
        yPoints[0] = rect.y + (height / 2);

        xPoints[1] = width;
        yPoints[1] = height;
    }

}
