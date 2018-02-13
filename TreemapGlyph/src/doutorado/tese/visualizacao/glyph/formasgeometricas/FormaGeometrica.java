/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Anderson Soares
 */
public class FormaGeometrica {

    private Rectangle bounds;
    private String name;
    private Color cor;

    public FormaGeometrica(Rectangle bounds, String name) {
        this.bounds = bounds;
        this.name = name;
    }

    public void setColor(Color cor) {
        this.cor = cor;
    }

    public void paint(Graphics g) {
        switch (name) {
            case "RETANGULO":
                Retangulo r = new Retangulo(bounds);
                r.paint(g);
                break;
            case "CRUZ":
                Cruz cruz = new Cruz(bounds);
                cruz.paint(g);
                break;
            case "ESTRELA":
                Estrela e = new Estrela(bounds);
                e.paint(g);
                break;
            case "CIRCULO":
                Circulo c = new Circulo(bounds, cor);
                c.paint(g);
                break;
            case "TRIANGULO":
                Triangulo t = new Triangulo(bounds);
                t.paint(g);
                break;
            case "HEXAGONO":
                Hexagono h = new Hexagono(bounds);
                h.paint(g);
                break;
            case "LOSANGO":
                Losango l = new Losango(bounds);
                l.paint(g);
                break;
            case "PENTAGONO":
                Pentagono p = new Pentagono(bounds);
                p.paint(g);
                break;
            case "TRAPEZIO":
                Trapezio tra = new Trapezio(bounds);
                tra.paint(g);
                break;
            default:
                System.err.println("Não ha glyphs para o desenho.");
        }
    }

}
