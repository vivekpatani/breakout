package com.breakoutv2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

/*
 * Name:
 * 		Main.java
 * Function:
 * 		Simulate the BreakOut game. Puts all elements in the board.
 * Collaborators:
 * 		Andres, Shruti, Vivek, and Yash
 */
@SuppressWarnings("serial")
public class Main  implements Constants {
    // Variables
    private static JFrame frame;
    private static Board board;
    private static Dimension dim;
    private static JPanel rightPanel;
    // Build and run the game
    public static void main(String[] args) {
        // Set look and feel to that of OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        BorderLayout layout = new BorderLayout(0,2);
        frame = new JFrame("Brick Breaker 1.0");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(layout);
        board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(board,layout.CENTER);
       
        // Place frame in the middle of the screen
        try {
    		dim = Toolkit.getDefaultToolkit().getScreenSize();
    		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        } catch (Exception e) {
            e.printStackTrace();
        
        }

        frame.setVisible(true);
		frame.add(dashboard(),layout.EAST);
    }


private static JPanel dashboard(){
	
	rightPanel = new JPanel(new GridLayout(4, 1,10,10));
	rightPanel.setBackground(Color.WHITE);
	rightPanel.setBounds(600,0, PANEL_WIDTH, WINDOW_HEIGHT);
	rightPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	rightPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	frame.getContentPane().add(rightPanel);
	
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");
	JButton stopButton = new JButton("Stop");
	JButton undoButton = new JButton("Undo");
	JButton replayButton = new JButton("Replay");
	JLabel timeDisplay = new JLabel("Time Elapsed: ");
	JLabel timeSpent = new JLabel("Time Value goes here");
	JLabel scoreCard = new JLabel("Score: ");
	JLabel scoreValue = new JLabel("Score value goes here");
	
	
	
	Box box = Box.createVerticalBox();
	Box box1 = Box.createVerticalBox();
	Box box3 = Box.createVerticalBox();
	Box box4 = Box.createVerticalBox();
	
	
	rightPanel.add(box);
	rightPanel.add(box1);
	rightPanel.add(box3);
	rightPanel.add(box4);
	
	Box.createHorizontalGlue();
	box.add(timeDisplay);
	box.add(timeSpent);
	Box.createVerticalStrut(10);
	box1.add(scoreCard);
	box1.add(scoreValue);
	Box.createVerticalStrut(10);
	box3.add(playButton);
	box3.add(pauseButton);
	box3.add(stopButton);
	Box.createVerticalStrut(10);
	box4.add(undoButton);
	box4.add(replayButton);
	
	
	return rightPanel;
}
}