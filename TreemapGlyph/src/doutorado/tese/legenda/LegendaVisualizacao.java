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
import static java.lang.ProcessBuilder.Redirect.to;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    ArrayList<Object> atributosEscolhidosGlyph;
    private Rectangle bounds;

    public LegendaVisualizacao(Rectangle bound) {
        setBounds(bound);
    }

    public JLabel criarLabel(String conteudoDist, Icon icon) {
        JLabel label = null;
        if (icon != null) {
            label = new JLabel(conteudoDist, icon, JLabel.RIGHT);
            //Set the position of the text, relative to the icon:
            label.setVerticalTextPosition(JLabel.CENTER);
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
            IconeLegenda icon = new IconeLegenda();
            icon.setDimensao(dimensao);
            switch (dimensao) {
                case 0:
                    icon.setValorIcon(Constantes.TIPO_TEXTURA[i]);
                    break;
                case 1:
                    icon.setValorIcon(Constantes.getCor()[i]);
                    break;
                case 2:
                    icon.setValorIcon(Constantes.TIPOS_FORMAS_GEOMETRICAS[i]);
                    break;
                case 3:
                    icon.setValorIcon(Constantes.LETRAS_ALFABETO[i]);
                    break;
                case 4:
                    icon.setValorIcon(Constantes.NUMEROS[i]);
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

    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    private Rectangle getBounds() {
        return this.bounds;
    }
}
