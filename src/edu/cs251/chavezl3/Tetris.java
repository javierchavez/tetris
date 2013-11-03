package edu.cs251.chavezl3;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Created with IntelliJ IDEA.
 * User: javierAle
 * Date: 11/2/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
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
