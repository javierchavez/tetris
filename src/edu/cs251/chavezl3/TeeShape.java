package edu.cs251.chavezl3;


/**
 * Driver for testing tetris game pieces.
 * @author Javier Chavez
 */


public class TeeShape extends TetrisPiece2D {

	public TeeShape(Block b) {
		// TODO Auto-generated constructor stub
		piece = new Block[][]{
				{null,b,null},
				{b,	b,	b}
			};
		
		dimension = new Dimension2D(piece.length, piece[0].length);
	}

}
