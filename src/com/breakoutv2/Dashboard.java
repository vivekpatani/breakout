package com.breakoutv2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Dashboard extends JPanel {
	/**
	 * 
	 */
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

	}

}
