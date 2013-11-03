package edu.cs251.chavezl3;

import java.awt.*;

/**
 * Class to use in building Tetris pieces.
 *
 * Do not change this class.
 *
 * This implementation will be replaced with a version with more
 * functionality for future parts of this project.
 */
public class Block {

    private Color fillColor;
    private Color lineColor;

    /**
     * Construct a new block with specified colors.
     * @param fillColor Color to fill in the block.
     * @param lineColor Color of block border lines.
     */
    public Block(Color fillColor, Color lineColor) {
        this.fillColor = fillColor;
        this.lineColor = lineColor;
    }

    /**
     * Paint this Block at the given position
     * @param g Graphics object to do the painting
     * @param x The x coord of the top left corner of the block.
     * @param y The y coord of the top left corning of the block.
     */
    public void paint(Graphics g, int x, int y, int cellSize) {
        g.setColor(fillColor);

        g.fillRoundRect(x, y, cellSize, cellSize, cellSize/4, cellSize/4);

        g.setColor(lineColor);
        //g.drawRect(x, y, cellSize, cellSize);
        g.drawRoundRect(x, y, cellSize, cellSize, cellSize/4, cellSize/4);
    }

    /**
     * Paint this Block at the given position
     * @param g Graphics object to do the painting
     * @param x The x coord of the top left corner of the block.
     * @param y The y coord of the top left corning of the block.
     * @param cellSizeW The width of the square (for scaling)
     * @param cellSizeH The height of the square (for scaling)
     */
    public void paint(Graphics g, int x, int y, int cellSizeW, int cellSizeH) {
        g.setColor(fillColor);

        //g.fillRect(x, y, cellSize, cellSize);
        g.fillRoundRect(x, y, cellSizeW, cellSizeH, (cellSizeW+cellSizeH/2)/4, (cellSizeW+cellSizeH/2)/4);

        g.setColor(lineColor);
        //g.drawRect(x, y, cellSize, cellSize);
        g.drawRoundRect(x, y, cellSizeW, cellSizeH, (cellSizeW+cellSizeH/2)/4, (cellSizeW+cellSizeH/2)/4);

    }


    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[]";
	}
}