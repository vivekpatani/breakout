package com.breakoutv2;

import java.util.ArrayList;

/*
 * PaddleMoveCommand.java
 * 
 * implements the interface command, this piece will make the paddle move
 * while keeping track of where the paddle was last
 * 
 * Andres, Shruti, Vivek, and Yash
 */

public class PaddleMoveCommand implements Command{
	private Paddle paddle;
	private ArrayList<Paddle> prevPaddle;
	
	public PaddleMoveCommand(Paddle paddle) {
		this.paddle = paddle;
		this.prevPaddle = new ArrayList<Paddle>();
	}
	
	@Override
	public void execute(int dx, int dy) {
		this.prevPaddle.add(this.paddle);
		this.paddle.move(dx, dy);
	}
	
	@Override
	public void undo() {
		if (this.prevPaddle.size()  > 0) {
			this.paddle = this.prevPaddle.get(this.prevPaddle.size()-1);
			this.prevPaddle.remove(this.prevPaddle.size()-1);
			//TODO redraw the paddle
		}
	}
}
