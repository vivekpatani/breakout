package com.breakoutv2;

import java.util.ArrayList;

/*
 * BallMoveCommand
 * 
 * Implements Command in order to keep track of the position of the ball
 * 
 * Andres, Shruti, Vivek, Yash
 */
public class BallMoveCommand implements Command, Observer {
	private Ball ball;
	private ArrayList<Ball> prevBall;
	private Board subject;
	
	public BallMoveCommand(Board subject, Ball ball) {
		this.subject = subject;
		this.ball = ball;
		this.prevBall = new ArrayList<Ball>();
	}
	
	public void ballSave() {
		Ball temp = new Ball(this.ball.x, this.ball.y, this.ball.width, this.ball.height, this.ball.color);
		this.prevBall.add(temp);
	}
	
	public void save() {
		ballSave();
	}
	
	public void print() {
		System.out.println("Size: "+this.prevBall.size());
		for(int i = 0; i < this.prevBall.size(); i++) {
			System.out.println("X: "+this.prevBall.get(i).x+" Y:" +this.prevBall.get(i).y);
		}
	}

	@Override
	public void execute(int dx, int dy) {
		//this.prevBall.add(this.ball);
		this.ball.move(dx, dy);
	}

	@Override
	public void undo() {
		if (this.prevBall.size()  > 0) {
			this.ball = prevBall.get(prevBall.size()-1);
			this.subject.setBall(this.ball);
			this.prevBall.remove(this.prevBall.size()-1);
			//TODO redraw the ball
		}
	}

	@Override
	public void update(Ball ball, Paddle paddle, Clock clock, Brick[][] brick) {
		this.ball = ball;
	}
}
