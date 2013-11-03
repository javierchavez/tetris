package edu.cs251.chavezl3;
/**
 * Class contains information about the L block
 * @author Javier Chavez
 *
 *
 */
public class EssShape extends TetrisPiece2D {

	public EssShape(Block b) {
		// TODO Auto-generated constructor stub

		piece = new Block[][]{
				{	b,	b,	null},
				{null,	b,	b}
			};
		
		dimension = new Dimension2D(piece.length, piece[0].length);
	}

}
