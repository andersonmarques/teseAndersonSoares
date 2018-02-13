package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Circulo {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private int numberColor;
    private Color cor;

    public Circulo(Rectangle r, Color cor) {
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
        montarCirculo();
        g2d.setColor(cor);
        g2d.fillOval(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
    }

    public Color selecionarCor(int number) {
        //verde
        Color c1 = new Color(125, 167, 116);
        //azul
        Color c2 = new Color(42, 179, 231);

        //roxo
        Color c3 = new Color(70, 67, 123);
        //pesego
        Color c4 = new Color(241, 98, 69);
        //preto
        Color c5 = new Color(63, 121, 186);
        //cinza
        Color c6 = new Color(169, 169, 169);
        //vermelho
        Color c7 = new Color(139, 0, 0);
        //branco
        Color c8 = new Color(255, 255, 255);
        //amarelo
        Color c9 = new Color(252, 211, 61);

        switch (numberColor) {
            case 1:
                return c1;
            case 2:
                return c2;
            case 3:
                return c3;
            case 4:
                return c4;
            case 5:
                return c5;
            case 6:
                return c6;
            case 7:
                return c7;
            case 8:
                return c8;
            case 9:
                return c9;

            default:
                throw new AssertionError();
        }
    }

    private void montarCirculo() {
        int width = (int) Math.round(rect.width / 1.6);
        int height = (int) Math.round(rect.height / 1.6);

        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[0] = rect.x + width / 4;
        yPoints[0] = rect.y + width / 4;

        xPoints[1] = (int) (width);
        yPoints[1] = (int) (height);
    }

}
