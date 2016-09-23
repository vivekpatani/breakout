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

public class PaddleMoveCommand implements Command, Observer{
	private Paddle paddle;
	private ArrayList<Paddle> prevPaddle;
	private Board subject;
	
	public PaddleMoveCommand(Board subject, Paddle paddle) {
		this.subject = subject;
		this.paddle = paddle;
		this.prevPaddle = new ArrayList<Paddle>();
	}
	
	public void paddleSave() {
		Paddle temp = new Paddle(this.paddle.x, this.paddle.y, this.paddle.width, this.paddle.height, this.paddle.color);
		this.prevPaddle.add(temp);
	}
	
	public void save() {
		paddleSave();
	}
	@Override
	public void execute(int dx, int dy) {
		//this.prevPaddle.add(this.paddle);
		this.paddle.move(dx, dy);
	}
	
	@Override
	public void undo() {
		if (this.prevPaddle.size()  > 0) {
			this.paddle = this.prevPaddle.get(this.prevPaddle.size()-1);
			this.subject.setPaddle(this.paddle);
			this.prevPaddle.remove(this.prevPaddle.size()-1);
			//TODO redraw the paddle
		}
	}

	@Override
	public void update(Ball ball, Paddle paddle, Clock clock, Brick[][] brick) {
		this.paddle = paddle;
	}
}
