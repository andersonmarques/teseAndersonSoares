/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.numeros;

import java.awt.Color;
import java.awt.Font;
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
    private Font fonte;
    public int width;
    public int height;
    private boolean legenda;

    public Numeral(Rectangle r, String numero, boolean legenda) {
        this.rect = r;
        setBounds(this.rect);
        this.numero = numero;
        this.legenda = legenda;

        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = points[0];
        int height = points[1];
        width = width / 13;
        height = height / 13;
        int area = width + height;

        fonte = new Font("Arial", Font.PLAIN, area);
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

        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = points[0];
        int height = points[1];
        width = width / 13;
        height = height / 13;
        int result = width + height;

        //verificação para não desenhar letras muito pequenas
        if (result > 5) {
            montarRetangulo();
            g2d.setPaint(Color.white);

            //centro + 1/4
            int centerX = rect.x + rect.width / 2 + width / 4;
            int centerY = rect.y + rect.height / 2 - height;

            //width um 10% 
            g2d.fillRect(centerX, centerY, (int) (result * 0.9), result);

            g2d.setColor(Color.black);
            g2d.setFont(getFonte());
            g2d.drawString(numero, getCenter().x, getCenter().y);
        }
        if (legenda) {
            montarRetangulo();
            g2d.setColor(Color.black);
            g2d.setFont(getFonte());
            g2d.drawString(numero, getCenter().x, getCenter().y);
        }
    }

    private Point getCenter() {
        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = (int) Math.round(points[0] * 0.2);
        int height = (int) Math.round(points[1] * 0.2);

        int pX = (int) (rect.x + rect.width / 2 + width / 4);
        int pY = (int) (rect.y + rect.height / 2 + (height / 2.5));

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
}
