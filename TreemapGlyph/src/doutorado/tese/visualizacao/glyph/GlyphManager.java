/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.alfabeto.Letra;
import doutorado.tese.visualizacao.glyph.formasgeometricas.FormaGeometrica;
import doutorado.tese.visualizacao.glyph.numeros.Numeral;
//import doutorado.tese.visualizacao.glyph.numeros.Numeral;
import doutorado.tese.visualizacao.glyph.texture.Textura;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.bouthier.treemapAWT.TMNodeEncapsulator;
import net.bouthier.treemapAWT.TMNodeModel;
import net.bouthier.treemapAWT.TMNodeModelComposite;
import net.bouthier.treemapAWT.TMNodeModelRoot;

/**
 *
 * @author Anderson Soares
 */
public final class GlyphManager {

    private ManipuladorArquivo manipulador;
    private List<Object> atributosEscolhidos;
    private HashMap<String, List<String>> colunaDadosDist;
    private TMNodeModelRoot rootNodeZoom;
    private boolean dimensao1Ativada, dimensao2Ativada, dimensao3Ativada, dimensao4Ativada, dimensao5Ativada;
    private String letraUtilizada;
    private static String[] shufflerColors;

    public GlyphManager() {
        
    }
    
    public GlyphManager(ManipuladorArquivo manipulador, List<Object> atributosEscolhidos) {
        this.manipulador = manipulador;
        this.atributosEscolhidos = atributosEscolhidos;
        colunaDadosDist = new HashMap<>();
        analisarAtributosEscolhidos();
        shufflerColors = Constantes.getCorGlyphs();
    }
    
    public void analisarAtributosEscolhidos() {
        for (int i = 0; i < atributosEscolhidos.size(); i++) {
            if (!atributosEscolhidos.get(i).equals("---")) {
                Coluna c = ManipuladorArquivo.getColuna(atributosEscolhidos.get(i).toString());
                List<String> dadosDistintos = c.getDadosDistintos();
                colunaDadosDist.put(c.getName(), dadosDistintos);
            }
        }
    }

    public void adicionarTextura(Graphics g, Rectangle bounds, String textura) {
        Textura t = new Textura(bounds, textura);
        t.paint(g);
    }

    public void adicionarCorForma(Graphics g, Rectangle bounds, Color cor) {
        FormaGeometrica f = new FormaGeometrica(bounds, Constantes.TIPOS_FORMAS_GEOMETRICAS[Constantes.TIPOS_FORMAS_GEOMETRICAS.length - 1]);
        f.setColor(cor);
        f.paint(g);
    }

    public void adicionarFormaGeometrica(Graphics g, Rectangle bounds, String nomeForma) {
        FormaGeometrica f = new FormaGeometrica(bounds, nomeForma);
        f.paint(g);
    }

    public void adicionarLetrasAlfabeto(Graphics g, Rectangle bounds, String letra) {
        Letra f = new Letra(bounds, letra, false);
        f.paint(g);
    }

    /**
     * Metodo responsavel por instanciar um glyphs do tipo NUMERAL. Esse metodo
     * concatena o glyph do tipo LETRA, caso tenha sido ativado, ao glyphs
     * NUMERAL.
     *
     * @param g
     * @param bounds
     * @param letra
     * @param numero
     */
    public void adicionarNumeros(Graphics g, Rectangle bounds, String letra, String numero) {
        Numeral f = new Numeral(bounds, letra + numero, false);
        f.paint(g);
    }

    /**
     * Metodo responsavel por instanciar um glyphs do tipo NUMERAL.
     *
     * @param g
     * @param bounds
     * @param numero
     */
    public void adicionarNumeros(Graphics g, Rectangle bounds, String numero) {
        Numeral f = new Numeral(bounds, numero, false);
        f.paint(g);
    }

    public void paint(Graphics g) {
        if (getRootNodeZoom() != null) {
            setGlyphsInTreeMapItems(getRootNodeZoom().getRoot(), g);

        }
    }

