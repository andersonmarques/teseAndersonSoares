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
import doutorado.tese.visualizacao.glyph.texture.Textura;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import net.bouthier.treemapAWT.TMNodeEncapsulator;
import net.bouthier.treemapAWT.TMNodeModel;
import net.bouthier.treemapAWT.TMNodeModelComposite;
import net.bouthier.treemapAWT.TMNodeModelRoot;

/**
 *
 * @author Anderson Soares
 */
public final class GlyphManager {

    private final ManipuladorArquivo manipulador;
    private final List<Object> atributosEscolhidos;
    private HashMap<String, Integer> colunaDadosDist;
    private TMNodeModelRoot rootNodeZoom;
    

    public GlyphManager(ManipuladorArquivo manipulador, List<Object> atributosEscolhidos) {
        this.manipulador = manipulador;
        this.atributosEscolhidos = atributosEscolhidos;
        colunaDadosDist = new HashMap<>();
        analisarAtributosEscolhidos();
    }

    public void analisarAtributosEscolhidos() {
        for (int i = 0; i < atributosEscolhidos.size(); i++) {
            if (!atributosEscolhidos.get(i).equals("---")) {
                Coluna c = ManipuladorArquivo.getColuna(atributosEscolhidos.get(i).toString());
                List<String> dadosDistintos = c.getDadosDistintos();
                colunaDadosDist.put(c.getName(), dadosDistintos.size());
            }
        }
    }

    private void adicionarTextura(Graphics g, Rectangle bounds, String textura) {
        Textura t = new Textura(bounds, textura);
        t.paint(g);
    }

    private void adicionarCorForma(Graphics g, Rectangle bounds, Color cor) {
        FormaGeometrica f = new FormaGeometrica(bounds, Constantes.TIPOS_FORMAS_GEOMETRICAS[Constantes.TIPOS_FORMAS_GEOMETRICAS.length - 1]);
        f.setColor(cor);
        f.paint(g);
    }

    private void adicionarFormaGeometrica(Graphics g, Rectangle bounds, String nomeForma) {
        FormaGeometrica f = new FormaGeometrica(bounds, nomeForma);
        f.paint(g);
    }

    private void adicionarLetrasAlfabeto(Graphics g, Rectangle bounds, String letra) {
        Letra f = new Letra(bounds, letra, false);
        f.paint(g);
    }

    private void adicionarNumeros(Graphics g, Rectangle bounds, String numero) {
        Numeral f = new Numeral(bounds, numero, false);
        f.paint(g);
    }

    public void paint(Graphics g) {
        for (int dimensao = 0; dimensao < atributosEscolhidos.size(); dimensao++) {
            if (!atributosEscolhidos.get(dimensao).equals("---")) {
                Coluna col = ManipuladorArquivo.getColuna(atributosEscolhidos.get(dimensao).toString());
                List<String> dadosDistintos = col.getDadosDistintos();
                if (getRootNodeZoom() != null) {
                    setGlyphsInTreeMapItems(getRootNodeZoom().getRoot(), dimensao, g, col, dadosDistintos);
                }
            }
        }
    }

    public void setGlyphsInTreeMapItems(TMNodeModel nodo, int dimensao, Graphics g, Coluna col, List<String> dadosDistintos) {
        if (nodo instanceof TMNodeModelComposite) {//se for TreeMap Level
            TMNodeModelComposite pai = (TMNodeModelComposite) nodo; 
            for (TMNodeModel n : pai.getChildrenList()) {
                setGlyphsInTreeMapItems(n, dimensao, g, col, dadosDistintos);
            }
        } else {//se for um treemap Item ele vai desenhar os glyphs
            TMNodeEncapsulator nodeEncapsulator = (TMNodeEncapsulator) nodo.getNode();
            TreeMapItem treemapItem = (TreeMapItem) nodeEncapsulator.getNode();
            defineDimension2DrawGlyph(dimensao, g, col, treemapItem, dadosDistintos);
        }
    }

    public void defineDimension2DrawGlyph(int dimensao, Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        switch (dimensao) {
            case 0:
                calcularPrimeiraDimensao(g, col, item, dadosDistintos);
                break;
            case 1:
                calcularSegundaDimensao(g, col, item, dadosDistintos);
                break;
            case 2:
                calcularTerceiraDimensao(g, col, item, dadosDistintos);
                break;
            case 3:
                calcularQuartaDimensao(g, col, item, dadosDistintos);
                break;
            case 4:
                calcularQuintaDimensao(g, col, item, dadosDistintos);
                break;
            default:
                System.err.println("Nao foi possível calcular a dimensão.");
        }
    }

    private void calcularPrimeiraDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPO_TEXTURA.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarTextura(g, item.getBounds(), Constantes.TIPO_TEXTURA[j]);
                break;
            }
        }
    }

    private void calcularSegundaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.getCor().length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarCorForma(g, item.getBounds(), Color.decode(Constantes.getCor()[j]));
                break;
            }
        }
    }

    private void calcularTerceiraDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPOS_FORMAS_GEOMETRICAS.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarFormaGeometrica(g, item.getBounds(), Constantes.TIPOS_FORMAS_GEOMETRICAS[j]);
                break;
            }
        }
    }

    private void calcularQuartaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.LETRAS_ALFABETO.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarLetrasAlfabeto(g, item.getBounds(), Constantes.LETRAS_ALFABETO[j]);
                break;
            }
        }
    }

    private void calcularQuintaDimensao(Graphics g, Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.NUMEROS.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                adicionarNumeros(g, item.getBounds(), Constantes.NUMEROS[j]);
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
}
