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
public class Board extends JPanel implements ActionListener, Object2D{

    private static final int BOARD_HEIGHT = 22;
    private static final int BOARD_WIDTH = 10;

    private static Block[][] blocks;
    private Object2D currentShape,nextShape;
    private Map<String,Integer> currentShapePoints;
    private Point movement = new Point();
    private Dimension blockScaledDim = new Dimension ();
    private CollisionManager collisionManager = new CollisionManager();
    private PieceGenerator generator = new PieceGenerator();
    private TetrisFrame tetrisFrame;
    private Map<Integer,Integer> multiplierScore;
    private int lines = 0;
    private int score;
    private int level=1;
    private boolean isLost = false;


//    public Board(){
//        setFocusable(true);
//        this.blocks = new Block[BOARD_HEIGHT][BOARD_WIDTH];
//        movement.setLocation(0, 0);
//    }

    public Board(TetrisFrame tetrisFrame) {
        multiplierScore =new HashMap<Integer, Integer>();// 40,100,300,1200};
        multiplierScore.put(0,20);
        multiplierScore.put(1,40);
        multiplierScore.put(2,100);
        multiplierScore.put(3,300);
        multiplierScore.put(4,1200);
        this.tetrisFrame = tetrisFrame;
        setFocusable(true);
        this.blocks = new Block[BOARD_HEIGHT][BOARD_WIDTH];
        movement.setLocation(0, 0);
        nextShape =    generator.nextShape();
    }

    public void addShape(Object2D s){
        currentShape = s;
        tetrisFrame.setNextPiece(nextShape);

        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isLost){
            g.setColor(Color.BLACK);
            g.drawString("Game over!",getWidth()/2,getHeight()/2);
        }
        else if (currentShape != null){
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
        if (!isLost){

            if ( collisionManager.isBottomOpen(currentShape, blocks, currentShapePoints)){
                movement.y  += blockScaledDim.height;
                repaint();
                return true;
            } else{
                commit();
                checkRows();
                resetCurrShape();
                return false;

            }
        }
        return false;
    }

    public boolean forceDown(){


        moveDown();

        return false;
    }

    public void rotateShape(){
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

        currentShape =  nextShape;
        nextShape =    generator.nextShape();
        tetrisFrame.setNextPiece(nextShape);
        movement = new Point();
        blockScaledDim = new Dimension ();
        collisionManager = new CollisionManager();
        tetrisFrame.setCurrentPiece(currentShape);
        checkRows();
        repaint();

    }
    private void checkRows(){

        int comboMultiplier = 0;
        boolean isRowFilled = false, wasFilled = false;
        int row;
        if (blocks[1][1]!=null){
            isLost = true;
            tetrisFrame.isLost(true);
            return;
        }
        for( row = 0; row < BOARD_HEIGHT; row++ ){
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
                removeRow(row);
                //reset row sine the board has chnagd
                row = 0;
                comboMultiplier++;
                lines++;


            }

        }
        if (wasFilled){
            score += multiplierScore.get(comboMultiplier);

        }

        if (comboMultiplier > 0 && lines > 0 && lines % 5 == 0) {
            level++;
            tetrisFrame.setLevel(level);

        }
        //send to main frame to be displayed to user
        tetrisFrame.setLines(lines);
        tetrisFrame.setScore(score);

    }

    private void removeRow(int filledRow){
        Block [][] tmp = new Block[BOARD_HEIGHT][BOARD_WIDTH];
        for (int y = 1; y < BOARD_HEIGHT; y++){
            for (int x = 0; x < BOARD_WIDTH; x++){
                if (blocks[y][x] != null && blocks[y-1][x] != null && y <= filledRow){
                    tmp[y][x] = blocks[y-1][x];

                } else if(blocks[y][x] != null && y > filledRow){
                    tmp[y][x] = blocks[y][x];

                }

            }

        }
        blocks = tmp;


    }

    @Override
    public void rotate() {
        throw new NoSuchMethodError();
    }

    @Override
    public Dimension2D getDimension() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Block getBlockAt(int row, int col) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
