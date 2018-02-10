/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.util;

import java.awt.Color;

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
    private static String[] cor = {"#3366cc", "#dc3912", "#ff9900", "#109618", "#990099", "#0099c6", "#dd4477", "#66aa00", "#b82e2e", "#316395", "#994499", "#22aa99", "#aaaa11", "#6633cc", "#e67300", "#8b0707", "#651067", "#329262", "#5574a6", "#3b3eac"};

    public enum TipoTextura {
        PATTERN_SS,
        PATTERN_DIAG1,
        PATTERN_DIAG2,
        PATTERN_DIAGDOTS,
        PATTERN_DIAGS,
        PATTERN_DOTS,
        PATTERN_PLUS,
        PATTERN_TUILES,
        PATTERN_SQUARES,
        PATTERN_LIGHT_GRAY,
        PATTERN_TIO
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

    public static String[] getCor() {
        return cor;
    }

}
