/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.texture.Textura;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Anderson Soares
 */
public final class GlyphManager {

    private final ManipuladorArquivo manipulador;
    private final List<Object> atributosEscolhidos;
    private HashMap<String, Integer> colunaDadosDist;

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
                System.out.println("Coluna: " + c.getName() + " - dadosDistintos = " + dadosDistintos.size());
                colunaDadosDist.put(c.getName(), dadosDistintos.size());
            }
        }
    }

    public void paint(Graphics g) {
        for (int atri = 0; atri < atributosEscolhidos.size(); atri++) {
            if (!atributosEscolhidos.get(atri).equals("---")) {
                Coluna col = ManipuladorArquivo.getColuna(atributosEscolhidos.get(atri).toString());
                List<String> dadosDistintos = col.getDadosDistintos();
                Integer quantDadosDist = colunaDadosDist.get(col.getName());
                switch (quantDadosDist) {
                    case 2:
                        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                            TreeMapItem item = manipulador.getItensTreemap()[i];
                            Rectangle bounds = item.getBounds();
                            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                                Textura t = new Textura(bounds, Constantes.TipoTextura.PATTERN_DIAGDOTS.name());
                                t.paint(g);
                            } else {
                                Textura t = new Textura(bounds, Constantes.TipoTextura.PATTERN_PLUS.name());
                                t.paint(g);
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                            TreeMapItem item = manipulador.getItensTreemap()[i];
                            Rectangle bounds = item.getBounds();
                            if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                                Textura t = new Textura(bounds, Constantes.TipoTextura.PATTERN_DIAGDOTS.name());
                                t.paint(g);
                            } else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(1))) {
                                Textura t = new Textura(bounds, Constantes.TipoTextura.PATTERN_PLUS.name());
                                t.paint(g);
                            } else {
                                Textura t = new Textura(bounds, Constantes.TipoTextura.PATTERN_TIO.name());
                                t.paint(g);
                            }
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }

//        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
//            Rectangle bounds = manipulador.getItensTreemap()[i].getBounds();
////            Estrela star = new Estrela(bounds);
//            Retangulo e = new Retangulo(bounds);
//            Circulo c = new Circulo(bounds);
////            Triangulo t = new Triangulo(bounds); 
//
//            e.paint(g);
//            c.paint(g);
////            star.paint(g);
////            t.paint(g);
//
//        }
    }
}
