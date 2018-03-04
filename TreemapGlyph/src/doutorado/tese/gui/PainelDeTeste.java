/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.gui;

import doutorado.tese.util.Constantes;
import doutorado.tese.visualizacao.glyph.GlyphManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 *
 * @author Elvis (LABVIS)
 */
public class PainelDeTeste extends javax.swing.JPanel {

    private HashMap<String, Integer> configs;
    private HashMap<String, Boolean> output;
    private HashMap<String, Integer> areas;
    private AreaCallback areaCallback;

    /**
     * Creates new form PainelDeTeste
     */
    public PainelDeTeste() {
        configs = new HashMap<>();
        output = new HashMap<>();
        areas = new HashMap<>();
        initComponents();
        this.areaCallback = new AreaCallback() {
            @Override
            public void areaUpdated(HashMap<String, Integer> areas) {
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;

        Color coritem = configs.get("coritem")>=0 ? 
                Color.decode(Constantes.getCor()[configs.get("coritem")]):
                Color.decode(Constantes.getCor()[Constantes.getCor().length-1]);
        g2d.setColor(coritem);
        g2d.fillRect(configs.get("x"), configs.get("y"), configs.get("width"), configs.get("height"));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(configs.get("x"), configs.get("y"), configs.get("width"), configs.get("height"));

        GlyphManager manager = new GlyphManager();
        Rectangle bounds = new Rectangle(configs.get("x"), configs.get("y"), configs.get("width"), configs.get("height"));
        System.out.println("width: "+configs.get("width") +" height: "+configs.get("height"));
        int at = 0, ac = 0, af = 0, al = 0, an =0;
        if(configs.get("textura") >=0)
            at = manager.adicionarTextura(g, bounds, Constantes.TIPO_TEXTURA[configs.get("textura")]);
        if(configs.get("cor") >=0)
            ac = manager.adicionarCorForma(g, bounds, Color.decode(Constantes.getCorGlyphs()[configs.get("cor")]));
        if(configs.get("forma") >=0)
            af = manager.adicionarFormaGeometrica(g, bounds, Constantes.TIPOS_FORMAS_GEOMETRICAS[configs.get("forma")]);
        if(configs.get("letra") >=0 && configs.get("numero") >=0){
            an = manager.adicionarNumeros(g, bounds,Constantes.LETRAS_ALFABETO[configs.get("letra")]+Constantes.NUMEROS[configs.get("numero")]);
            al = an;
        }else{
            if(configs.get("letra") >=0)
                al = manager.adicionarLetrasAlfabeto(g, bounds,Constantes.LETRAS_ALFABETO[configs.get("letra")]);        
            if(configs.get("numero") >=0)
                an = manager.adicionarNumeros(g, bounds,Constantes.NUMEROS[configs.get("numero")]);
        }
        
        
        areas.put("textura", at);
        areas.put("cor", ac);
        areas.put("forma", af);
        areas.put("letra", al);
        areas.put("numero", an);

        this.areaCallback.areaUpdated(areas);

        bounds.x = 400;

        g2d.setColor(coritem);
        g2d.fillRect(bounds.x, bounds.y, configs.get("width"), configs.get("height"));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(bounds.x,bounds.y,configs.get("width"),configs.get("height"));
        
        if(output.get("texture"))
            manager.adicionarTextura(g, bounds, Constantes.TIPO_TEXTURA[configs.get("textura")]);
        if(output.get("circle"))
            manager.adicionarCorForma(g, bounds, Color.decode(Constantes.getCorGlyphs()[configs.get("cor")]));
        if(output.get("geometry"))
            manager.adicionarFormaGeometrica(g, bounds, Constantes.TIPOS_FORMAS_GEOMETRICAS[configs.get("forma")]);
        if(output.get("letter") && output.get("number"))
            manager.adicionarNumeros(g, bounds,Constantes.LETRAS_ALFABETO[configs.get("letra")]+Constantes.NUMEROS[configs.get("numero")]);
        else{
            if(output.get("letter"))
                manager.adicionarLetrasAlfabeto(g, bounds,Constantes.LETRAS_ALFABETO[configs.get("letra")]);
            if(output.get("number"))
                manager.adicionarNumeros(g, bounds,Constantes.NUMEROS[configs.get("numero")]);
        }
        
        
            
        
        
    }

    public void setAreaCallback(AreaCallback areaCallback) {
        this.areaCallback = areaCallback;
    }

    public interface AreaCallback {

        public void areaUpdated(HashMap<String, Integer> areas);
    }

    public void setConfigs(HashMap<String, Integer> configs) {
        this.configs = configs;
        repaint();
    }

    public void updateOutput(HashMap<String, Boolean> output) {
        this.output = output;
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
