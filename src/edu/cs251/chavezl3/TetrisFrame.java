package edu.cs251.chavezl3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Javier Chavez
 *
 *
 */
public class TetrisFrame extends JFrame implements KeyListener{

    private static final long serialVersionUID = -7803583554407246969L;
    Board tetrisPanel = new Board(this);
    private static final int DELAY = 1000;
    private boolean isRunning = false;
    JButton startPauseButton;
    JLabel scoreNumLbl;
    private int score = 0;
    private PieceGenerator generator = new PieceGenerator();


    private Timer timer;


    public TetrisFrame(){
        setFocusable(true);

        Color rightPanelColor = new Color(236, 240, 241);
        Font f = new Font("Dialog", Font.ITALIC, 20);
        timer = new Timer(DELAY, tetrisPanel);
        timer.setInitialDelay(0);
        addKeyListener(this);
        //
        scoreNumLbl = new JLabel("0");
        JLabel linesNumLbl = new JLabel("0");
        JLabel levelNumLbl = new JLabel("1");
        JPanel nextShapePanel = new JPanel();
        JPanel controlPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3,1));
        rightPanel.setBackground(rightPanelColor);

        // this will hold the number values
        JPanel infoLabels = new JPanel();
        infoLabels.setLayout(new GridLayout(6,1,0,0));
        infoLabels.setBackground(rightPanelColor);


        //create labels
        JLabel scoreLabel = new JLabel("Score:");
        scoreLabel.setFont(f);
        JLabel linesLabel = new JLabel("Lines:");
        linesLabel.setFont(f);
        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setFont(f);
        startPauseButton = new JButton("Start/Pause");

        startPauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {


                startPauseButton.setText(isRunning ? "Start" : "Pause");
                if(!isRunning) {
                    isRunning = true;
                    runGame();
                } else {
                    isRunning = false;
                    timer.stop();
                }
            }
        });

        //create a panel to show main game with black background
        tetrisPanel.setBackground(Color.BLACK);

        //main container only hold tetris and right panel in grid form
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout());

        //add two panels to main
        pane.add(tetrisPanel);
        pane.add(rightPanel);


        // holds controls for game
        controlPanel.add(startPauseButton);
        controlPanel.setBackground(rightPanelColor);

        // adding title and border to next piece area
        TitledBorder box = new TitledBorder("Next Piece");
        box.setTitleJustification(TitledBorder.CENTER);
        box.setTitleFont(f);
        nextShapePanel.setBorder(box);
        nextShapePanel.setBackground(rightPanelColor);


        // add all labels to panel
        infoLabels.add(scoreLabel);
        infoLabels.add(scoreNumLbl);
        infoLabels.add(linesLabel);
        infoLabels.add(linesNumLbl);
        infoLabels.add(levelLabel);
        infoLabels.add(levelNumLbl);

        // this allows to keep spacing correctly formatted
        rightPanel.add(nextShapePanel);
        rightPanel.add(infoLabels);
        rightPanel.add(controlPanel);


        setTitle("Tetris");

    }

    private void initPanel(){

    }

    private void runGame(){

        tetrisPanel.addShape(new PieceGenerator().nextShape());
        timer.start();
        this.requestFocus();

//        while (isRunning){
//
//        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP){
            tetrisPanel.rotate();

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            tetrisPanel.moveRight();

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            tetrisPanel.moveLeft();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            tetrisPanel.moveDown();

        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}



    @Override
    public void keyReleased(KeyEvent e) {}

    public void setScore(int score){
        this.score += score;
        scoreNumLbl.setText(String.valueOf(this.score));
    }

}
