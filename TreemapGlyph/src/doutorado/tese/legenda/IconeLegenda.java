/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.legenda;

import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.alfabeto.Letra;
import doutorado.tese.visualizacao.glyph.formasgeometricas.FormaGeometrica;
import doutorado.tese.visualizacao.glyph.formasgeometricas.GeometryFactory;
import doutorado.tese.visualizacao.glyph.numeros.Numeral;
import doutorado.tese.visualizacao.glyph.texture.Textura;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    private GeometryFactory.FORMAS.GLYPH_FORMAS formaGeom;
    private Color cor;

    private BasicStroke stroke = new BasicStroke(4);

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        Rectangle bounds = new Rectangle(x, y, width, height);
        switch (dimensao) {
            case 0:
                Textura t = new Textura(bounds, valor);
                t.paint(g);
                break;
            case 1:
                FormaGeometrica forma = GeometryFactory.create(bounds, Color.BLUE,GeometryFactory.FORMAS.GLYPH_FORMAS.ARCO, valor);
                forma.paint(g);
                break;
            case 2:
                FormaGeometrica form = GeometryFactory.create(bounds, Color.WHITE,formaGeom, valor);
                form.paint(g);
                break;
            case 3:
                FormaGeometrica formCor = GeometryFactory.create(bounds,cor,GeometryFactory.FORMAS.GLYPH_FORMAS.RETANGULO,valor);
                formCor.paint(g);
                break;
            case 4:
                FormaGeometrica circulo = GeometryFactory.create(bounds,cor,GeometryFactory.FORMAS.GLYPH_FORMAS.CIRCULO,valor);
                circulo.paint(g);
        
                break;
            default:
                inserirIconeAusente(g2d, x, y);
                break;
        }
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
    
    void setFormaIcon(GeometryFactory.FORMAS.GLYPH_FORMAS forma){
        this.formaGeom = forma;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}

