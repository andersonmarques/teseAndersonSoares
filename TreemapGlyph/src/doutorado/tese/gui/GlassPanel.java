/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.gui;

import doutorado.tese.io.ManipuladorArquivo;
import doutorado.tese.visualizacao.glyph.GlyphManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import net.bouthier.treemapAWT.TMNodeModelRoot;
import net.bouthier.treemapAWT.TMOnDrawFinished;
import net.bouthier.treemapAWT.TMUpdaterConcrete;
import net.bouthier.treemapAWT.TMView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Anderson Soares
 */
public class GlassPanel extends JPanel {

    private static final Logger logger = LogManager.getLogger(GlassPanel.class);
    private ManipuladorArquivo manipulador;
    private GlyphManager glyphManager;
    private TMNodeModelRoot nodeModelRoot;
    private TMView view;
    private boolean decisioTree;

    /**
     * Construtor chamado ao selecionar o checkbox indicando que os glyphs serao usados.
     */
    public GlassPanel() {
        setOpaque(false);
        setLayout(new GroupLayout(this));
        callListner();
    }

    public GlassPanel(Rectangle bounds) {
        setOpaque(false);
        setBounds(bounds);
        setLayout(new GroupLayout(this));
        callListner();
    }
    
    private void callListner(){
        TMUpdaterConcrete.listeners.add(new TMOnDrawFinished() {
            @Override
            public void onDrawFinished(String text) {
                logger.info("Acionando onDrawFinished()", text);
                glyphManager.setRootNodeZoom(view.getRootAnderson());   
                logger.info("Acionando prepare2Draw() a partir do onDrawFinished() - Root: "+glyphManager.getRootNodeZoom());
                glyphManager.prepare2Draw();//chamado para redesenhar os glyphs no drill-down
            }
        });
    }

    public void setAtributosEscolhidos(List<Object> atributosEscolhidos) {
        glyphManager = new GlyphManager(getManipulador(), atributosEscolhidos);
        glyphManager.setUseDecisionTree(decisioTree);
        glyphManager.setRootNodeZoom(view.getRootAnderson()); 
        logger.info("Acionando prepare2Draw() a partir do setAtributosEscolhidos() - Root: "+glyphManager.getRootNodeZoom());
        glyphManager.prepare2Draw();
    }    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 255, 0, 0));//painel com fundo transparente
        Rectangle r = getBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
             
        glyphManager.paint(g);
        g.dispose();
    }

    /**
     * @return the manipulador
     */
    public ManipuladorArquivo getManipulador() {
        return manipulador;
    }

    /**
     * @param manipulador the manipulador to set
     */
    public void setManipulador(ManipuladorArquivo manipulador) {
        this.manipulador = manipulador;
    }

    public void setTMView(TMView view) {
        this.view = view;
    }

    void setUseDecisionTree(boolean decisioTree) {
        this.decisioTree = decisioTree;
    }

}
