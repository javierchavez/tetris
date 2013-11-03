package edu.cs251.chavezl3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: javierAle
 * Date: 11/2/13
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Board extends JPanel{

    private static final int BOARDHEIGHT = 22;
    private static final int BOARDWIDTH = 10;
    public static final int CELL_SIZE = 30;
    public static final int MAX_SHAPE_CELLS = 6;

    private Block[][] blocks;


    public Board(){
        this.blocks = new Block[BOARDHEIGHT][BOARDWIDTH];
        setFocusable(true);
        addKeyListener(new KL());

    }

    public void addShape(Object2D s){
        Object2D.Dimension2D dim = s.getDimension();


        //center the block
        for (int y = 0; y < dim.getHeight(); y++){

            for (int x = 0; x < dim.getWidth(); x++){
                blocks[(dim.getHeight())-y][(5 +(dim.getWidth()/2))-x] = s.getBlockAt(y,x);


            }
        }
        for (int y = 0; y < blocks.length; y++){
//            System.out.print(blocks[y]);
            for (int x = 0; x < blocks[y].length; x++){
                System.out.print(blocks[y][x]);


            }
            System.out.print("\n");
        }


    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        int width = getWidth ( );
        int height = getHeight ( );
        System.out.print(width + " " + height);
        for ( int row = 0; row <= BOARDHEIGHT; row++ ) {

            // Draw the hori lines
            g.drawLine (0, row * (height/BOARDHEIGHT), width, row * (height/BOARDHEIGHT));


            // Draw the vertical lines
            g.drawLine (row * (width/10), 0, row * (width/10), height);
        }

        // Then draw the contents
        drawBoard ( (Graphics2D) g );
    }

    private void drawBoard(Graphics2D g) {


        int width = getWidth ( );
        int height = getHeight ( );
        int cellsice = ((width/BOARDWIDTH)*(height/BOARDHEIGHT))/32;



        for( int row = 0; row < BOARDHEIGHT; row++ ){
            for ( int col = 0; col < BOARDWIDTH; col++ ){

                if(blocks[row][col] != null){

                    Block b =  blocks[row][col];
//                    int x = ((col)*(height/BOARDHEIGHT));

                    int x = (width/BOARDWIDTH)*col;
                    int y = (height/BOARDHEIGHT)*row;
                    b.paint(g,x,y,cellsice);
//                    System.out.print("here");


                }

            }

        }
    }

    private class KL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            if (e.getKeyCode() == e.VK_UP){
                System.out.print("up");

            } else if (e.getKeyCode() == e.VK_RIGHT){
                System.out.print("right");

            } else if (e.getKeyCode() == e.VK_LEFT){

                System.out.print("left");
            } else if (e.getKeyCode() == e.VK_DOWN){

                System.out.print("down");
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {}



        @Override
        public void keyReleased(KeyEvent e) {}
    }

}
