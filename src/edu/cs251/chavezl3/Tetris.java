package edu.cs251.chavezl3;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Driver for testing tetris game pieces.
 * @author Javier Chavez
 */

public class Tetris {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                TetrisFrame frame = new TetrisFrame();

                frame.setVisible(true);
                frame.setSize(640, 740);
                frame.setLocationRelativeTo(null); //center on screen
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }

        });

    }
}
