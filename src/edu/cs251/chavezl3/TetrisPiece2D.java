package edu.cs251.chavezl3;

/**
 * Class contains information about the L block
 * @author Javier Chavez
 * @assignment Lab3  Part 1 - Tetris Blocks
 *
 */
public abstract class TetrisPiece2D implements Object2D{
	
	protected Block[][] piece;
	protected Dimension2D dimension;

	
	@Override
	public Block getBlockAt(int row, int col) {

		return piece[row][col];
	}
	
	@Override
	public void rotate() {
		piece = rotateRight(piece);
		dimension = new Dimension2D(piece.length, piece[0].length);
	}
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder _sb = new StringBuilder();
		for (int i = 0; i < piece.length; i++) {
			for (Block s : piece[i]) {
				if (s != null) {
					_sb.append('*');
				}
				else{
					_sb.append(' ');
				}
			}
			_sb.append('\n');
		}
		return _sb.toString();
	}

	private Block[][] rotateRight(Block[][] p)
	{
	    int w = p.length;
	    int h = p[0].length;
	    Block[][] _p = new Block[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            _p[i][j] = p[w - j - 1][i];
	        }
	    }
	    return _p;
	}
	
	@Override
	public Dimension2D getDimension() {
		// Not supporting jagged arrays
		// assuming the piece will always have 2d's since this is implementing 2d class
		return dimension;
	}
	
}
