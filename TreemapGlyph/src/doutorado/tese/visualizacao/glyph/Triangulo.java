/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 *
 * @author Elvis (LABVIS)
 */
public class Triangulo extends JComponent {

    private Rectangle rect;
   
    public Triangulo(Rectangle r) {
        this.rect = r;
        setBounds(this.rect);
    }
    
    @Override
    public void paint(Graphics g) {
        this.setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.red);
        
        Polygon p = new Polygon();
        p.addPoint(0, 50);
        p.addPoint(25, 0);
        p.addPoint(50, 50);
        g.drawPolygon(p);

        g2d.dispose();
    }

}
