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
                new EllShape(new Block(Color.decode("#4d4dff"), Color.decode("#0000ff"))),
                new JayShape(new Block(Color.decode("#a64da6"), Color.decode("#800080"))),
                new EyeShape(new Block(Color.decode("#ffc04d"), Color.decode("#ffa500"))),
                new OhShape(new Block(Color.decode("#ff4d4d"), Color.decode("#ff0000"))),
                new EssShape(new Block(Color.decode("#c6f1ff"), Color.decode("#aeebff"))),
                new TeeShape(new Block(Color.decode("#e7ec67"), Color.decode("#d7df01"))),
                new ZeeShape(new Block(Color.decode("#b2f3b2"), Color.decode("#90ee90")))
        };
    }

    public Object2D nextShape(){
        return shapes[rand.nextInt(shapes.length)];
    }
}
