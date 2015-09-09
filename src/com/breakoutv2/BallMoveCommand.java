package com.breakoutv2;

/*
 * BallMoveCommand
 * 
 * Implements Command in order to keep track of the position of the ball
 * 
 * Andres, Shruti, Vivek, Yash
 */
public class BallMoveCommand implements Command {
	private Ball ball;
	
	public BallMoveCommand(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void execute(int dx, int dy) {
		this.ball.move(dx, dy);
	}

	@Override
	public void undo() {
		
	}
}
