package com.breakoutv2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Dashboard extends JPanel {
	/**
	 * 
	 */
	
	//Board  gameboard = new  Board();
	private static final long serialVersionUID = 1L;
	public JButton play;
	public JButton pause;
	public JButton stop;
	public JButton undo;
	public JButton replay;

	
	public Dashboard(Board b) {
		this.setLayout(new GridLayout(5, 1));
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NORTH;
		
		this.play = new JButton("Play");
		this.pause = new JButton("Pause");
		this.stop = new JButton("Stop");
		this.undo = new JButton("Undo");
		this.replay = new JButton("Replay");
		
		this.add(play);
		this.add(pause);
		this.add(stop);
		this.add(replay);
		this.add(undo);
		panel.add(this, gbc);
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(b.getLives() > Constants.MIN_LIVES) {
					b.start();
				}
				b.requestFocus();
			}
		});
		
		pause.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!b.getIsPaused()) {
					b.stop();
				}
				b.requestFocus();
			}
		});

		replay.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				replay.setText("replay clicked");
			}
		});
		
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				undo.setText("undo clicked");
				
			}
		});
		
		
		stop.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b.getBall().reset();
				b.getClock().reset();
	            b.setBricksLeft(Constants.MAX_BRICKS);
	            b.makeBricks();
	            b.setLives(Constants.MAX_LIVES);
	            b.setLevel(1);
	            b.setScore(0);
	            b.repaint();
	            b.stop();
	            b.requestFocus();
			}
		});
		
	}
}
