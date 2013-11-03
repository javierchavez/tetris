package edu.cs251.chavezl3;

import java.awt.*;
import java.util.Map;

/**
 *
 * @author Javier Chavez
 *
 *
 */
public class CollisionManager {
    private static final int BOARD_HEIGHT = 22;
    private static final int BOARD_WIDTH = 10;

    public boolean isMovable(Object2D piece, Block[][] board){
        return false; //isLeftOpen(piece,board);// || isRightOpen(piece,board) || isRotatable(piece, board);

    }
    public boolean isRightOpen(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints){
        boolean isOpen = false;

        for(int row = 0; row < piece.getDimension().height; ++row) {
            for (int col = 0; col < piece.getDimension().width; ++col) {
                Block b = piece.getBlockAt(row, col);
                if(b != null) {

                    int x = ((int) (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width")));
                    int y = ((int) (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height")));

                    ++x;

                    System.out.println("TEST=Location: " + x + " " + y);
                    if (board[y][x] == null && x < BOARD_WIDTH){
                        isOpen = true;
                    }
                    else{
                        return false;
                    }

                }
            }
        }
        return isOpen;

    }
    public boolean isLeftOpen(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints){
        boolean isOpen = false;

        for(int row = 0; row < piece.getDimension().height; ++row) {
            for (int col = 0; col < piece.getDimension().width; ++col) {
                Block b = piece.getBlockAt(row, col);
                if(b != null) {

                    int x = ((int) (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width")));
                    int y = ((int) (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height")));

                    --x;

                    System.out.println("TEST=Location: " + x + " " + y);
                    if (board[y][x] == null && x < BOARD_WIDTH){
                        isOpen = true;
                    }
                    else{
                        return false;
                    }

                }
            }
        }
        return isOpen;

    }
    public boolean isRotatable(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints){
        boolean isOpen = false;

//        for(int row = 0; row < piece.getDimension().height; ++row) {
//            for (int col = 0; col < piece.getDimension().width; ++col) {
//                Block b = piece.getBlockAt(row, col);
//                if(b != null) {
//
//                    int x = ((int) (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width")));
//                    int y = ((int) (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height")));
//
//
//
//
//                    if (board[y][x] == null && x < BOARD_WIDTH){
//                        isOpen = true;
//                    }
//                    else{
//                        return true;
//                    }
//
//                }
//            }
//        }
        return true;

    }

    public boolean isBottomOpen(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints) {
        boolean isOpen = false;

        for(int row = 0; row < piece.getDimension().height; ++row) {
            for (int col = 0; col < piece.getDimension().width; ++col) {
                Block b = piece.getBlockAt(row, col);
                if(b != null) {

                    int x = ((int) (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width")));
                    int y = ((int) (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height")));



                    ++y;

                    if (board[y][x] == null && y < BOARD_HEIGHT){
                        isOpen = true;
                    }
                    else{
                        return false;
                    }

                }
            }
        }
        return isOpen;
    }
}
