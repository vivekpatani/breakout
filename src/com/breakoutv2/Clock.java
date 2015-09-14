package com.breakoutv2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/*
 * Name:
 * 		Clock.java
 * Function:
 * 		Observes the time spent in the game. The Clock pulls the information
 * 		from the subject when necessary.
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public class Clock  extends Structure implements Observer {
    private static final int MILLISEC_TO_SEC = 1000;
    private int time;
    
    public Clock(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        time = 0;
    }
    
    //reset clock back to 0
    public void reset() {
    	this.time = 0;
    }
    
    public int getTime() {
    	return this.time;
    }

	public void setTime(int prevTime) {
		this.time = prevTime;
	}
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g.setFont(newFont);
        int mm = time / MILLISEC_TO_SEC / 60 % 60;
        int ss = time / MILLISEC_TO_SEC % 60;
        g.drawString(String.format("%02d:%02d", mm, ss),
                x, y);
        g.setFont(currentFont);
    }

    @Override
    public void update(Ball ball, Paddle paddle, Clock clock) {
        //time = (time + timeStep) % (3600 * MILLISEC_TO_SEC);
    	this.time = clock.getTime() + TIME_STEP;
    }
}
