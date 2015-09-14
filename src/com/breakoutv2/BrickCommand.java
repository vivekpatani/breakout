package com.breakoutv2;

import java.util.ArrayList;

public class BrickCommand implements Command, Observer{
	private Brick[][] bricks;
	private ArrayList<Helper> lst;
	private Board subject;
	
	public BrickCommand(Board subject, Brick[][] bricks) {
		this.subject = subject;
		this.bricks = bricks;
		this.lst = new ArrayList<Helper>();
	}
	public void saveBrick(int i, int j) {
		Helper tmp = new Helper(i, j);
		this.lst.add(tmp);
	}
	
	@Override
	public void execute(int dx, int dy) {
		return;
	}
	@Override
	public void undo() {
		Helper tmp;
		if(this.lst.size() > 0) {
			tmp = this.lst.get(this.lst.size()-1);
			this.bricks[tmp.x][tmp.y].setDestroyed(false);
			this.subject.setBrick(this.bricks);
			this.lst.remove(this.lst.size()-1);
		}
	}
	@Override
	public void save() {
	}
	@Override
	public void update(Ball ball, Paddle paddle, Clock clock, Brick[][] brick) {
		this.bricks = brick;
	}
	//holder of 2 ints which location of brick
	private class Helper {
		private int x;
		private int y;
		
		public Helper(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
