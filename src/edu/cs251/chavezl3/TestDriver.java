package edu.cs251.chavezl3;

/** 
 * Driver for testing tetris game pieces.
 * @author Javier Chavez
 */
public class TestDriver {
    public final static int NUM_SHAPES = 7;

    public static void main (String[] args) {
        // we'll use this block to build our shapes
        Block b = new Block();

        Object2D[] pieces = new Object2D[NUM_SHAPES];
        pieces[0] = new EllShape(b);
        pieces[1] = new JayShape(b);
        pieces[2] = new EyeShape(b);
        pieces[3] = new OhShape(b);
        pieces[4] = new EssShape(b);
        pieces[5] = new TeeShape(b);
        pieces[6] = new ZeeShape(b);

        for ( int num = 0; num < NUM_SHAPES; num++ ) {
            System.out.println("Piece num: " + (num+1) );
            
            for ( int rotateNum = 0; rotateNum <= 4; rotateNum++ ) {
                System.out.println("Number of rotations: " + rotateNum);
                System.out.print(pieces[num]);
                for (int i = 0; i < pieces[num].getDimension().height; i++) {
                	for (int j = 0; j < pieces[num].getDimension().width; j++) {

                		if(pieces[num].getBlockAt(i, j)!= null){
                			System.out.println(pieces[num].getBlockAt(i, j));
                		}
					}
					
				}                
                pieces[num].rotate();
                System.out.println("Dimention: " + pieces[num].getDimension());
                System.out.println();

            }
        }
    }
}