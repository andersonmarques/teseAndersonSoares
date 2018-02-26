/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.alfabeto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import javax.swing.BorderFactory;

/**
 *
 * @author Anderson Soares
 */
public class Letra {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private String letra;
    private Font fonte;
    private boolean legenda;

    public Letra(Rectangle r, String letra, boolean legenda) {
        this.rect = r;
        setBounds(this.rect);
        this.letra = letra;
        this.legenda = legenda;
        //verifica o quadrado interno
        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = points[0];
        int height = points[1];
        width = width / 13;
        height = height / 13;
        int area = width + height;

        fonte = new Font("Arial black", Font.PLAIN, area);
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

        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int result = points[0] + points[1];

        //verificação para não desenhar letras muito pequenas
        if (result > 5) {
            g2d.setFont(getFonte());
            //calculode centro das letras
            FontMetrics metrics = g.getFontMetrics(getFonte());
            int x = rect.x + (rect.width - metrics.stringWidth(getLetra())) / 2;
            int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

            FontMetrics fm = g.getFontMetrics();
            Rectangle2D rect = fm.getStringBounds(getLetra(), g);

            g.setColor(Color.white);
            g.fillRect(x, y - fm.getAscent(), (int) rect.getWidth(), (int) rect.getHeight());

            g2d.setColor(Color.black);
            g2d.drawString(letra, x, y);
        }
        if (legenda) {
            montarRetangulo();
            g2d.setColor(Color.black);
            g2d.setFont(getFonte());
            g2d.drawString(letra, getCenter().x, getCenter().y);
        }

    }

    private Point getCenter() {
        int pX = (int) (rect.x + rect.width / 2);
        int pY = (int) (rect.y + rect.height / 2);

        return new Point(pX, pY);
    }

    /**
     * Função para deixar os glyphs quadrados
     *
     * @param width
     * @param height
     * @return
     */
    private int[] verificarRetangulo(int[] point) {
        if (point[0] > point[1]) {
            point[0] = point[1];
            return point;
        } else if (point[0] < point[1]) {
            point[1] = point[0];
            return point;
        }
        return null;
    }

    private void montarRetangulo() {

        int[] points = new int[2];

        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = (int) Math.round(points[0] * 0.2);
        int height = (int) Math.round(points[1] * 0.2);

        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[0] = (int) (rect.x + (rect.x / 2) - width / 2);
        yPoints[0] = (int) (rect.y + (rect.y / 2) - height / 2);

        xPoints[1] = width;
        yPoints[1] = height;
    }

    /**
     * @return the fonte
     */
    public Font getFonte() {
        return fonte;
    }

    /**
     * @param fonte the fonte to set
     */
    public void setFonte(Font fonte) {
        this.fonte = fonte;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

}
