/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import javax.swing.ListModel;

/**
 *
 * @author Anderson Soares
 */
public final class GlyphManager {

    private final ManipuladorArquivo manipulador;
    private final ListModel<String> atributosEscolhidos;
    private HashMap<String, Integer> colunaDadosDist;

    public GlyphManager(ManipuladorArquivo manipulador, ListModel<String> atributosEscolhidos) {
        this.manipulador = manipulador;
        this.atributosEscolhidos = atributosEscolhidos;
        colunaDadosDist = new HashMap<>();
        analisarAtributosEscolhidos();
    }

    public void analisarAtributosEscolhidos() {
        for (int i = 0; i < atributosEscolhidos.getSize(); i++) {
            Coluna c = ManipuladorArquivo.getColuna(atributosEscolhidos.getElementAt(i));
            List<String> dadosDistintos = c.getDadosDistintos();
            System.out.println("Coluna: " + c.getName() + " - dadosDistintos = " + dadosDistintos.size());
            colunaDadosDist.put(c.getName(), dadosDistintos.size());

        }
    }

    public void paint(Graphics g) {
        for (int atri = 0; atri < atributosEscolhidos.getSize(); atri++) {
            Coluna col = ManipuladorArquivo.getColuna(atributosEscolhidos.getElementAt(atri));
            List<String> dadosDistintos = col.getDadosDistintos();
            Integer quantDadosDist = colunaDadosDist.get(col.getName());
            switch (quantDadosDist) {
                case 2:
                    for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                        TreeMapItem item = manipulador.getItensTreemap()[i];
                        Rectangle bounds = item.getBounds();
                        if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                            Retangulo e = new Retangulo(bounds);
                            e.paint(g);
                        } else {
                            Circulo c = new Circulo(bounds);
                            c.paint(g);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                        TreeMapItem item = manipulador.getItensTreemap()[i];
                        Rectangle bounds = item.getBounds();
                        if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                            Retangulo e = new Retangulo(bounds);
                            e.paint(g);
                        } else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(1))){
                            Circulo c = new Circulo(bounds);
                            c.paint(g);
                        }else{
                            Estrela e = new Estrela(bounds);
                            e.paint(g);
                        }
                    }
                    break;
                default:
                    throw new AssertionError();
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
