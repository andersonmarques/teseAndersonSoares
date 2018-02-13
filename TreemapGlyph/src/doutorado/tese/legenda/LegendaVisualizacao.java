/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.legenda;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

    public JLabel criarLabel(String conteudoDist, int dimensao) {
        JLabel label = null;
        Coluna col = ManipuladorArquivo.getColuna(this.atributosEscolhidosGlyph.get(dimensao).toString());
        List<String> dadosDistintos = col.getDadosDistintos();
        switch (dimensao) {
            case 0:
                IconeLegenda icon = new IconeLegenda();
                icon.setDimensao(0);
                icon.setValorIcon(Constantes.TIPO_TEXTURA[0]);
                label = new JLabel(conteudoDist, icon, JLabel.RIGHT);
                //Set the position of the text, relative to the icon:
                label.setVerticalTextPosition(JLabel.CENTER);
                label.setHorizontalTextPosition(JLabel.RIGHT);
                break;
            case 1:
                label = new JLabel(conteudoDist, new IconeLegenda(), JLabel.RIGHT);
                //Set the position of the text, relative to the icon:
                label.setVerticalTextPosition(JLabel.CENTER);
                label.setHorizontalTextPosition(JLabel.RIGHT);
                break;
            case 2:
                label = new JLabel(conteudoDist, new IconeLegenda(), JLabel.RIGHT);
                //Set the position of the text, relative to the icon:
                label.setVerticalTextPosition(JLabel.CENTER);
                label.setHorizontalTextPosition(JLabel.RIGHT);
                break;
            case 3:
                label = new JLabel(conteudoDist, new IconeLegenda(), JLabel.RIGHT);
                //Set the position of the text, relative to the icon:
                label.setVerticalTextPosition(JLabel.CENTER);
                label.setHorizontalTextPosition(JLabel.RIGHT);
                break;
            case 4:
                label = new JLabel(conteudoDist, new IconeLegenda(), JLabel.RIGHT);
                //Set the position of the text, relative to the icon:
                label.setVerticalTextPosition(JLabel.CENTER);
                label.setHorizontalTextPosition(JLabel.RIGHT);
                break;
            default:
                System.err.println("Nao ha dimensoes");
                ;
        }
//        if (icon != null) {
//            label = new JLabel(conteudoDist, icon, JLabel.CENTER);
//            //Set the position of the text, relative to the icon:
//            label.setVerticalTextPosition(JLabel.BOTTOM);
//            label.setHorizontalTextPosition(JLabel.RIGHT);
//        } else {
//            label = new JLabel(conteudoDist);
//        }
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
//            JLabel label = criarLabel(dadosDistintos.get(i), null);
//            painel.add(label);
        }

        return painel;
    }

    /**
     * Dimensao analisada no treemap representada por um glyph
     *
     * @param dimensao varia de 0 a 4, pois sao 5 dimensoes de analise de glyphs
     * @return
     */
    public JPanel addLegendaDimensao(int dimensao) {
        JPanel painel = new JPanel(new GridLayout(0, 3));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createTitledBorder(this.atributosEscolhidosGlyph.get(dimensao).toString() + "'s Subtitle"));
        painel.setBounds(bounds);
        painel.setVisible(true);

        Coluna c = ManipuladorArquivo.getColuna(this.atributosEscolhidosGlyph.get(dimensao).toString());
        List<String> dadosDistintos = c.getDadosDistintos();
        for (int i = 0; i < dadosDistintos.size(); i++) {
            JLabel label = criarLabel(dadosDistintos.get(i), dimensao);
            painel.add(label);
            label.setHorizontalAlignment(SwingConstants.LEFT);
            painel.setAlignmentX(label.LEFT_ALIGNMENT);
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
