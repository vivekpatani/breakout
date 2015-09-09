package com.breakoutv2;

/*
 * ClockIncrementCommand
 * 
 * Clock command only keeps track of the previous time displayed.
 * 
 * Andres, Shruti, Vivek, and Yash
 */
public class ClockIncrementCommand implements Command{
	private Clock clock;
	private int prevTime;

	public ClockIncrementCommand(Clock clock) {
		this.clock = clock;
	}
	@Override
	public void execute(int dx, int dy) {
		this.prevTime = this.clock.getTime();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		this.clock.setTime(this.prevTime);
	}

}
