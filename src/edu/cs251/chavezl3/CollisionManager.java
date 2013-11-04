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

    public boolean isMovable(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints){
        return  isLeftOpen(piece,board,currentShapePoints) ||
                isRightOpen(piece,board,currentShapePoints) ||
                isBottomOpen(piece, board, currentShapePoints);

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

                    //System.out.println("TEST=Location: " + x + " " + y);
                    if (x < BOARD_WIDTH && board[y][x] == null && x < BOARD_WIDTH){
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

                    //System.out.println("TEST=Location: " + x + " " + y);
                    if (x >= 0 && board[y][x] == null && x < BOARD_WIDTH){
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

                    if (y < BOARD_HEIGHT && board[y][x] == null && y < BOARD_HEIGHT){
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
