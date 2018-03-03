/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Gustavo
 */
public class GeometryFactory {
    
    public static final class FORMAS{
        public static final String CIRCULO = "CIRCULO";
        public static final String RETANGULO = "RETANGULO";
        public static final String CRUZ = "CRUZ";
        public static final String ELLIPSE = "ELLIPSE";
        public static final String OCTOGONO = "OCTOGONO";
        public static final String HEXAGONO = "HEXAGONO";
        public static final String LOSANGO = "LOSANGO";
        public static final String PENTAGONO = "PENTAGONO";
        public static final String TRAPEZIO = "TRAPEZIO";
    }

    private GeometryFactory() {
    }
    
    public static FormaGeometrica create(Rectangle bounds, Color cor, String forma){
        switch (forma) {
            case FORMAS.RETANGULO:
                return new Retangulo(bounds);
            case FORMAS.CRUZ:
                return new Cruz(bounds);
            case FORMAS.ELLIPSE:
                return new Ellipse(bounds);
            case FORMAS.CIRCULO:
                return new Circulo(bounds, cor);
            case FORMAS.OCTOGONO:
                return new Octogono(bounds);
            case FORMAS.HEXAGONO:
                return new Hexagono(bounds);
            case FORMAS.LOSANGO:
                return new Losango(bounds);
            case FORMAS.PENTAGONO:
                return new Pentagono(bounds);
            case FORMAS.TRAPEZIO:
                return new Trapezio(bounds);
            default:
                return new Retangulo(bounds);
        }
    }
    
}
