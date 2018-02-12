/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.util;

import doutorado.tese.io.ManipuladorArquivo;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anderson Soares
 */
public class LegendaVisualizacao {

    ArrayList<Object> atributosEscolhidosGlyph;
    private Rectangle bounds;

    public LegendaVisualizacao(Rectangle bound) {
        setBounds(bound);
    }

    public JLabel criarLabel(String conteudoDist, Icon icon) {
        JLabel label = null;
        if (icon != null) {
            label = new JLabel(conteudoDist, icon, JLabel.CENTER);
            //Set the position of the text, relative to the icon:
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalTextPosition(JLabel.RIGHT);
        } else {
            label = new JLabel(conteudoDist);
        }
//        label.setBounds(getBounds());
        label.setVisible(true);
        return label;
    }

    public JPanel addLegendaCor(String itemCor) {
        JPanel painel = new JPanel(new GridLayout(0, 3));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createTitledBorder("Color Subtitle"));
        painel.setBounds(bounds);
        painel.setVisible(true);

        Coluna c = ManipuladorArquivo.getColuna(itemCor);
        List<String> dadosDistintos = c.getDadosDistintos();
        for (int i = 0; i < dadosDistintos.size(); i++) {
            JLabel label = criarLabel(dadosDistintos.get(i), null);
            painel.add(label);
        }

        return painel;
    }

    public JPanel addLegendaDimensao(int dimensao) {
        JPanel painel = new JPanel(new GridLayout(0, 3));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createTitledBorder(this.atributosEscolhidosGlyph.get(dimensao).toString() + "'s Subtitle"));
        painel.setBounds(bounds);
        painel.setVisible(true);

        Coluna c = ManipuladorArquivo.getColuna(this.atributosEscolhidosGlyph.get(dimensao).toString());
        List<String> dadosDistintos = c.getDadosDistintos();
        for (int i = 0; i < dadosDistintos.size(); i++) {
            JLabel label = criarLabel(dadosDistintos.get(i), null);
            painel.add(label);
        }
        return painel;
    }

    public void setAtributosGlyphs(ArrayList<Object> atributosEscolhidosGlyph) {
        this.atributosEscolhidosGlyph = atributosEscolhidosGlyph;
    }

    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    private Rectangle getBounds() {
        return this.bounds;
    }
}
