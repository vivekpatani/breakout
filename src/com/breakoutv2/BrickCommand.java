package com.breakoutv2;

import java.util.ArrayList;

public class BrickCommand implements Command, Observer{
	private Brick[][] bricks;
	private ArrayList<Brick[][]> prevBricks;
	
	public BrickCommand(Brick[][] bricks) {
		this.bricks = bricks;
		this.prevBricks = new ArrayList<Brick[][]>();
	}
	
	public void bricksSave() {
		this.prevBricks.add(this.bricks);
	}
	
	@Override
	public void execute(int dx, int dy) {
		return;
	}
	@Override
	public void undo() {
		if(this.prevBricks.size() > 0) {
			this.bricks = this.prevBricks.get(this.prevBricks.size()-1);
			this.prevBricks.remove(this.prevBricks.size()-1);
		}
	}
	@Override
	public void save() {
		bricksSave();
	}
	@Override
	public void update(Ball ball, Paddle paddle, Clock clock, Brick[][] brick) {
		this.bricks = brick;
	}
}
