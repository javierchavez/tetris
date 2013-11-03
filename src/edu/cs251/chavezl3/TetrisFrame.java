package edu.cs251.chavezl3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Created with IntelliJ IDEA.
 * User: javierAle
 * Date: 11/2/13
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class TetrisFrame extends JFrame{

    private static final long serialVersionUID = -7803583554407246969L;
    private JLabel scoreLabel, linesLabel, levelLabel;
    private JButton startPauseButton;
    private Board tetrisPanel;
    private JPanel rightPanel, infoLabels, nextShapePanel, controlPanel;
    private Color rightPanelColor = new Color(236, 240, 241);
    private Font f = new Font("Dialog", Font.ITALIC, 20);

    private JLabel scoreNumLbl, linesNumLbl, levelNumLbl;


    public TetrisFrame(){

        //
        scoreNumLbl = new JLabel("0");
        linesNumLbl = new JLabel("0");
        levelNumLbl = new JLabel("1");
        nextShapePanel = new JPanel();
        controlPanel = new JPanel();
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3,1));
        rightPanel.setBackground(rightPanelColor);

        // this will hold the number values
        infoLabels = new JPanel();
        infoLabels.setLayout(new GridLayout(6,1,0,0));
        infoLabels.setBackground(rightPanelColor);


        //create labels
        scoreLabel = new JLabel("Score:");
        scoreLabel.setFont(f);
        linesLabel = new JLabel("Lines:");
        linesLabel.setFont(f);
        levelLabel = new JLabel("Level:");
        levelLabel.setFont(f);
        startPauseButton = new JButton("Start/Pause");

        //create a panel to show main game with black background
        tetrisPanel = new Board();
        tetrisPanel.addShape(new EllShape(new Block(Color.BLUE,Color.BLACK)));
//        tetrisPanel.setBackground(Color.BLACK);

        System.out.print("TETFR" + tetrisPanel.getWidth()+ " " + tetrisPanel.getHeight());


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
}
