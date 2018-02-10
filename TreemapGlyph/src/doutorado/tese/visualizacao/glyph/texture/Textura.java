/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.texture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import net.bouthier.treemapAWT.TMPatternFactory;

/**
 *
 * @author Anderson Soares
 */
public class Textura {
    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private String nome;

    public Textura(Rectangle r, String nome) {
        this.rect = r;
        setBounds(this.rect);
        this.nome = nome;
    }

    public void setBounds(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getBounds() {
        return rect;
    }

    public void paint(Graphics g) {
        TMPatternFactory textura = TMPatternFactory.getInstance();
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(textura.get(nome));

        montarRetangulo();
        g2d.fillRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
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
