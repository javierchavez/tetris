package edu.cs251.chavezl3;

import java.awt.*;
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
public class TetrisFrame extends JFrame implements KeyListener {

    private static final long serialVersionUID = -7803583554407246969L;
    private Board tetrisPanel = new Board(this);
    private static int DELAY = 1000;

    private boolean isRunning = false;

    private JButton startPauseButton;
    private JLabel scoreNumLbl, linesNumLbl, levelNumLbl;
    private int score = 0;
    private PieceGenerator generator = new PieceGenerator();
    private Object2D currentPiece,nextPiece;
    private int level = 1;
    private NextShapePanel nextShapePanel;
    private Timer timer;


    private int lines;
    private boolean isLost = false;

    public TetrisFrame(){
        setFocusable(true);
        currentPiece = generator.nextShape();

        Color rightPanelColor = Color.white;
        Font f = new Font("Dialog", Font.BOLD, 20);
        timer = new Timer(DELAY, tetrisPanel);
        timer.setInitialDelay(0);
        addKeyListener(this);
        //
        scoreNumLbl = new JLabel("0");
        linesNumLbl = new JLabel("0");
        levelNumLbl = new JLabel("1");
        nextShapePanel = new NextShapePanel();
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
        startPauseButton.setBorderPainted(false);
        startPauseButton.setBackground(Color.decode("#b2f3b2"));
        startPauseButton.setForeground(Color.white);
        startPauseButton.setOpaque(true);
        rightPanel.setPreferredSize(new Dimension(160,30));

        startPauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {


                startPauseButton.setText(isRunning ? "Start" : "Pause");
                if(!isRunning) {
                    isRunning = true;
                    startPauseButton.setBackground(Color.decode("#ff4d4d"));
                    runGame();
                } else {
                    isRunning = false;
                    timer.stop();
                    startPauseButton.setBackground(Color.decode("#b2f3b2"));

                }
            }
        });

        //create a panel to show main game with black background
        tetrisPanel.setBackground(Color.WHITE);

//        tetrisPanel.setPreferredSize(new Dimension(100,110));


        //main container only hold tetris and right panel in grid form
        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        //add two panels to main
        pane.add(tetrisPanel, BorderLayout.CENTER);
        pane.add(rightPanel, BorderLayout.LINE_END);


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


//    private void initPanel(){

    private void runGame(){
        if (!isLost){
            tetrisPanel.addShape(currentPiece);
            timer.start();
            this.requestFocus();

        }

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
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){

//            tetrisPanel.forceDown();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}



    public void setScore(int score){
        this.score = score;
        scoreNumLbl.setText(String.valueOf(this.score));
    }

    public void setLines(int lines){
        this.lines = lines;
        linesNumLbl.setText(String.valueOf(this.lines));

    }


    public void setCurrentPiece(Object2D piece){
        this.currentPiece = piece;
    }

    public void setLevel(int level){
        this.level = level;
        levelNumLbl.setText(String.valueOf(this.level));
        if (this.level > 0 && this.level % 2 == 0){
            DELAY -= 100;
            if (DELAY == 0){
                timer.stop();
            }   else{
                timer.setDelay(DELAY);

            }
            System.out.println("Time: "+ DELAY);

        }

    }

    public void setNextPiece(Object2D nextPiece) {
        this.nextPiece = nextPiece;
        nextShapePanel.setShape(nextPiece);

    }

    public void isLost(boolean lost) {
        isLost = lost;
        timer.stop();

    }
    public void setRunning(boolean running) {
        isRunning = running;
        if(!isRunning ){
            timer.stop();
        } else if(!timer.isRunning() && isRunning){
            timer.start();
        }

    }
}
