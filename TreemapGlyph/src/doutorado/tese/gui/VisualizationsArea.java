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
import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.StarGlyph;
import doutorado.tese.visualizacao.treemap.Rect;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import doutorado.tese.visualizacao.treemap.TreeMapLevel;
import doutorado.tese.visualizacao.treemap.TreeMapNode;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    private TreeMapNode fixedRoot;

    //variaveis para a API do Treemap
    private TMModelNode modelTree = null; // the model of the demo tree
    private TreeMap treeMap = null; // the treemap builded
    private TMView view = null;
    //Star Glyph
    private StarGlyph[] starGlyphs;
    private String itemCor;

    public VisualizationsArea(int w, int h, ManipuladorArquivo manipulador,
            String itemTamanho, String[] itensHierarquia, String itemLegenda, String itemCor) {
        this.manipulador = manipulador;
        this.hierarquiaFila = new LinkedList<>();

        Rectangle rect = new Rectangle(0, 0, w, h);
        root = new TreeMapLevel(new Rect(rect.x, rect.y, rect.width, rect.height));//TMModelNode
        fixedRoot = root;
        root.setPaiLevel(null);
        root.setRaiz(true);
        root.setLabel("ROOT");
        //é possivel imprimir a arvore chamando o metodo printTree()
        modelTree = createTree(itensHierarquia, itemTamanho, itemLegenda, itemCor);
        treeMap = new TreeMap(modelTree);

        TMModel_Size cSize = new TMModel_Size();
        TMModel_Draw cDraw = new TMModel_Draw();
        cDraw.setItemCor(itemCor);
        cDraw.getFillingOfObject(root);

        this.view = treeMap.getView(cSize, cDraw);//getView() retorna um JPainel

        this.view.getAlgorithm().setBorderSize(15);
        this.view.setBounds(rect);

        TMOnDrawFinished listener = (new TMOnDrawFinished() {
            @Override
            public void onDrawFinished(String t) {
                getRootBoundsFromView(t);
            }
        });
        TMThreadModel.listener = listener;
        TMUpdaterConcrete.listener = listener;
    }

    public void acionarStarGlyph(List<String> variaveisStarGlyph) {
        for (int i = 0; i < manipulador.getItensTreemap().length; i++) {//manipulador.getItensTreemap().length
            StarGlyph starGlyph = new StarGlyph(manipulador.getItensTreemap()[i].getBounds(), variaveisStarGlyph);
            starGlyph.setQuantVar(variaveisStarGlyph.size());
            starGlyph.setManipulador(manipulador);
            starGlyph.setVisible(true);
            this.view.add(starGlyph);
        }
    }

    public void getRootBoundsFromView(String t) {
        TMNodeModel nodeModel = this.view.getAlgorithm().getRoot();//TMNodeModel
        if (nodeModel != null) {
            Rectangle area = nodeModel.getArea();
            this.root.setBounds(area);

            this.root = this.fixedRoot;
            equalizeRoots(root, nodeModel);

            setAreaNodesTree(this.root, nodeModel);
        }
    }

    public boolean equalizeRoots(TreeMapNode equalized, TMNodeModel nodeModel) {
        if (equalized.getLabel().equals(nodeModel.getTitle())) {
            this.root = equalized;
            return true;
        } else {
            for (TreeMapNode e : equalized.getChildren()) {
                if (equalizeRoots(e, nodeModel)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void setAreaNodesTree(TMModelNode item, TMNodeModel nodoPaiModel) {
        TreeMapNode nodo = (TreeMapNode) item;

        if (nodoPaiModel instanceof TMNodeModelComposite) {
            for (int i = 0; i < nodo.getChildren().size(); i++) {

                TreeMapNode filho = nodo.getChildren().get(i);
                TMNodeModel filhoModel = ((TMNodeModelComposite) nodoPaiModel).getChildrenList().get(i);

                filho.setBounds(filhoModel.getArea());
                filho.setLabel(filhoModel.getTitle());
                filho.setParent(nodo);
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

    public void setTreemapItemLabel(List<TreeMapNode> itensFilhos, boolean isUseLabel) {
        itensFilhos.forEach((filho) -> {
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

//    public void setTreemapItemColor(List<TreeMapNode> itensFilhos) {
//        itensFilhos.forEach((filho) -> {
//            if (filho instanceof TreeMapItem) {
//                if (!itemCor.equals("---")) {
//                    Coluna c = ManipuladorArquivo.getColuna(itemCor);
//                    List<String> dadosDistintos = c.getDadosDistintos();
//                    for (int j = 0; j < Constantes.getCor().length; j++) {
//                        if (filho.getMapaDetalhesItem().get(c).equalsIgnoreCase(dadosDistintos.get(j))) {
//                            filho.setColor(Color.decode(Constantes.getCor()[j]));
//                            break;
//                        }
//                    }
//                }
//            } else {
//                TreeMapLevel level = (TreeMapLevel) filho;
//                setTreemapItemColor(level.getChildren());
//            }
//        });
//    }

//    public void setColorColumn(String itemCor) {
//        this.itemCor = itemCor;
////        setTreemapItemColor(this.root.getChildren());
//    }

    private TreeMapNode createTree(String[] hierarquia, String itemTamanho, String itemLegenda, String itemCor) {
        setHierarchy(hierarquia);
        setSizeColumn(ManipuladorArquivo.getColuna(itemTamanho));
//        setColorColumn(itemCor);
        if (!Constantes.isShowLegenda()) {
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
