package edu.cs251.chavezl3;

import java.util.ArrayList;
import java.util.Collections;
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

//    public boolean isMovable(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints){
//        return  isLeftOpen(piece,board,currentShapePoints) ||
//                isRightOpen(piece,board,currentShapePoints) ||
//                isBottomOpen(piece, board, currentShapePoints);
//
//    }
    public boolean isRightOpen(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints){
        boolean isOpen = false;

        for(int row = 0; row < piece.getDimension().height; ++row) {
            for (int col = 0; col < piece.getDimension().width; ++col) {
                Block b = piece.getBlockAt(row, col);
                if(b != null) {

                    int x =  (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width"));
                    int y =  (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height"));

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

                    int x =  (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width"));
                    int y =  (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height"));

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
        ArrayList<Integer> spacesH = new ArrayList<Integer>();
        ArrayList<Integer> spacesV = new ArrayList<Integer>();

        //rotate once to get worse case scenario
        piece.rotate();
        Object2D.Dimension2D dim = piece.getDimension();
        //rotate back to original state
        piece.rotate();
        piece.rotate();
        piece.rotate();

        for(int row = 0; row < piece.getDimension().height; ++row) {
            for (int col = 0; col < piece.getDimension().width; ++col) {
                Block b = piece.getBlockAt(row, col);
                if(b != null) {

                    int x = (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width"));
                    int y = (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height"));

//                    x = x + dim.width;
//                    y = y + dim.height;
                    int space = (BOARD_WIDTH) - x;
                    spacesH.add(space);
                    space = (BOARD_HEIGHT) - y;
                    spacesV.add(space);



                }
            }
        }
        if (dim.width > Collections.max(spacesH) || dim.height > Collections.max(spacesV) || !isBottomOpen(piece, board, currentShapePoints) ){
            return false;
        }
        return true;
    }

    public boolean isBottomOpen(Object2D piece, Block[][] board, Map<String, Integer> currentShapePoints) {
        boolean isOpen = false;

        for(int row = 0; row < piece.getDimension().height; ++row) {
            for (int col = 0; col < piece.getDimension().width; ++col) {
                Block b = piece.getBlockAt(row, col);
                if(b != null) {

                    int x = (currentShapePoints.get(row + ""+col+"x") / currentShapePoints.get("width"));
                    int y = (currentShapePoints.get(row + ""+col+"y") / currentShapePoints.get("height"));



                    ++y;
                    if (y < BOARD_HEIGHT && board[y][x] == null ){
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
