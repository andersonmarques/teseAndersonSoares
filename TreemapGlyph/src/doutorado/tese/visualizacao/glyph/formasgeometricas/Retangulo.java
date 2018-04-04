package doutorado.tese.visualizacao.glyph.formasgeometricas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Retangulo extends FormaGeometrica {

    private int[] xPoints;
    private int[] yPoints;
    private Color cor;
    private String angulo;
    private float tam;
    private String position;

    public Retangulo(Rectangle r, Color cor, String angulo, float tam, String position) {
        super(r, "RETANGULO");
        this.cor = cor;
        this.angulo = angulo;
        this.tam = tam;
        this.position = position;
        montarRetangulo();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(cor);
        g2d.setColor(cor);
        g2d.fillRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);

    }

    //função para deixar os glyphs quadrados
    private int[] verificarRetangulo(int[] point) {
        if (point[0] > point[1]) {
            point[0] = point[1];
            return point;
        } else if (point[0] < point[1]) {
            point[1] = point[0];
            return point;
        }
        return null;
    }

    private void montarRetangulo() {
        Rectangle rect = getBounds();

        int[] points = new int[2];
        points[0] = rect.width;
        points[1] = rect.height;

        verificarRetangulo(points);
        int innerRectX = (int) Math.round(points[0] * 0.8);
        int innerRectY = (int) Math.round(points[0] * 0.8);

        int width = (int) (innerRectX * tam);
        int height = (int) (innerRectY * tam);
        
        xPoints = new int[2];
        yPoints = new int[2];

        xPoints[1] = width;
        yPoints[1] = height;

        int[] pos = definirPosicao(rect,innerRectX,innerRectY,width, height, position);
        xPoints[0] = pos[0];
        yPoints[0] = pos[1];
        
    }
    private int[] definirPosicao(Rectangle rect, int innerRectX, int innerRectY, int width, int height, String position) {
        int[] result = new int[2];
        switch (position) {
            case "1":
                result[0] = rect.x + rect.width/2 - innerRectX/2;
                result[1] = rect.y + rect.height/2 - innerRectY/2 ;
                return result;
            case "2":
                result[0] = rect.x + rect.width/2 - innerRectX/2;
                result[1] = rect.y + rect.height/2 + innerRectY/2-height;
                return result;
            case "3":
                result[0] = rect.x + rect.width/2 + innerRectX/2-width;
                result[1] = rect.y + rect.height/2 + innerRectY/2-height ;
                break;
            case "4":
                result[0] = rect.x + rect.width/2 + innerRectX/2-width;
                result[1] = rect.y + rect.height/2 - innerRectY/2;
                break;
            case "5":
                result[0] = rect.x + rect.width / 2 -  width / 2;
                result[1] = rect.y + rect.height / 2 - height / 2;

                return result;

        }
        return result;
    }

    @Override
    public int getArea() {
        return xPoints[1] * yPoints[1];
    }

}
