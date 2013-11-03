package edu.cs251.chavezl3;
/**
 * Class contains information about the L block
 * @author Javier Chavez
 *
 *
 */
public class ZeeShape extends TetrisPiece2D {

	public ZeeShape(Block b) {
		// TODO Auto-generated constructor stub
		piece = new Block[][]{

				{null,	b,	b},
				{b,	b,	null}
			};
		
		dimension = new Dimension2D(piece.length, piece[0].length);
	}

}
