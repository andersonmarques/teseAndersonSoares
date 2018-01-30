/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.gui;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.visualizacao.glyph.Estrela;
import doutorado.tese.visualizacao.glyph.Retangulo;
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

    private ManipuladorArquivo manipulador;
    
    public GlassPanel() {
        setOpaque(false);
        setLayout(new GroupLayout(this));
    }

    public GlassPanel(Rectangle bounds) {
        setOpaque(false);
        setBounds(bounds);
        setLayout(new GroupLayout(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 255, 0, 0));
        Rectangle r = getBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        
        g.setColor(Color.BLACK);        
        /** Desenhar estrela **/
        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
            Rectangle bounds = manipulador.getItensTreemap()[i].getBounds();
            Estrela e = new Estrela(bounds);
            e.paint(g);
        }
        /**Desenhar Retangulo**/
//        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
//            Rectangle bounds = manipulador.getItensTreemap()[i].getBounds();
//            Retangulo e = new Retangulo(bounds);
//            e.paint(g);
//        }
    }

    /**
     * @return the manipulador
     */
    public ManipuladorArquivo getManipulador() {
        return manipulador;
    }

    /**
     * @param manipulador the manipulador to set
     */
    public void setManipulador(ManipuladorArquivo manipulador) {
        this.manipulador = manipulador;
    }

}
