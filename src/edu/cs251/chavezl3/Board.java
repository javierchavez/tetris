package edu.cs251.chavezl3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: javierAle
 * Date: 11/2/13
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Board extends JPanel{

    private static final int BOARD_HEIGHT = 22;
    private static final int BOARD_WIDTH = 10;

    private Block[][] blocks;
    private Object2D currentShape;


    public Board(){
        this.blocks = new Block[BOARD_HEIGHT][BOARD_WIDTH];
        setFocusable(true);
        addKeyListener(new KL());

    }

    public void addShape(Object2D s){
        currentShape = s;


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth ( );
        int height = getHeight ( );
        System.out.print(width + " " + height);
        for ( int row = 0; row <= BOARD_HEIGHT; row++ ) {

            // Draw the hori lines
            g.drawLine (0, row * (height/BOARD_HEIGHT), width, row * (height/BOARD_HEIGHT));


            // Draw the vertical lines
            g.drawLine (row * (width/10), 0, row * (width/10), height);
        }

        // Then draw the contents
        drawBoard ((Graphics2D) g );
        drawCurrentPiece((Graphics2D) g);
    }

    private void drawCurrentPiece(Graphics2D g) {
        super.paintComponent(g);

        Object2D.Dimension2D dim = currentShape.getDimension();

        int width = getWidth ( );
        int height = getHeight ( );


        int cellSizeW = width/BOARD_WIDTH;
        int cellSiceH = height/BOARD_HEIGHT;



        for(int row = 0; row < dim.getHeight(); ++row) {
            for (int col = 0; col < dim.getWidth(); ++col) {
                Block b = currentShape.getBlockAt(row, col);
                if(b != null) {
                    int x = (width/BOARD_WIDTH)*col;
                    int y = (height/BOARD_HEIGHT)*row;
                    b.paint(g,x,y,cellSizeW,cellSiceH);
                }
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

    private void drawBoard(Graphics2D g) {
        super.paintComponent(g);


        int width = getWidth ( );
        int height = getHeight ( );
//        int cellsice = ((width/BOARDWIDTH)*(height/BOARDHEIGHT))/32;

        int cellSizeW = width/BOARD_WIDTH;
        int cellSiceH = height/BOARD_HEIGHT;


        for( int row = 0; row < BOARD_HEIGHT; row++ ){
            for ( int col = 0; col < BOARD_WIDTH; col++ ){

                if(blocks[row][col] != null){

                    Block b =  blocks[row][col];

                    int x = (width/BOARD_HEIGHT)*col;
                    int y = (height/BOARD_WIDTH)*row;
                    b.paint(g,x,y,cellSizeW,cellSiceH);


                }

            }

        }
    }

    private void commit(Object2D p){

    }

    private class KL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            if (e.getKeyCode() == e.VK_UP){
                System.out.print("up");
                currentShape.rotate();

                repaint();
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
