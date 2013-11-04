package edu.cs251.chavezl3;

import java.awt.*;
import java.util.Random;

/**
 *
 * @author Javier Chavez
 */
public class PieceGenerator {
    private Object2D[] shapes;
    private static final Random rand = new Random();


    PieceGenerator(){
        shapes = new Object2D[]{
                new EllShape(new Block(Color.ORANGE, Color.GRAY)),
                new JayShape(new Block(Color.BLUE, Color.GRAY)),
                new EyeShape(new Block(Color.CYAN, Color.GRAY)),
                new OhShape(new Block(Color.YELLOW, Color.GRAY)),
                new EssShape(new Block(Color.GREEN, Color.GRAY)),
                new TeeShape(new Block(Color.MAGENTA, Color.GRAY)),
                new ZeeShape(new Block(Color.RED, Color.GRAY))
        };
    }

    public Object2D nextShape(){
        return shapes[rand.nextInt(shapes.length)];
    }
}
