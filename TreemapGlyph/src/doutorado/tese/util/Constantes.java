/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Anderson
 */
public class Constantes {

    public final static String PROGRESS = "progress";
    public final static String VALUE_SAME_SIZE = "\t1";
    private static boolean showGlyph = false;
    private static boolean showLegenda = false;
    private static boolean showStarGlyph = false;
//    private static Color[] cores = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
    public static final int LIMITE_TESTES = 100;
    private static String[] cor = {
        "#3366cc","#dc3912","#ff9900","#109618","#990099","#0099c6","#dd4477",
        "#66aa00","#b82e2e","#316395","#994499","#22aa99","#aaaa11","#6633cc",
        "#e67300","#8b0707","#651067","#329262","#5574a6", "#3b3eac","#F0F8FF"
    };

    private static String[] corGlyphs = {
        "#1f77b4","#ff7f0e","#2ca02c","#d62728",
        "#9467bd","#8c564b","#e377c2","#7f7f7f",
        "#bcbd22","#17becf","#c6dbef","#a1d99b"
    };

    public final static String[] TIPO_TEXTURA = {
        "PATTERNV",
        "PATTERN_DIAG1",
        "PATTERN_DIAG2",
        "PATTERN_DIAG3",
        "PatternSquareC",
        "PATTERN_DIAG5",
        "PATTERN_DIAGDOTS",
        "PATTERN_DIAGDOTS2",
        "PATTERN_DIAGS",
        "PATTERN_DOTS",
        "PATTERN_PLUS",
        "PATTERN_TUILES",
        "PATTERN_SQUARES",
        "PATTERN_LIGHT_GRAY",
        "PATTERN_TIO"

    };
    /**
     * 0 - "OCTOGONO", 1 - "ELLIPSE", 2 - "CRUZ", 3 - "LOSANGO", 4 -
     * "PENTAGONO", 5 - "TRAPEZIO", 6 - "HEXAGONO", 7 - "RETANGULO", 8 -
     * "CIRCULO"
     */
    public final static String[] TIPOS_FORMAS_GEOMETRICAS = {
        "OCTOGONO", "ELLIPSE", "CRUZ", "LOSANGO", "PENTAGONO", "TRAPEZIO", "HEXAGONO", "RETANGULO", "CIRCULO"

    };

    public final static String[] LETRAS_ALFABETO = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] NUMEROS = {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
    };

    public enum NivelGlyph {
        NIVEL_1(1),
        NIVEL_2(2),
        NIVEL_3(3),
        NIVEL_4(4),
        NIVEL_5(5);

        private final int nivelGlyph;

        NivelGlyph(int nivelGlyph) {
            this.nivelGlyph = nivelGlyph;
        }

        private double nivel() {
            return nivelGlyph;
        }
    }

    public static boolean isShowGlyph() {
        return showGlyph;
    }

    public static void setShowGlyph(boolean aShowGlyph) {
        showGlyph = aShowGlyph;
    }

    public static boolean isShowLegenda() {
        return showLegenda;
    }

    public static void setShowLegenda(boolean aShowLegenda) {
        showLegenda = aShowLegenda;
    }

    public static boolean isShowStarGlyph() {
        return showStarGlyph;
    }

    public static void setShowStarGlyph(boolean aShowStarGlyph) {
        showStarGlyph = aShowStarGlyph;
    }

    /**
     * Vetor de cores usado no segundo nivel de glyphs e na dimensao cores do
     * treemap
     *
     * @return
     */
    public static String[] getCor() {
        return cor;
    }

    /**
     * @return the corGlyphs
     */
    public static String[] getCorGlyphs() {
        return corGlyphs;
    }

}
