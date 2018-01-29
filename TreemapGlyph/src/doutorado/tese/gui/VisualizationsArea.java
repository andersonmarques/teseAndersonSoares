/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.gui;

import doutorado.tese.visualizacao.treemap.treemapAPI.TMModel_Draw;
import doutorado.tese.visualizacao.treemap.treemapAPI.TMModel_Size;
import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Flags;
import doutorado.tese.visualizacao.glyph.Circulo;
import doutorado.tese.visualizacao.glyph.Estrela;
import doutorado.tese.visualizacao.glyph.Retangulo;
import doutorado.tese.visualizacao.glyph.StarGlyph;
import doutorado.tese.visualizacao.glyph.Triangulo;
import doutorado.tese.visualizacao.treemap.Rect;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import doutorado.tese.visualizacao.treemap.TreeMapLevel;
import doutorado.tese.visualizacao.treemap.TreeMapNode;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import net.bouthier.treemapAWT.TMModelNode;
import net.bouthier.treemapAWT.TMOnDrawFinished;
import net.bouthier.treemapAWT.TMNodeModel;
import net.bouthier.treemapAWT.TMNodeModelComposite;
import net.bouthier.treemapAWT.TMThreadModel;
import net.bouthier.treemapAWT.TMUpdaterConcrete;
import net.bouthier.treemapAWT.TMView;
import net.bouthier.treemapAWT.TreeMap;

/**
 *
 * @author Anderson Soares
 */
public class VisualizationsArea {

    private final ManipuladorArquivo manipulador;
    private Queue<String> hierarquiaFila;
    private String labelColumn = "";
    private TreeMapNode root;
    //variaveis para a API do Treemap
    private TMModelNode modelTree = null; // the model of the demo tree
    private TreeMap treeMap = null; // the treemap builded
    private TMView view = null;
    //Star Glyph
    private StarGlyph[] starGlyphs;

    public VisualizationsArea(int w, int h, ManipuladorArquivo manipulador,
            String itemTamanho, String[] itensHierarquia, String itemLegenda,
            List<String> variaveisStarGlyph) {
        this.manipulador = manipulador;
        this.hierarquiaFila = new LinkedList<>();

        Rectangle rect = new Rectangle(0, 0, w, h);
        root = new TreeMapLevel(new Rect(rect.x, rect.y, rect.width, rect.height));//TMModelNode
        root.setPaiLevel(null);
        root.setRaiz(true);
        root.setLabel("ROOT");
        //é possivel imprimir a arvore chamando o metodo printTree()
        modelTree = createTree(itensHierarquia, itemTamanho, itemLegenda);
        treeMap = new TreeMap(modelTree);

        TMModel_Size cSize = new TMModel_Size();
        TMModel_Draw cDraw = new TMModel_Draw();

        this.view = treeMap.getView(cSize, cDraw);//getView() retorna um JPainel

        this.view.getAlgorithm().setBorderSize(15);
        this.view.setBounds(rect);
        
//        setLayoutView();
//        this.view.setLayout(new FlowLayout());
//        this.view.setPreferredSize(new Dimension(this.view.getBounds().width, this.view.getBounds().height));
        TMOnDrawFinished listener = (new TMOnDrawFinished() {
            @Override
            public void onDrawFinished(String t) {
                getRootBoundsFromView(t);
            }
        });
        TMThreadModel.listener = listener;
        TMUpdaterConcrete.listener = listener;

        if (Flags.isShowStarGlyph()) {
            acionarStarGlyph(variaveisStarGlyph);
        }

        if (Flags.isShowGlyph()) {
            acionarGlyphs();
        }
        //acionarGLyphFX();
    }

    public void acionarGlyphs() {
        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {//manipulador.getItensTreemap().length
            Estrela e = new Estrela(manipulador.getItensTreemap()[i].getBounds());
            Triangulo t = new Triangulo(manipulador.getItensTreemap()[i].getBounds());
            Circulo c = new Circulo(manipulador.getItensTreemap()[i].getBounds());
            Retangulo r = new Retangulo(manipulador.getItensTreemap()[i].getBounds());
            this.view.add(r);
            this.view.add(e);
            this.view.add(c);
            this.view.add(t);
        }
    }

