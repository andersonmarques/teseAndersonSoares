/*
 * TMModel_Draw.java
 * www.bouthier.net
 *
 * The MIT License :
 * -----------------
 * Copyright (c) 2001 Christophe Bouthier
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package doutorado.tese.visualizacao.treemap.treemapAPI;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.util.Coluna;
import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.treemap.TreeMapItem;
import doutorado.tese.visualizacao.treemap.TreeMapLevel;
import doutorado.tese.visualizacao.treemap.TreeMapNode;
import java.awt.Color;
import java.awt.Paint;
import java.util.List;

import net.bouthier.treemapAWT.TMComputeDrawAdapter;

/**
 * The TMModel_Draw class implements an example of a TMComputeDrawAdapter for a
 * TMFileModelNode. It use the date of last modification as color, and the name
 * of the file as tooltip.
 * <P>
 * The color legend is :
 * <UL>
 * <IL> white for files less than a hour old</IL>
 * <IL> green for files less than a day old</IL>
 * <IL> yellow for files less than a week old</IL>
 * <IL> orange for files less than a month old</IL>
 * <IL> red for files less than a year old</IL>
 * <IL> blue for files more than a year old</IL>
 * </UL>
 *
 * @author Christophe Bouthier [bouthier@loria.fr]
 * @version 2.5
 */
public class TMModel_Draw
        extends TMComputeDrawAdapter {

    private String itemCor;


    /* --- TMComputeSizeAdapter -- */
    public boolean isCompatibleWithObject(Object node) {
        return node instanceof TreeMapNode;
    }

    @Override
    public Paint getFillingOfObject(Object node) {
        if (node instanceof TreeMapLevel) {
            TreeMapLevel level = (TreeMapLevel) node;
            for (TreeMapNode filhos : level.getChildren()) {
                getFillingOfObject(filhos);
            }
        } else {
            TreeMapItem nodeItem = (TreeMapItem) node;
            if (!itemCor.equals("---")) {
                Coluna c = ManipuladorArquivo.getColuna(getItemCor());
                List<String> dadosDistintos = c.getDadosDistintos();
                for (int j = 0; j < Constantes.getCor().length; j++) {
                    if (nodeItem.getMapaDetalhesItem().get(c).equalsIgnoreCase(dadosDistintos.get(j))) {
                        nodeItem.setColor(Color.decode(Constantes.getCor()[j]));
                        break;
                    }
                }
                return nodeItem.getColor();
            }
        }
        return Color.decode(Constantes.getCor()[Constantes.getCor().length - 1]);
    }

    public String getTooltipOfObject(Object node) {
        if (node instanceof TreeMapNode) {
            TreeMapNode n = (TreeMapNode) node;
            String name = n.getLabel();
//            long modTime = n.lastModified();
//            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
//            DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);
//            String date = df.format(new Date(modTime));
//            String time = tf.format(new Date(modTime));

            String tooltip = "<html>" + name + "<p>"
                    + n.getBounds();
            return tooltip;
        }
        return "";
    }

    @Override
    public String getTitleOfObject(Object node) {
        if (node instanceof TreeMapNode) {
            TreeMapNode n = (TreeMapNode) node;
            return n.getLabel();
        }
        return "";
    }

    @Override
    public Paint getColorTitleOfObject(Object node) {
        if (node instanceof TreeMapNode) {
            TreeMapNode n = (TreeMapNode) node;
            return Color.black;
        }
        return Color.black;
    }

    /**
     * @return the itemCor
     */
    public String getItemCor() {
        return itemCor;
    }

    /**
     * @param itemCor the itemCor to set
     */
    public void setItemCor(String itemCor) {
        this.itemCor = itemCor;
    }

}
