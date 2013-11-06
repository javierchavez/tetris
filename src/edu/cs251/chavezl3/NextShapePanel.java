package edu.cs251.chavezl3;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: javierAle
 * Date: 11/5/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class NextShapePanel extends JPanel{


    private Object2D shape;

    public void setShape(Object2D shape){
        this.shape = shape;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //To change body of overridden methods use File | Settings | File Templates.

        if (shape!=null){
            drawShape((Graphics2D) g);
        }

    }

    private void drawShape(Graphics2D g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight ( );


        int cellSizeW = width/8;
        int cellSizeH = height/6;

        for(int row = 0; row < shape.getDimension().height; ++row) {
            for (int col = 0; col < shape.getDimension().width; ++col) {
                Block b = shape.getBlockAt(row, col);
                if(b != null) {
                    int x = ((width/8)*col);
                    int y = (height/6)*row;

                    //rough midpoint
                    x = x + (cellSizeW*3);
                    y = y + (cellSizeH*2);

                    b.paint(g, x, y , cellSizeW, cellSizeH);
                }
            }
        }
    }
}