    public void acionarStarGlyph(List<String> variaveisStarGlyph) {
        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {//manipulador.getItensTreemap().length
            StarGlyph starGlyph = new StarGlyph(manipulador.getItensTreemap()[i].getBounds(), variaveisStarGlyph);
//            System.out.println("Item: " + manipulador.getItensTreemap()[i].getLabel()
//                    + " Rect: " + manipulador.getItensTreemap()[i].getBounds());
            starGlyph.setQuantVar(variaveisStarGlyph.size());
            starGlyph.setManipulador(manipulador);
            starGlyph.setVisible(true);
            this.view.add(starGlyph);
        }
//        this.view.repaint();
    }

    public void getRootBoundsFromView(String t) {
        TMNodeModel nodeModel = this.view.getAlgorithm().getRoot();//TMNodeMode
        if (nodeModel != null) {
            Rectangle area = nodeModel.getArea();
            this.root.setBounds(area);

            setAreaNodesTree(this.root, nodeModel);

        }
    }

    public void setAreaNodesTree(TMModelNode item, TMNodeModel nodoModel) {
        TreeMapNode nodo = (TreeMapNode) item;

        if (nodoModel instanceof TMNodeModelComposite) {
//            filhoModel = ((TMNodeModelComposite) nodoModel).getChildrenList().get(i);
            for (int i = 0; i < nodo.getChildren().size(); i++) {

                TreeMapNode filho = nodo.getChildren().get(i);

                TMNodeModel filhoModel = ((TMNodeModelComposite) nodoModel).getChildrenList().get(i);

                filho.setBounds(filhoModel.getArea());
                filho.setLabel(filhoModel.getTitle());
//            System.out.println("Titulo: "+filho.getLabel()+" - "+filho.getBounds());
                setAreaNodesTree(filho, filhoModel);
            }
        }

    }

    public void setHierarchy(String[] hierarquia) {
        for (TreeMapItem treeMapItem : manipulador.getItensTreemap()) {
            hierarquiaFila.addAll(Arrays.asList(hierarquia));
            root.inserirFilhos(hierarquiaFila, treeMapItem, root);
        }
    }

    public void setSizeColumn(Coluna column) {
        this.root.setSize(column);
        this.root.organize();
    }

    public void setLabelColumn(Coluna column) {
        this.labelColumn = column.getName();
        setTreemapItemLabel(this.root.getChildren(), true);
    }

    public void setTreemapItemLabel(List<TreeMapNode> itemsFilhos, boolean isUseLabel) {
        itemsFilhos.forEach((filho) -> {
            if (filho instanceof TreeMapItem) {
                filho.setUseLabel(isUseLabel);
                if (filho.isUseLabel()) {
                    filho.setLabel(filho.getMapaDetalhesItem().get(ManipuladorArquivo.getColuna(labelColumn)));
                } else {
                    filho.setLabel("");
                }
            } else {
                TreeMapLevel level = (TreeMapLevel) filho;
                setTreemapItemLabel(level.getChildren(), isUseLabel);
            }
        });
    }

    private TreeMapNode createTree(String[] hierarquia, String itemTamanho, String itemLegenda) {
        setHierarchy(hierarquia);
        setSizeColumn(ManipuladorArquivo.getColuna(itemTamanho));
        if (!Flags.isShowLegenda()) {
            setTreemapItemLabel(root.getChildren(), false);
        } else {
            setLabelColumn(ManipuladorArquivo.getColuna(itemLegenda));
        }
        return root;
    }

    public void printTree(TMModelNode item, String appender) {
        TreeMapNode nodo = (TreeMapNode) item;
        System.out.println(appender + nodo.getLabel() + " - " + nodo.getSize());
        nodo.getChildren().forEach(each -> printTree(each, appender + appender));
    }

    /**
     * @return the treeMap
     */
    public TreeMap getTreeMap() {
        return treeMap;
    }

    /**
     * @return the view
     */
    public TMView getView() {
        return view;
    }
}
