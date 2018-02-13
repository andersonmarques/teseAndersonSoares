/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.legenda;

import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.texture.Textura;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author Anderson Soares
 */
public class IconeLegenda implements Icon {

    private int width = 32;
    private int height = 32;
    private int dimensao;
    private String valor;

    private BasicStroke stroke = new BasicStroke(4);

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

//        switch (dimensao) {
//            case 0:
//                calcularPrimeiraDimensao(g, x, y);
//                break;
//            case 1:
//                calcularSegundaDimensao(g, col, item, dadosDistintos);
//                break;
//            case 2:
//                calcularTerceiraDimensao(g, col, item, dadosDistintos);
//                break;
//            case 3:
//                calcularQuartaDimensao(g, col, item, dadosDistintos);
//                break;
//            case 4:
//                calcularQuintaDimensao(g, col, item, dadosDistintos);
//                break;
//            default:
                inserirIconeAusente(g2d, x, y);
//        }
        g2d.dispose();
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    private void calcularPrimeiraDimensao(Graphics g, int x, int y) {
        Rectangle bounds = new Rectangle(x, y, width, height);
        Textura t = new Textura(bounds, this.valor);
        t.paint(g);
    }

    private void inserirIconeAusente(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x + 1, y + 1, width - 2, height - 2);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 1, y + 1, width - 2, height - 2);

        g2d.setColor(Color.RED);

        g2d.setStroke(stroke);
        g2d.drawLine(x + 10, y + 10, x + width - 10, y + height - 10);
        g2d.drawLine(x + 10, y + height - 10, x + width - 10, y + 10);
    }

    void setValorIcon(String valor) {
        this.valor = valor;
    }
}
