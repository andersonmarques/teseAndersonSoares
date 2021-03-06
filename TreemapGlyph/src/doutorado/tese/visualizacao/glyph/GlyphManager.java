/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import doutorado.tese.util.Metadados;
import doutorado.tese.visualizacao.glyph.alfabeto.Letra;
import doutorado.tese.visualizacao.glyph.formasgeometricas.FormaGeometrica;
import doutorado.tese.visualizacao.glyph.formasgeometricas.GeometryFactory;
import doutorado.tese.visualizacao.glyph.numeros.Numeral;
import doutorado.tese.util.tree.*;
import doutorado.tese.visualizacao.glyph.texture.Textura;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import net.bouthier.treemapAWT.TMNode;
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
    private boolean dimensao4Ativada;
    private String letraUtilizada;
    private static String[] shufflerColors;

    private HashMap<String, Integer> configs;
    private boolean decisionTreeActivate;

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

    public void setUseDecisionTree(boolean decisionTreeActivate) {
        this.decisionTreeActivate = decisionTreeActivate;
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

    public void paintTextura(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getTextura() != null) {
            treemapItem.getTextura().paint(g);
        }
    }

    public void paintCorForma(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getCorForma() != null) {
            treemapItem.getCorForma().paint(g);
        }

    }

    public void paintFormaGeometrica(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getFormaGeometrica() != null) {
            treemapItem.getFormaGeometrica().paint(g);
        }
    }

    public void paintLetrasAlfabeto(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getLetra() != null) {
            treemapItem.getLetra().paint(g);
        }
    }

    public void paintNumeros(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getNumero() != null) {
            treemapItem.getNumero().paint(g);
        }
    }

    public int prepareTextura(Rectangle bounds, String textura, TreeMapItem treemapItem) {
        Textura t = new Textura(bounds, textura);
        if (treemapItem != null) {
            treemapItem.setTextura(t);
        }
        return t.getArea();
    }

    public int prepareCorForma(Rectangle bounds, Color cor, TreeMapItem treemapItem) {
        FormaGeometrica corForma = GeometryFactory.create(bounds, cor, GeometryFactory.FORMAS.GLYPH_FORMAS.CIRCULO);
        corForma.setColor(cor);
        if (treemapItem != null) {
            treemapItem.setCorForma(corForma);
        }
        return corForma.getArea();
    }

    public int prepareFormaGeometrica(Rectangle bounds, GeometryFactory.FORMAS.GLYPH_FORMAS nomeForma, TreeMapItem treemapItem) {
//        System.out.println("++++ "+nomeForma+"\t"+bounds);
        FormaGeometrica formaGeometrica = GeometryFactory.create(bounds, null, nomeForma);
        if (treemapItem != null) {
            treemapItem.setFormaGeometrica(formaGeometrica);
        }
        return formaGeometrica.getArea();
    }

    public int prepareLetrasAlfabeto(Rectangle bounds, String simbolo, TreeMapItem treemapItem) {
        Letra letra = new Letra(bounds, simbolo, false);
        if (treemapItem != null) {
            treemapItem.setLetra(letra);
        }
        return letra.getArea();
    }

    /**
     * Metodo responsavel por instanciar um glyphs do tipo NUMERAL. Esse metodo
     * concatena o glyph do tipo LETRA, caso tenha sido ativado, ao glyphs
     * NUMERAL.
     *
     * @param bounds
     * @param simbolo
     * @param letra
     * @return
     */
    public int prepareNumeros(Rectangle bounds, String letra, String simbolo, TreeMapItem treemapItem) {
        Numeral numero = new Numeral(bounds, letra + simbolo, false);
        if (treemapItem != null) {
            treemapItem.setNumero(numero);
        }
        return numero.getArea();
    }

    /**
     * Metodo responsavel por instanciar um glyphs do tipo NUMERAL.
     *
     * @param bounds
     * @param simbolo
     * @return
     */
    public int prepareNumeros(Rectangle bounds, String simbolo, TreeMapItem treemapItem) {
        Numeral numero = new Numeral(bounds, simbolo, false);
        if (treemapItem != null) {
            treemapItem.setNumero(numero);
        }
        return numero.getArea();
    }

    public void prepare2Draw() {
        if (getRootNodeZoom() != null) {
            prepareGlyphsInTreeMapItems(getRootNodeZoom().getRoot());
        }
    }

    private void paintAnalyser(Graphics g, TMNodeModel nodo) {
        if (nodo instanceof TMNodeModelComposite) {//se for TreeMap Level
            TMNodeModelComposite pai = (TMNodeModelComposite) nodo;
            TMNode node = pai.getNode();

            for (TMNodeModel n : pai.getChildrenList()) {
                paintAnalyser(g, n);
            }
        } else {//se for um treemap Item ele vai desenhar os glyphs
            TMNodeEncapsulator nodeEncapsulator = (TMNodeEncapsulator) nodo.getNode();
            TreeMapItem item = (TreeMapItem) nodeEncapsulator.getNode();
            if (!decisionTreeActivate || item.getWhat2Draw()[0] == 1) {
                paintTextura(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[1] == 1) {
                paintCorForma(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[2] == 1) {
                paintFormaGeometrica(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[3] == 1) {
                paintLetrasAlfabeto(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[4] == 1) {
                paintNumeros(g, item);
            }
        }
    }

    public void paint(Graphics g) {
        if (getRootNodeZoom() != null) {
            paintAnalyser(g, getRootNodeZoom().getRoot());
        }
    }

    public void prepareGlyphsInTreeMapItems(TMNodeModel nodo) {
        if (nodo instanceof TMNodeModelComposite) {//se for TreeMap Level
            TMNodeModelComposite pai = (TMNodeModelComposite) nodo;
            for (TMNodeModel n : pai.getChildrenList()) {
                prepareGlyphsInTreeMapItems(n);
            }
        } else {//se for um treemap Item ele vai desenhar os glyphs
            TMNodeEncapsulator nodeEncapsulator = (TMNodeEncapsulator) nodo.getNode();
            TreeMapItem treemapItem = (TreeMapItem) nodeEncapsulator.getNode();
            prepareDimension2DrawGlyph(treemapItem);
        }
    }

    public void prepareDimension2DrawGlyph(TreeMapItem item) {
        double[] features = new double[15];
        limparGlyphsTreemapItem(item);
        features[Constantes.FEATURE_LARGURA] = item.getBounds().width;
        features[Constantes.FEATURE_ALTURA] = item.getBounds().height;
        features[Constantes.PRESENCA_COR_TREEMAP] = item.getColor().equals(Constantes.ALICE_BLUE) ? Constantes.AUSENTE : Constantes.PRESENTE;
        features[Constantes.FEATURE_AREA] = features[Constantes.FEATURE_LARGURA] * features[Constantes.FEATURE_ALTURA];

        double aspect = features[Constantes.FEATURE_ALTURA] > features[Constantes.FEATURE_LARGURA]
                ? features[Constantes.FEATURE_LARGURA] / features[Constantes.FEATURE_ALTURA]
                : features[Constantes.FEATURE_ALTURA] / features[Constantes.FEATURE_LARGURA];

        features[Constantes.FEATURE_ASPECT] = aspect;
        for (int dimensao = 0; dimensao < atributosEscolhidos.size(); dimensao++) {
            String colunaEscolhida = atributosEscolhidos.get(dimensao).toString();
            Coluna col = ManipuladorArquivo.getColuna(colunaEscolhida);
            List<String> dadosDistintos = colunaDadosDist.get(colunaEscolhida);
            if (atributosEscolhidos.get(dimensao).equals("---")) {
                dadosDistintos = null;
            }
            switch (dimensao) {
                case 0:
                    features[Constantes.AREA_TEXTURA] = dadosDistintos != null ? preparePrimeiraDimensao(col, item, dadosDistintos)
                            : prepareTextura(item.getBounds(), Constantes.TIPO_TEXTURA[0], null);
                    features[Constantes.PRESENCA_TEXTURA] = Constantes.PRESENTE;
                    break;
                case 1:
                    features[Constantes.AREA_CIRCULO_COLORIDO] = dadosDistintos != null ? prepareSegundaDimensao(col, item, dadosDistintos)
                            : prepareCorForma(item.getBounds(), Color.decode(getShufflerColors()[0]), null);
                    features[Constantes.PRESENCA_COR_FORMA] = Constantes.PRESENTE;
                    break;
                case 2:
                    features[Constantes.AREA_SHAPE] = dadosDistintos != null ? prepareTerceiraDimensao(col, item, dadosDistintos)
                            : prepareFormaGeometrica(item.getBounds(), GeometryFactory.FORMAS.GLYPH_FORMAS.values()[0], null);
                    features[Constantes.PRESENCA_FORMA] = Constantes.PRESENTE;
                    break;
                case 3:
                    if (!atributosEscolhidos.get(dimensao).equals("---")) {
                        dimensao4Ativada = true;
                        letraUtilizada = "";
                    }
                    features[Constantes.AREA_LETRA] = dadosDistintos != null ? prepareQuartaDimensao(col, item, dadosDistintos)
                            : prepareLetrasAlfabeto(item.getBounds(), Constantes.LETRAS_ALFABETO[0], null);
                    features[Constantes.PRESENCA_LETRA] = Constantes.PRESENTE;
                    break;
                case 4:
                    features[Constantes.AREA_NUMERO] = dadosDistintos != null ? prepareQuintaDimensao(col, item, dadosDistintos)
                            : prepareNumeros(item.getBounds(), Constantes.NUMEROS[0], null);
                    features[Constantes.PRESENCA_NUMERO] = Constantes.PRESENTE;
                    break;
                default:
                    System.err.println("Nao foi possível calcular a dimensão.");
            }
        }
        item.getWhat2Draw()[Constantes.PRESENCA_TEXTURA] = DecisionTreeClassifier.predict(features)[0];
        item.getWhat2Draw()[Constantes.PRESENCA_COR_FORMA] = DecisionTreeClassifier.predict(features)[1];
        item.getWhat2Draw()[Constantes.PRESENCA_FORMA] = DecisionTreeClassifier.predict(features)[2];
        item.getWhat2Draw()[Constantes.PRESENCA_LETRA] = DecisionTreeClassifier.predict(features)[3];
        item.getWhat2Draw()[Constantes.PRESENCA_NUMERO] = DecisionTreeClassifier.predict(features)[4];
    }

    public int preparePrimeiraDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPO_TEXTURA.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                return prepareTextura(item.getBounds(), Constantes.TIPO_TEXTURA[j], item);
            }
        }
        return 0;
    }

    public int prepareSegundaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        if (col.getDescription() == Metadados.Descricao.CONTINUOUS) {
            ColorInterpolator interpolator = new ColorInterpolator();
            interpolator.config(col.maiorMenorValues[0], col.maiorMenorValues[1], Color.orange, Color.decode("#4682B4"));
            Color cor = interpolator.interpolate(Double.parseDouble(item.getMapaDetalhesItem().get(col)));
            return prepareCorForma(item.getBounds(), cor, item);
        } else {
            for (int j = 0; j < Constantes.getCor().length; j++) {
                if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                    return prepareCorForma(item.getBounds(), Color.decode(getShufflerColors()[j]), item);
                }
            }
        }
        return 0;
    }

    public int prepareTerceiraDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < GeometryFactory.FORMAS.GLYPH_FORMAS.values().length - 1; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                return prepareFormaGeometrica(item.getBounds(), GeometryFactory.FORMAS.GLYPH_FORMAS.values()[j], item);
            }
        }
        return 0;
    }

    public int prepareQuartaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.LETRAS_ALFABETO.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                int result = prepareLetrasAlfabeto(item.getBounds(), Constantes.LETRAS_ALFABETO[j], item);
                letraUtilizada = Constantes.LETRAS_ALFABETO[j];
                return result;
            }
        }
        return 0;
    }

    public int prepareQuintaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.NUMEROS.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                if (dimensao4Ativada) {
                    return prepareNumeros(item.getBounds(), letraUtilizada, Constantes.NUMEROS[j], item);
                } else {
                    return prepareNumeros(item.getBounds(), Constantes.NUMEROS[j], item);
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
//        System.out.println("Root Node Zoom: "+this.rootNodeZoom.getRoot().getTitle());
    }

    /**
     * @return the shufflerColors
     */
    public static String[] getShufflerColors() {
        return shufflerColors;
    }

    private void limparGlyphsTreemapItem(TreeMapItem item) {
        item.setTextura(null);
        item.setCorForma(null);
        item.setFormaGeometrica(null);
        item.setLetra(null);
        item.setNumero(null);
    }
}
