package com.breakoutv2;

import java.util.ArrayList;

/*
 * BallMoveCommand
 * 
 * Implements Command in order to keep track of the position of the ball
 * 
 * Andres, Shruti, Vivek, Yash
 */
public class BallMoveCommand implements Command {
	private Ball ball;
	private ArrayList<Ball> prevBall;
	
	public BallMoveCommand(Ball ball) {
		this.ball = ball;
		this.prevBall = new ArrayList<Ball>();
	}

	@Override
	public void execute(int dx, int dy) {
		this.prevBall.add(this.ball);
		this.ball.move(dx, dy);
	}

	@Override
	public void undo() {
		if (this.prevBall.size()  > 0) {
			this.ball = prevBall.get(prevBall.size()-1);
			this.prevBall.remove(this.prevBall.size()-1);
			//TODO redraw the ball
		}
	}
}
