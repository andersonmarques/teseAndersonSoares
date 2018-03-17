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
        
        public enum GLYPH_FORMAS{
            RETANGULO   ("RETANGULO"),
            CRUZ        ("CRUZ"),
            ELLIPSE     ("ELLIPSE"),
            HEXAGONO    ("HEXAGONO"),
            LOSANGO     ("LOSANGO"),
            PENTAGONO   ("PENTAGONO"),
            ARCO        ("ARCO"),
            CIRCULO     ("CIRCULO");
            
            private final String nome;
            
            GLYPH_FORMAS(String nome){
                this.nome = nome;
            }
            
            private String nome(){
                return nome;
            }
        }
//        public static final String CIRCULO = "";
//        public static final String RETANGULO = "";
//        public static final String CRUZ = "";
//        public static final String ELLIPSE = "";
//        public static final String ARCO = "";
//        public static final String HEXAGONO = "";
//        public static final String LOSANGO = "";
//        public static final String PENTAGONO = "";

        
    }

    private GeometryFactory() {
    }
    
    public static FormaGeometrica create(Rectangle bounds, Color cor, FORMAS.GLYPH_FORMAS forma,String angulo){
        switch (forma) {
            case RETANGULO:
                return new Retangulo(bounds,cor);
            case CRUZ:
                return new Cruz(bounds,cor);
            case ELLIPSE:
                return new Ellipse(bounds,cor);
            case CIRCULO:
                return new Circulo(bounds, cor);
            case HEXAGONO:
                return new Hexagono(bounds,cor);
            case LOSANGO:
                return new Losango(bounds,cor);
            case PENTAGONO:
                return new Pentagono(bounds,cor);
             case ARCO:
                return new Arco(bounds,angulo);
            default:
                return new Retangulo(bounds,cor);
        }
    }
    
}