    public void setGlyphsInTreeMapItems(TMNodeModel nodo, Graphics g) {
        if (nodo instanceof TMNodeModelComposite) {//se for TreeMap Level
            TMNodeModelComposite pai = (TMNodeModelComposite) nodo;
            for (TMNodeModel n : pai.getChildrenList()) {
                setGlyphsInTreeMapItems(n, g);
            }
        } else {//se for um treemap Item ele vai desenhar os glyphs
            TMNodeEncapsulator nodeEncapsulator = (TMNodeEncapsulator) nodo.getNode();
            TreeMapItem treemapItem = (TreeMapItem) nodeEncapsulator.getNode();
            defineDimension2DrawGlyph(g, treemapItem);
        }
    }

    public void defineDimension2DrawGlyph(Graphics g, TreeMapItem item) {
        for (int dimensao = 0; dimensao < atributosEscolhidos.size(); dimensao++) {
            if (!atributosEscolhidos.get(dimensao).equals("---")) {
                String colunaEscolhida = atributosEscolhidos.get(dimensao).toString();
                Coluna col = ManipuladorArquivo.getColuna(colunaEscolhida);
                List<String> dadosDistintos = colunaDadosDist.get(colunaEscolhida);
                switch (dimensao) {
                    case 0:
                        dimensao1Ativada = true;
                        calcularPrimeiraDimensao(g, col, item, dadosDistintos);
                        break;
                    case 1:
                        dimensao2Ativada = true;
                        calcularSegundaDimensao(g, col, item, dadosDistintos);
                        break;
                    case 2:
                        dimensao3Ativada = true;
                        calcularTerceiraDimensao(g, col, item, dadosDistintos);
                        break;
                    case 3:
                        dimensao4Ativada = true;
                        letraUtilizada = "";
                        calcularQuartaDimensao(g, col, item, dadosDistintos);
                        break;
                    case 4:
                        dimensao5Ativada = true;
                        calcularQuintaDimensao(g, col, item, dadosDistintos);
                        break;
                    default:
                        System.err.println("Nao foi possível calcular a dimensão.");
                }
            }
        }
    }

    public void calcularPrimeiraDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPO_TEXTURA.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarTextura(g, item.getBounds(), Constantes.TIPO_TEXTURA[j]);
                break;
            }
        }
    }

    public void calcularSegundaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.getCor().length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarCorForma(g, item.getBounds(), Color.decode(getShufflerColors()[j]));
                break;
            }
        }
    }

    public void calcularTerceiraDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPOS_FORMAS_GEOMETRICAS.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarFormaGeometrica(g, item.getBounds(), Constantes.TIPOS_FORMAS_GEOMETRICAS[j]);
                break;
            }
        }
    }

    public void calcularQuartaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.LETRAS_ALFABETO.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarLetrasAlfabeto(g, item.getBounds(), Constantes.LETRAS_ALFABETO[j]);
                letraUtilizada = Constantes.LETRAS_ALFABETO[j];
                break;
            }
        }
    }

    public void calcularQuintaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.NUMEROS.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                if (dimensao4Ativada) {
                    adicionarNumeros(g, item.getBounds(), letraUtilizada, Constantes.NUMEROS[j]);
                } else {
                    adicionarNumeros(g, item.getBounds(), Constantes.NUMEROS[j]);
                }
                break;
            }
        }
    }

    /**
     * @return the rootNodeZoom
     */
    public TMNodeModelRoot getRootNodeZoom() {
        return rootNodeZoom;
    }

    /**
     * @param rootNodeZoom the rootNodeZoom to set
     */
    public void setRootNodeZoom(TMNodeModelRoot rootNodeZoom) {
        this.rootNodeZoom = rootNodeZoom;
    }
    
    /**
     * @return the shufflerColors
     */
    public static String[] getShufflerColors() {
        return shufflerColors;
    }
}
