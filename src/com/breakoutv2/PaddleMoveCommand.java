package com.breakoutv2;

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
	private int prevPos;
	
	public PaddleMoveCommand(Paddle paddle) {
		this.paddle = paddle;
	}
	
	@Override
	public void execute(int dx, int dy) {
		this.prevPos = this.paddle.getX();
		this.paddle.move(dx, dy);
	}
	
	@Override
	public void undo() {
		this.paddle.move(this.prevPos, 0);
	}
}
