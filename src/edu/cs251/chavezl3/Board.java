package edu.cs251.chavezl3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Javier Chavez
 *
 *
 */
public class Board extends JPanel implements ActionListener{

    private static final int BOARD_HEIGHT = 22;
    private static final int BOARD_WIDTH = 10;

    private static Block[][] blocks;
    private Object2D currentShape;
    private Map<String,Integer> currentShapePoints;
    private Point movement = new Point();
    private Dimension blockScaledDim = new Dimension ();
    private CollisionManager collisionManager = new CollisionManager();
    private PieceGenerator generator = new PieceGenerator();
    private TetrisFrame tetrisFrame;
    private int multiplierScore = 1;
    private int lines = 0;





//    public Board(){
//        setFocusable(true);
//        this.blocks = new Block[BOARD_HEIGHT][BOARD_WIDTH];
//        movement.setLocation(0, 0);
//    }

    public Board(TetrisFrame tetrisFrame) {
        this.tetrisFrame = tetrisFrame;
        setFocusable(true);
        this.blocks = new Block[BOARD_HEIGHT][BOARD_WIDTH];
        movement.setLocation(0, 0);
    }

    public void addShape(Object2D s){
        currentShape = s;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentShape != null){
            drawBoard((Graphics2D) g);

        }

    }

    private void drawBoard(Graphics2D g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight ( );


        int cellSizeW = width/BOARD_WIDTH;
        int cellSizeH = height/BOARD_HEIGHT;

        //keep some information about the current piece that way we can scale and check for collisions
        blockScaledDim.setSize(cellSizeW,cellSizeH);
        currentShapePoints = new HashMap<String, Integer>();
        currentShapePoints.put("width",cellSizeW);
        currentShapePoints.put("height",cellSizeH);


        //draw the piece that is currently moving
        for(int row = 0; row < currentShape.getDimension().height; ++row) {
            for (int col = 0; col < currentShape.getDimension().width; ++col) {
                Block b = currentShape.getBlockAt(row, col);
                if(b != null) {
                    int x = (width/BOARD_WIDTH)*col;
                    int y = (height/BOARD_HEIGHT)*row;

                    currentShapePoints.put(row + ""+col+"x",x+movement.x);
                    currentShapePoints.put(row + ""+col+"y",y+movement.y);

                    b.paint(g, x + movement.x, y + movement.y, cellSizeW, cellSizeH);
                }
            }
        }
        // Draw the board (shapes already committed)
        for( int row = 0; row < BOARD_HEIGHT; row++ ){
            for ( int col = 0; col < BOARD_WIDTH; col++ ){

                if(blocks[row][col] != null){
                    Block b =  blocks[row][col];

                    int x = (width/BOARD_WIDTH)*col;
                    int y = (height/BOARD_HEIGHT)*row;

                    b.paint(g,x, y, cellSizeW, cellSizeH);

                }
            }


        }

    }

    private void commit(){

        System.out.print("Commit");
        for( int row = 0; row < currentShape.getDimension().height; row++ ){
            for ( int col = 0; col < currentShape.getDimension().width; col++ ){
                Block block = currentShape.getBlockAt(row,col);
                if (block != null){
                    int x = currentShapePoints.get(row + ""+col+"x")/currentShapePoints.get("width");
                    int y = currentShapePoints.get(row + ""+col+"y")/currentShapePoints.get("height");
                    blocks[y][x] = block;

                }

            }
        }

        currentShape = null;

    }
    public boolean moveRight(){
        if (collisionManager.isRightOpen(currentShape,blocks,currentShapePoints)){
            movement.x  += blockScaledDim.width;
            repaint();
            return true;
        }

        return false;
    }
    public boolean moveLeft(){
        if (collisionManager.isLeftOpen(currentShape,blocks,currentShapePoints)){
            movement.x  -= blockScaledDim.width;
            repaint();
            return true;
        }
        return false;

    }
    public boolean moveDown(){
        if (collisionManager.isBottomOpen(currentShape, blocks, currentShapePoints)){
            movement.y  += blockScaledDim.height;
            repaint();
        } else{
            commit();
            checkRows();
            resetCurrShape();
        }

        return false;

    }

    public void rotate(){
        if (collisionManager.isRotatable(currentShape, blocks, currentShapePoints)){
            currentShape.rotate();
            repaint();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("run");
        moveDown();
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < blocks.length; y++){
            for (int x = 0; x < blocks[y].length; x++){
                stringBuilder.append(blocks[y][x]);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private void resetCurrShape(){

        currentShape = generator.nextShape();
        movement = new Point();
        blockScaledDim = new Dimension ();
        collisionManager = new CollisionManager();
        repaint();

    }
    private void checkRows(){

        boolean isRowFilled = false, wasFilled = false;

        for( int row = 0; row < BOARD_HEIGHT; row++ ){
            for ( int col = 0; col < BOARD_WIDTH; col++ ){

                if(blocks[row][col] != null){
                    isRowFilled  = true;

                } else {
                    isRowFilled = false;
                    break;

                }
            }

            if(isRowFilled){
                wasFilled = true;
                adjustBoard(row);
                multiplierScore++;
                tetrisFrame.setLines(++lines);
            }

        }
        if (wasFilled ){
            tetrisFrame.setScore(10*multiplierScore);
            multiplierScore = 1;

        }
    }

    private void adjustBoard(int row){
        Block [][] tmp = new Block[BOARD_HEIGHT][BOARD_WIDTH];
        for (int y = 1; y < BOARD_HEIGHT; y++){
            for (int x = 0; x < blocks[y].length; x++){
                if (blocks[y][x] != null){
                    if(y <= row){
                        tmp[y][x] = blocks[y-1][x];

                    }   else{
                        tmp[y-1][x] = blocks[y-1][x];

                    }
                }

            }

        }
        blocks = tmp;


    }


}
