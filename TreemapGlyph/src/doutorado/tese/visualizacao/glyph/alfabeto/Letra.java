/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.visualizacao.glyph.alfabeto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.text.AttributedCharacterIterator;
import javax.swing.BorderFactory;

/**
 *
 * @author Anderson Soares
 */
public class Letra {

    private int[] xPoints;
    private int[] yPoints;
    private Rectangle rect;
    private String letra;
    private Font fonte;
    private String shandowletra;
    private Font fonte2;


    public Letra(Rectangle r, String letra) {
        this.rect = r;
        setBounds(this.rect);
        this.letra = letra;
        this.shandowletra = letra;

        int width = rect.width;
        int height = rect.height;
        width = width/14;
        height = height/14;
        int area = width+height;

        if(area<10){
                //fonte = new Font("Arial", Font.PLAIN, 10);
                fonte2 =new Font("Arial", Font.PLAIN, area);
        }
        else{
        fonte = new Font("Arial", Font.PLAIN, area);
         fonte2 = new Font("Arial", Font.PLAIN, area);
        }
    }

    public void setBounds(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getBounds() {
        return rect;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        montarRetangulo();
       
        g2d.setPaint(Color.white);
        g2d.setFont(fonte2);
        g2d.drawString(shandowletra, getCenter().x, getCenter().y);

        g2d.setColor(Color.BLACK);
        g2d.drawString(letra, getCenter().x, getCenter().y);
    

    }
    
    private Point getCenter() {
        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;
        
        verificarRetangulo(points);
        
        int width = (int) Math.round(points[0] * 0.2);
        int height = (int) Math.round(points[1] * 0.2);
   
        int pX = (int) (rect.x + rect.width/2-width/2);
        int pY = (rect.y + rect.height/2+ (height/6));
        
        
        return new Point(pX, pY);
    }
    
    /**
     * Função para deixar os glyphs quadrados
     * 
     * @param width
     * @param height
     * @return 
     */
    
     private int[] verificarRetangulo(int [] point){
        if(point[0] > point[1]){
            point[0] = point[1];
           return point;
        }
        else if(point[0] < point[1]){
            point[1] = point[0];
           return point;
        }
        return null;
    }

    private void montarRetangulo() {
        
        
        int[] points = new int[2];

        points[0] = rect.width;
        points[1] = rect.height;
        
        verificarRetangulo(points);
        
        int width = (int) Math.round(points[0] * 0.2);
        int height = (int) Math.round(points[1] * 0.2);
        
        
        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[0] = (int) (rect.x + (rect.x / 2)-width/2);
        yPoints[0] = (int) (rect.y + (rect.y / 2)-height/2);

        xPoints[1] = width;
        yPoints[1] = height;
    }
    /**
     * @return the fonte
     */
    public Font getFonte() {
        return fonte;
    }
    /**
     * @param fonte the fonte to set
     */
    public void setFonte(Font fonte) {
        this.fonte = fonte;
    }
}
