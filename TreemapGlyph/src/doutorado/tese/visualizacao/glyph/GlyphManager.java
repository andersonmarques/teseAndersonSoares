/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph;

import doutorado.tese.visualizacao.glyph.formasgeometricas.Estrela;
import doutorado.tese.visualizacao.glyph.formasgeometricas.Circulo;
import doutorado.tese.visualizacao.glyph.formasgeometricas.Cruz;
import doutorado.tese.visualizacao.glyph.formasgeometricas.Triangulo;
import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.alfabeto.Letra;
import doutorado.tese.visualizacao.glyph.numeros.Numeral;
import doutorado.tese.visualizacao.glyph.texture.Textura;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import java.awt.Color;
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
//                System.out.println("Coluna: " + c.getName() + " - dadosDistintos = " + dadosDistintos.size());
                colunaDadosDist.put(c.getName(), dadosDistintos.size());
            }
        }
    }

    private void calcularPrimeiraDimensao(Graphics g, Coluna col) {
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
                System.out.println("Sem opcoes...");
        }
    }

    public void paint(Graphics g) {
        for (int dimensao = 0; dimensao < atributosEscolhidos.size(); dimensao++) {
            if (!atributosEscolhidos.get(dimensao).equals("---")) {
                Coluna col = ManipuladorArquivo.getColuna(atributosEscolhidos.get(dimensao).toString());
                switch (dimensao) {
                    case 0:
                        calcularPrimeiraDimensao(g, col);
                        break;
                    case 1:
                        calcularSegundaDimensao(g, col);
                        break;
                    case 2:
                        calcularTerceiraDimensao(g, col);
                        break;
                    case 3:
                        calcularQuartaDimensao(g, col);
                        break;
                    case 4:
                        calcularQuintaDimensao(g, col);
                        break;
                    default:
                        System.err.println("Nao foi possível calcular a dimensão.");
                }
            }
        }
    }

    private void calcularSegundaDimensao(Graphics g, Coluna col) {
        List<String> dadosDistintos = col.getDadosDistintos();
        Integer quantDadosDist = colunaDadosDist.get(col.getName());
        switch (quantDadosDist) {
            case 2:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    Circulo c = new Circulo(bounds);
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        g.setColor(Color.decode(Constantes.getCor()[0]));
                        c.paint(g);
                    } else {
                        g.setColor(Color.decode(Constantes.getCor()[1]));
                        c.paint(g);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    Circulo c = new Circulo(bounds);
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        g.setColor(Color.decode(Constantes.getCor()[0]));
                        c.paint(g);
                    } else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(1))) {
                        g.setColor(Color.decode(Constantes.getCor()[1]));
                        c.paint(g);
                    } else {
                        g.setColor(Color.decode(Constantes.getCor()[2]));
                        c.paint(g);
                    }
                }
                break;
            default:
                System.out.println("Sem opcoes...");
        }
    }

    private void calcularTerceiraDimensao(Graphics g, Coluna col) {
        List<String> dadosDistintos = col.getDadosDistintos();
        Integer quantDadosDist = colunaDadosDist.get(col.getName());
        switch (quantDadosDist) {
            case 2:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        Triangulo t = new Triangulo(bounds);
                        t.paint(g);
                    } else {
                        Estrela r = new Estrela(bounds);
                        r.paint(g);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        Triangulo t = new Triangulo(bounds);
                        t.paint(g);
                    } else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(1))) {
                        Estrela r = new Estrela(bounds);
                        r.paint(g);
                    } else {
                        Cruz cruz = new Cruz(bounds);
                        cruz.paint(g);
                    }
                }
                break;
            default:
                System.out.println("Sem opcoes...");
        }
    }

    private void calcularQuartaDimensao(Graphics g, Coluna col) {
        List<String> dadosDistintos = col.getDadosDistintos();
        Integer quantDadosDist = colunaDadosDist.get(col.getName());
        switch (quantDadosDist) {
            case 2:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        Letra t = new Letra(bounds, "A");
                        t.paint(g);
                    } else {
                        Letra t = new Letra(bounds, "B");
                        t.paint(g);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    Circulo c = new Circulo(bounds);
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        Letra t = new Letra(bounds, "A");
                        t.paint(g);
                    } else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(1))) {
                        Letra t = new Letra(bounds, "B");
                        t.paint(g);
                    } else {
                        Letra t = new Letra(bounds, "C");
                        t.paint(g);
                    }
                }
                break;
                case 4:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    Circulo c = new Circulo(bounds);
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        Letra t = new Letra(bounds, "A");
                        t.paint(g);
                    } else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(1))) {
                        Letra t = new Letra(bounds, "B");
                        t.paint(g);
                    }else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(2))) {
                        Letra t = new Letra(bounds, "C");
                        t.paint(g);
                    } else {
                        Letra t = new Letra(bounds, "D");
                        t.paint(g);
                    }
                }
                break;
            default:
                System.out.println("Sem opcoes...");
        }
    }

    private void calcularQuintaDimensao(Graphics g, Coluna col) {
        List<String> dadosDistintos = col.getDadosDistintos();
        Integer quantDadosDist = colunaDadosDist.get(col.getName());
        switch (quantDadosDist) {
            case 2:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        Numeral t = new Numeral(bounds, "1");
                        t.paint(g);
                    } else {
                        Numeral t = new Numeral(bounds, "2");
                        t.paint(g);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < manipulador.getItensTreemap().length; i++) {
                    TreeMapItem item = manipulador.getItensTreemap()[i];
                    Rectangle bounds = item.getBounds();
                    Circulo c = new Circulo(bounds);
                    if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(0))) {
                        Numeral t = new Numeral(bounds, "1");
                        t.paint(g);
                    } else if (item.getMapaDetalhesItem().get(col).equalsIgnoreCase(dadosDistintos.get(1))) {
                        Numeral t = new Numeral(bounds, "2");
                        t.paint(g);
                    } else {
                        Numeral t = new Numeral(bounds, "3");
                        t.paint(g);
                    }
                }
                break;
            default:
                System.out.println("Sem opcoes...");
        }
    }
}
