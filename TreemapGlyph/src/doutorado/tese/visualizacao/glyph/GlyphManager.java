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
import doutorado.tese.visualizacao.glyph.formasgeometricas.Arco;
import doutorado.tese.visualizacao.glyph.formasgeometricas.Seta;
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
    private List<Object> formasEscolhidas;

    private HashMap<String, List<String>> colunaDadosDist;
    private TMNodeModelRoot rootNodeZoom;
    private boolean dimensao3Ativada;
    private GeometryFactory.FORMAS.GLYPH_FORMAS formaUsada;
    private static String[] shufflerColors;

    private HashMap<String, Integer> configs;
    private boolean decisionTreeActivate;

    public GlyphManager() {
        this.configs = new HashMap<>();
    }

    public GlyphManager(ManipuladorArquivo manipulador, List<Object> atributosEscolhidos,List<Object> formasEscolhidas) {
        this.manipulador = manipulador;
        this.atributosEscolhidos = atributosEscolhidos;
        this.formasEscolhidas = formasEscolhidas;
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

    public void paintCirculo(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getCirculo() != null) {
            treemapItem.getCirculo().paint(g);
        }
    }

    public void paintCorForma(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getCorForma() != null) {
            treemapItem.getCorForma().paint(g);
        }
    }

    public void paintCorFormaSupEsq(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getFormaSupEsq() != null) {
            treemapItem.getFormaSupEsq().paint(g);
        }
    }

    public void paintCorFormaInfEsq(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getFormaInfEsq() != null) {
            treemapItem.getFormaInfEsq().paint(g);
        }
    }

    public void paintCorFormaSupDir(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getFormaSupDir() != null) {
            treemapItem.getFormaSupDir().paint(g);
        }
    }

    public void paintCorFormaInfDir(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getFormaInfDir() != null) {
            treemapItem.getFormaInfDir().paint(g);
        }
    }

    public void paintFormaGeometrica(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getFormaGeometrica() != null) {
            treemapItem.getFormaGeometrica().paint(g);
        }
    }

    public void paintFormaGeometricaInterna(Graphics g, TreeMapItem treemapItem) {
        if (treemapItem.getFormaGeometricaInterna() != null) {
            treemapItem.getFormaGeometricaInterna().paint(g);
        }
    }

    public int prepareTextura(Rectangle bounds, String textura, TreeMapItem treemapItem) {
        Textura t = new Textura(bounds, textura);
        if (treemapItem != null) {
            treemapItem.setTextura(t);
        }
        return t.getArea();
    }

    public int prepareFormaGeometrica(Rectangle bounds, GeometryFactory.FORMAS.GLYPH_FORMAS nomeForma, String angulo, TreeMapItem treemapItem) {
        FormaGeometrica formaGeometrica = GeometryFactory.create(bounds, null, nomeForma, angulo,0.4f,"5");
        System.out.println("prepareFormaGeometrica - angulo: " + ((Arco) formaGeometrica).getAngulo());
        if (treemapItem != null) {
            treemapItem.setFormaGeometrica(formaGeometrica);
        }
        return formaGeometrica.getArea();
    }

    public int prepareCorFormaSupEsq(Rectangle bounds, GeometryFactory.FORMAS.GLYPH_FORMAS nomeForma, Color cor, TreeMapItem treemapItem) {
        FormaGeometrica corForma;
        corForma = GeometryFactory.create(bounds, cor, nomeForma, "0", 0.25f, "1");
        corForma.setColor(cor);
        if (treemapItem != null) {
            treemapItem.setFormaSupEsq(corForma);
        }
        return corForma.getArea();
    }

    public int prepareCorFormaInfEsq(Rectangle bounds, GeometryFactory.FORMAS.GLYPH_FORMAS nomeForma, Color cor, TreeMapItem treemapItem) {
        FormaGeometrica corForma;
        corForma = GeometryFactory.create(bounds, cor, nomeForma, "0", 0.25f, "2");
        corForma.setColor(cor);
        if (treemapItem != null) {
            treemapItem.setFormaInfEsq(corForma);
        }
        return corForma.getArea();
    }

    public int prepareCorFormaInfDir(Rectangle bounds, GeometryFactory.FORMAS.GLYPH_FORMAS nomeForma, Color cor, TreeMapItem treemapItem) {
        FormaGeometrica corForma;
        corForma = GeometryFactory.create(bounds, cor, nomeForma, "0", 0.25f, "3");
        corForma.setColor(cor);
        if (treemapItem != null) {
            treemapItem.setFormaInfDir(corForma);
        }
        return corForma.getArea();
    }

    public int prepareCorFormaSupDir(Rectangle bounds, GeometryFactory.FORMAS.GLYPH_FORMAS nomeForma, Color cor, TreeMapItem treemapItem) {
        FormaGeometrica corForma;
        corForma = GeometryFactory.create(bounds, cor, nomeForma, "0", 0.25f, "4");
        corForma.setColor(cor);
        if (treemapItem != null) {
            treemapItem.setFormaSupDir(corForma);
        }
        return corForma.getArea();
    }

    public int prepareCirculoCor(Rectangle bounds, GeometryFactory.FORMAS.GLYPH_FORMAS nomeForma, Color cor, TreeMapItem treemapItem) {
        FormaGeometrica corForma;
        corForma = GeometryFactory.create(bounds, cor, nomeForma, "0", 0.1f, "5");
        corForma.setColor(cor);
        if (treemapItem != null) {
            treemapItem.setCirculo(corForma);
        }
        return corForma.getArea();
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
                paintFormaGeometrica(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[2] == 1) {
                paintCorFormaSupEsq(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[3] == 1) {
                paintCorFormaInfEsq(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[4] == 1) {
                paintCorFormaInfDir(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[5] == 1) {
                paintCorFormaSupDir(g, item);
            }
            if (!decisionTreeActivate || item.getWhat2Draw()[6] == 1) {
                paintCirculo(g, item);
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

    private void limparGlyphsTreemapItem(TreeMapItem item) {
        item.setTextura(null);
        item.setFormaGeometrica(null);
        item.setFormaSupEsq(null);
        item.setFormaInfEsq(null);
        item.setFormaInfDir(null);
        item.setFormaSupDir(null);
        item.setCirculo(null);
    }

    public void prepareDimension2DrawGlyph(TreeMapItem item) {
        double[] features = new double[15];

        limparGlyphsTreemapItem(item);
        features[6] = item.getBounds().width;
        features[5] = item.getBounds().height;
        features[9] = item.getColor().equals(Constantes.ALICE_BLUE) ? 0 : 1;
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
                        features[10] = preparePrimeiraDimensao(col, item, dadosDistintos);
                        features[0] = 1;
                        break;
                    case 1:
                        features[11] = prepareSegundaDimensao(col, item, dadosDistintos);
                        features[1] = 1;
                        break;
                    case 2:
                        features[12] = prepareTerceiraDimensao(col, item, dadosDistintos,formasEscolhidas);
                        features[2] = 1;
                        break;
                    case 3:
                        features[13] = prepareQuartaDimensao(col, item, dadosDistintos,formasEscolhidas);
                        features[3] = 1;

                        break;
                    case 4:
                        features[14] = prepareQuintaDimensao(col, item, dadosDistintos,formasEscolhidas);
                        features[4] = 1;
                        break;
                    case 5:
                        features[14] = prepareSextaDimensao(col, item, dadosDistintos,formasEscolhidas);
                        features[4] = 1;
                        break;
                    case 6:
                        features[14] = prepareSetimaDimensao(col, item, dadosDistintos);
                        features[4] = 1;
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
    }

    public int preparePrimeiraDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.TIPO_TEXTURA.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                return prepareTextura(item.getBounds(), Constantes.TIPO_TEXTURA[j], item);
            }
        }
        return 0;
    }

    //aqui e o arco
    public int prepareSegundaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.ANGLE.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                return prepareFormaGeometrica(item.getBounds(), GeometryFactory.FORMAS.GLYPH_FORMAS.ARCO, Constantes.ANGLE[j], item);
            }
        }
        return 0;
    }
    //novos niveis 
    //nivel 3 cruz no canto superior direito do glyph
    public int prepareTerceiraDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos,List<Object> FormasEscolhidas) {
        for (int j = 0; j < GeometryFactory.FORMAS.GLYPH_FORMAS.values().length - 2; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                int result = prepareCorFormaSupEsq(item.getBounds(),GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(FormasEscolhidas.get(0))),Color.decode(Constantes.getCorFormas()[j]), item);
                return result;
            }
        }
        return 0;
    }

    public int prepareQuartaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos,List<Object> FormasEscolhidas) {
        for (int j = 0; j < GeometryFactory.FORMAS.GLYPH_FORMAS.values().length - 1; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                int result = prepareCorFormaInfEsq(item.getBounds(), GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(FormasEscolhidas.get(1))), Color.decode(Constantes.getCorFormas()[j]), item);
                return result;
            }
        }
        return 0;
    }

    public int prepareQuintaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos,List<Object> FormasEscolhidas) {
        for (int j = 0; j < GeometryFactory.FORMAS.GLYPH_FORMAS.values().length - 1; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                int result = prepareCorFormaInfDir(item.getBounds(),GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(FormasEscolhidas.get(2))), Color.decode(Constantes.getCorFormas()[j]), item);
                return result;
            }
        }
        return 0;
    }

    public int prepareSextaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos,List<Object> FormasEscolhidas) {
        for (int j = 0; j < GeometryFactory.FORMAS.GLYPH_FORMAS.values().length - 1; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                int result = prepareCorFormaSupDir(item.getBounds(), GeometryFactory.FORMAS.GLYPH_FORMAS.valueOf((String)(FormasEscolhidas.get(3))), Color.decode(Constantes.getCorFormas()[j]), item);
                return result;
            }
        }
        return 0;
    }

    public int prepareSetimaDimensao(Coluna col, TreeMapItem item, List<String> dadosDistintos) {
        for (int j = 0; j < Constantes.visivel.length; j++) {
            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(j))) {
                if(Constantes.visivel[j] == "1"){
                    return prepareCirculoCor(item.getBounds(), GeometryFactory.FORMAS.GLYPH_FORMAS.CIRCULO, Color.black, item);
                }
                else{
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
        System.out.println("Root Node Zoom: " + this.rootNodeZoom.getRoot().getTitle());
    }

    /**
     * @return the shufflerColors
     */
    public static String[] getShufflerColors() {
        return shufflerColors;
    }
}
