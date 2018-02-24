/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.alfabeto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
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
    private String shandowLetra;
    public int widht;
    public int height;
//    private Font fonte2;

    public Letra(Rectangle r, String letra) {
        this.rect = r;
        setBounds(this.rect);
        this.letra = letra;
        //this.shandowLetra = letra;

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

        montarRetangulo();

        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);

        int width = points[0];
        int height = points[1];
        width = width / 13;
        height = height / 13;
        int result = width + height;

//        g2d.setPaint(Color.black);
//
//        g2d.drawString(shandowLetra, getCenter().x, getCenter().y);
        //verificação para não desenhar letras muito pequenas
        if (result > 6) {
            g2d.setFont(getFonte());
            g2d.setPaint(Color.white);

            //um width um pouco maior + porcentagem de 4%
            int porcent = (int) (width * 1.4);

            int centerX = rect.x + (rect.width / 2) - porcent;
            int centerY = rect.y + rect.height / 2 - height;
            g2d.fillRect(centerX, centerY, result, result);

            g2d.setColor(Color.black);
            g2d.drawString(letra, getCenter().x, getCenter().y);

        } else {
        }

    }

    private Point getCenter() {

        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);
        int width = (int) Math.round(points[0] * 0.2);
        int height = (int) Math.round(points[1] * 0.2);
        int pX = (int) (rect.x + rect.width / 2 - width / 2);
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
