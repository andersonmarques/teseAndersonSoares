/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.numeros;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 *
 * @author Anderson Soares
 */
public class Numeral {
    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private String numero;

    public Numeral(Rectangle r, String numero) {
        this.rect = r;
        setBounds(this.rect);
        this.numero = numero;
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

        montarRetangulo();
        g2d.setColor(Color.BLACK);
        g2d.drawString(numero, getCenter().x, getCenter().y);
    }
    
    private Point getCenter() {
        int pX = (xPoints[0] + (xPoints[1] / 2) + 1);
        int pY = (yPoints[0] + (yPoints[1] / 2) + 2);
        return new Point(pX, pY);
    }
    
    /**
     * Função para deixar os glyphs quadrados
     * 
     * @param width
     * @param height
     * @return 
     */
    private float verificarRetangulo(float width, float height) {
        if (width > height) {
            width = height;

            return width;
        } else if (width < height) {
            height = width;
            return height;
        }
        return 0;
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