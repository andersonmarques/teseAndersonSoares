/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.numeros;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Anderson Soares
 */
public class Numeral {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private String numero;
    private Font fonte;
    private boolean legenda;
    private boolean ativo;

    public Numeral(Rectangle r, String numero, boolean legenda) {
        this.rect = r;
        setBounds(this.rect);
        this.numero = numero;
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
            int x = calcularFontMetrics(g).x;
            int y = calcularFontMetrics(g).y;

            FontMetrics fm = g.getFontMetrics();
            Rectangle2D rect = fm.getStringBounds(getNumero(), g);

            g.setColor(Color.white);
            g.fillRect(x, y - fm.getAscent(), (int) rect.getWidth(), (int) rect.getHeight());

            g2d.setColor(Color.black);
            g2d.drawString(getNumero(), x, y);
        }
        if (legenda) {
            montarRetangulo();
            g2d.setColor(Color.black);
            g2d.setFont(getFonte());
            g2d.drawString(getNumero(), calcularFontMetrics(g).x, calcularFontMetrics(g).y);
        }
    }

    public void setBounds(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getBounds() {
        return rect;
    }

    /**
     * Calculo do centro das letras
     *
     * @return
     */
    private Point calcularFontMetrics(Graphics g) {
        FontMetrics metrics = g.getFontMetrics(getFonte());
        int pX = rect.x + (rect.width - metrics.stringWidth(getNumero())) / 2;
        int pY = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

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

    public String getNumero() {
        return numero;
    }

    public void setLetra(String numero) {
        this.numero = numero;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
