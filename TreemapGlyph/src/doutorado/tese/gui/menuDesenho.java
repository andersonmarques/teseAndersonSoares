/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doutorado.tese.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JPanel;

/**
 *
 * @author Elvis (LABVIS)
 */
public class menuDesenho extends JPanel{
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
                g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);
        
       String basePath = new File("").getAbsolutePath();
       System.out.println(basePath);

       String path = new File("src\\icon\\logo2.png").getAbsolutePath();
       System.out.println(path);
       
       Image img = Toolkit.getDefaultToolkit().getImage(path);
        g.drawImage(img,65, 30, this);
    }
    

}
    
    

    

