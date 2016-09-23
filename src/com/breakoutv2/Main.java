package com.breakoutv2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

/*
 * Name:
 * 		Main.java
 * Function:
 * 		Simulate the BreakOut game. Puts all elements in the board.
 * Collaborators:
 * 		Andres, Shruti, Vivek, and Yash
 */
public class Main  implements Constants {
    // Variables
    private static JFrame frame;
    private static Board board;
    private static Dimension dim;
    //	 Build and run the game
    public static void main(String[] args) {
        // Set look and feel to that of OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        BorderLayout layout = new BorderLayout();
        frame = new JFrame("Brick Breaker 1.0");
   
        //frame.setPreferredSize(new Dimension(800, 800));
        frame.setSize(WINDOW_WIDTH+PANEL_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(layout);
        board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(board, BorderLayout.CENTER);
        frame.add(new Dashboard(board),BorderLayout.EAST);
        // Place frame in the middle of the screen
        try {
    		dim = Toolkit.getDefaultToolkit().getScreenSize();
    		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        frame.setVisible(true);
		
    }
}