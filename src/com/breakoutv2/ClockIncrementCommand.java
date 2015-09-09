package com.breakoutv2;

import java.util.ArrayList;

/*
 * ClockIncrementCommand
 * 
 * Clock command only keeps track of the previous time displayed.
 * 
 * Andres, Shruti, Vivek, and Yash
 */
public class ClockIncrementCommand implements Command{
	private Clock clock;
	private ArrayList<Integer> prevTime;

	public ClockIncrementCommand(Clock clock) {
		this.clock = clock;
		this.prevTime = new ArrayList<Integer>();
	}
	@Override
	public void execute(int dx, int dy) {
		this.prevTime.add(this.clock.getTime());
	}

	@Override
	public void undo() {
		if(this.prevTime.size() > 0) {
			this.clock.setTime(this.prevTime.get(this.prevTime.size()-1));
			this.prevTime.remove(this.prevTime.size()-1);
			//TODO redraw clock
		}
	}
}
