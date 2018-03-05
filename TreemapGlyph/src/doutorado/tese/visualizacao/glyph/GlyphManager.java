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
import doutorado.tese.visualizacao.glyph.formasgeometricas.GeometryFactory;
import doutorado.tese.visualizacao.glyph.numeros.Numeral;
import doutorado.tese.util.tree.*;
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
    
    private HashMap<String, Integer> configs;

    public GlyphManager() {
        this.configs = new HashMap<>();
    }
    
    public GlyphManager(ManipuladorArquivo manipulador, List<Object> atributosEscolhidos) {
        this.manipulador = manipulador;
        this.atributosEscolhidos = atributosEscolhidos;
        colunaDadosDist = new HashMap<>();
        analisarAtributosEscolhidos();
        shufflerColors = Constantes.getCorGlyphs();
        this.configs = new HashMap<>();
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

    public int adicionarTextura(Graphics g, Rectangle bounds, String textura) {
        Textura t = new Textura(bounds, textura);
        t.paint(g);
        return t.getArea();
    }

    public int adicionarCorForma(Graphics g, Rectangle bounds, Color cor) {
        FormaGeometrica f = GeometryFactory.create(bounds, cor, GeometryFactory.FORMAS.CIRCULO);
        f.setColor(cor);
        f.paint(g);
        return f.getArea();
    }

    public int adicionarFormaGeometrica(Graphics g, Rectangle bounds, String nomeForma) {
        FormaGeometrica f = GeometryFactory.create(bounds, null, nomeForma);
        f.paint(g);
        return f.getArea();
    }

    public int adicionarLetrasAlfabeto(Graphics g, Rectangle bounds, String letra) {
        Letra f = new Letra(bounds, letra, false);
        f.paint(g);
        return f.getArea();
//        return f.getArea();
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
    public int adicionarNumeros(Graphics g, Rectangle bounds, String letra, String numero) {
        Numeral f = new Numeral(bounds, letra + numero, false);
        f.paint(g);
        return f.getArea();
    }

    /**
     * Metodo responsavel por instanciar um glyphs do tipo NUMERAL.
     *
     * @param g
     * @param bounds
     * @param numero
     */
    public int adicionarNumeros(Graphics g, Rectangle bounds, String numero) {
        Numeral f = new Numeral(bounds, numero, false);
        f.paint(g);
        return f.getArea();
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
        
        double [] features = new double[15];
        
        features[6] = item.getBounds().width;
        features[5] = item.getBounds().height;
        features[9] = item.getColor().equals(Constantes.ALICE_BLUE)? 0 : 1;
        features[7] = features[6] * features[5];
        
        double aspect = features[5] > features[6]
                ? features[6] / features[5]
                : features[5] / features[6];
        
        features[8] = aspect;
        for (int dimensao = 0; dimensao < atributosEscolhidos.size(); dimensao++) {
            if (!atributosEscolhidos.get(dimensao).equals("---")) {
                String colunaEscolhida = atributosEscolhidos.get(dimensao).toString();
                Coluna col = ManipuladorArquivo.getColuna(colunaEscolhida);
                List<String> dadosDistintos = colunaDadosDist.get(colunaEscolhida);
                switch (dimensao) {
                    case 0:
                        if(item.getWhat2Draw()[0] > 0){
                            dimensao1Ativada = true;
                            features[10] = calcularPrimeiraDimensao(g, col, item, dadosDistintos);
                            features[0] = 1;
                        }
                        break;
                    case 1:
                        if(item.getWhat2Draw()[1] > 0){
                            dimensao2Ativada = true;
                            features[11] = calcularSegundaDimensao(g, col, item, dadosDistintos);
                            features[1] = 1;
                        }
                        break;
                    case 2:
                        if(item.getWhat2Draw()[2] > 0){
                            dimensao3Ativada = true;
                            features[12] = calcularTerceiraDimensao(g, col, item, dadosDistintos);
                            features[2] = 1;
                        }
                        break;
                    case 3:
                        if(item.getWhat2Draw()[3] > 0){
                            dimensao4Ativada = true;
                            letraUtilizada = "";
                            features[13] = calcularQuartaDimensao(g, col, item, dadosDistintos);
                            features[3] = 1;
                        }
                        break;
                    case 4:
                        if(item.getWhat2Draw()[4] > 0){
                            dimensao5Ativada = true;
                            features[14] = calcularQuintaDimensao(g, col, item, dadosDistintos);
                            features[4] = 1;
                        }
                        break;
                    default:
                        System.err.println("Nao foi possível calcular a dimensão.");
                }
            }
        }
        
        item.getWhat2Draw()[0] = DTViuTextura.predict(features);
        item.getWhat2Draw()[1] = DTViuCor.predict(features);
        item.getWhat2Draw()[2] = DTViuForma.predict(features);
        item.getWhat2Draw()[3] = DTViuLetra.predict(features);
        item.getWhat2Draw()[4] = DTViuNumero.predict(features);
        
        item.setWhat2Draw(true);
        
    }

    public int calcularPrimeiraDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPO_TEXTURA.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                return adicionarTextura(g, item.getBounds(), Constantes.TIPO_TEXTURA[j]);
            }
        }
        return 0;
    }

    public int calcularSegundaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.getCor().length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                return adicionarCorForma(g, item.getBounds(), Color.decode(getShufflerColors()[j]));
            }
        }
        return 0;
    }

    public int calcularTerceiraDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPOS_FORMAS_GEOMETRICAS.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                return adicionarFormaGeometrica(g, item.getBounds(), Constantes.TIPOS_FORMAS_GEOMETRICAS[j]);
            }
        }
        return 0;
    }

    public int calcularQuartaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.LETRAS_ALFABETO.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                int result = adicionarLetrasAlfabeto(g, item.getBounds(), Constantes.LETRAS_ALFABETO[j]);
                letraUtilizada = Constantes.LETRAS_ALFABETO[j];
                return result;
            }
        }
        return 0;
    }

    public int calcularQuintaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.NUMEROS.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                if (dimensao4Ativada) {
                    return adicionarNumeros(g, item.getBounds(), letraUtilizada, Constantes.NUMEROS[j]);
                } else {
                    return adicionarNumeros(g, item.getBounds(), Constantes.NUMEROS[j]);
                }
            }
        }
        return 0;
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
