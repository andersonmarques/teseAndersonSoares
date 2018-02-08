/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.gui;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.visualizacao.glyph.Circulo;
import doutorado.tese.visualizacao.glyph.GlyphManager;
import doutorado.tese.visualizacao.glyph.Retangulo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.ListModel;

/**
 *
 * @author Anderson Soares
 */
public class GlassPanel extends JPanel {

    private ManipuladorArquivo manipulador;
    private GlyphManager glyphManager;

    public GlassPanel() {
        setOpaque(false);
        setLayout(new GroupLayout(this));
    }

    public GlassPanel(Rectangle bounds) {
        setOpaque(false);
        setBounds(bounds);
        setLayout(new GroupLayout(this));
    }

    public void setAtributosEscolhidos(ListModel<String> atributosEscolhidos) {
        glyphManager = new GlyphManager(getManipulador(), atributosEscolhidos);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 255, 0, 0));//painel com fundo transparente
        Rectangle r = getBounds();
        g.fillRect(r.x, r.y, r.width, r.height);

//        g.setColor(Color.BLACK);
        glyphManager.paint(g);

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
