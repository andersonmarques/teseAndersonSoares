/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.legenda;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.formasgeometricas.GeometryFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Anderson Soares
 */
public class LegendaVisualizacao {
    List<Object> formasEscolhidas;
    List<Object> atributosEscolhidos;
    ArrayList<Object> atributosEscolhidosGlyph;
    private Rectangle bounds;

    public LegendaVisualizacao(Rectangle bound,List<Object> formasEscolhidas) {
        this.formasEscolhidas= formasEscolhidas;
        setBounds(bound);
    }
    
    public JLabel criarLabel(String conteudoDist, Icon icon) {
        JLabel label = null;
        if (icon != null) {
            label = new JLabel(conteudoDist, icon, JLabel.RIGHT);
            //Set the position of the text, relative to the icon:
            label.setVerticalTextPosition(JLabel.CENTER);
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(new Font("Arial", Font.PLAIN, 12));
        } else {
            label = new JLabel(conteudoDist);
        }
//        label.setBounds(getBounds());
        label.setVisible(true);
        return label;
    }

    public JPanel addLegendaCorTreemap(String itemCor) {
        JPanel painel = new JPanel(new GridLayout(0, 3));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createTitledBorder("Color TreeMap (" + itemCor + ") Subtitle"));
        painel.setBounds(bounds);
        painel.setVisible(true);

        Coluna c = ManipuladorArquivo.getColuna(itemCor);
        List<String> dadosDistintos = c.getDadosDistintos();
        for (int i = 0; i < dadosDistintos.size(); i++) {
            IconeLegenda icon = new IconeLegenda();
            icon.setDimensao(4);
            icon.setFormaIcon(GeometryFactory.FORMAS.GLYPH_FORMAS.RETANGULO);
            icon.setCor(Color.decode(Constantes.getCor()[i]));
            JLabel label = criarLabel(dadosDistintos.get(i), icon);
            painel.add(label);
            label.setHorizontalAlignment(SwingConstants.LEFT);
            painel.setAlignmentX(label.LEFT_ALIGNMENT);
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
            IconeLegenda icon = new IconeLegenda();
            icon.setDimensao(dimensao);
            switch (dimensao) {
                case 0:
                    icon.setValorIcon(Constantes.TIPO_TEXTURA[i]);
                    break;
                case 1:
                    icon.setValorIcon(Constantes.ANGLE[i]);
                    break;
                case 2:
                    icon.setFormaIcon(GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(formasEscolhidas.get(0))));
                    icon.setCor(Color.decode(Constantes.getCorFormas()[i]));
                    break;
                case 3:
                    icon.setFormaIcon(GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(formasEscolhidas.get(1))));
                    icon.setCor(Color.decode(Constantes.getCorFormas2()[i]));
                    break;
                case 4:
                    icon.setFormaIcon(GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(formasEscolhidas.get(2))));
                    icon.setCor(Color.decode(Constantes.getCorFormas()[i]));
                    break;
                case 5:
                    icon.setFormaIcon(GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(formasEscolhidas.get(3))));
                    icon.setCor(Color.decode(Constantes.getCorFormas2()[i]));
                    break;
                case 6:
                    icon.setValorIcon(Constantes.visivel[i]);
                    icon.setCor(Color.decode(Constantes.getCorFormas()[i]));
                    break;
                default:
                    throw new AssertionError();
            }
            JLabel label = criarLabel(dadosDistintos.get(i), icon);
            painel.add(label);

            label.setHorizontalAlignment(SwingConstants.LEFT);
            painel.setAlignmentX(label.LEFT_ALIGNMENT);
        }
        return painel;
    }

    public void setAtributosGlyphs(ArrayList<Object> atributosEscolhidosGlyph) {
        this.atributosEscolhidosGlyph = atributosEscolhidosGlyph;
    }

    public void setFormasEscolhidas(List<Object> formasEscolhidas) {
        this.formasEscolhidas = formasEscolhidas;
    }

    
    
    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    private Rectangle getBounds() {
        return this.bounds;
    }
}
