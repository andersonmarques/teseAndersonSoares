/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Anderson Soares
 */
public class GlassPanel extends JPanel {

    public GlassPanel() {
        setOpaque(false);
        setLayout(new GroupLayout(this));
    }

    public GlassPanel(Rectangle bounds) {
        setOpaque(false);
        setBounds(bounds);
        setLayout(new GroupLayout(this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 255, 0, 0));
        Rectangle r = getBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.red);
        g.drawLine(r.x, r.y, r.width, r.height);
    }

}
