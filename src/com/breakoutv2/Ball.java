package com.breakoutv2;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Name:
 * 		Ball.java
 * Function:
 * 		Initiates Ball and places it on the Board
 * 		Implementes the update method from observer to receive change in states
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public class Ball extends Structure implements Observer {
    // Variables
    private final int SLOWDOWN = 5;
    private boolean onScreen;
    private int xDir = 1, yDir = -1;

    // Constructor
    public Ball(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        setOnScreen(true);
    }

    // Draw the ball
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }


    // Resets the ball to original position at center of screen
    public void reset() {
        x = BALL_X_START;
        y = BALL_Y_START;
        xDir = 1;
        yDir = -1;
    }

    // Mutator methods
    public void setXDir(int xDir) {
        this.xDir = xDir;
    }

    public void setYDir(int yDir) {
        this.yDir = yDir;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

    // Accessor methods for location on board
    public int getXDir() {
        return xDir;
    }

    public int getYDir() {
        return yDir;
    }

    public boolean isOnScreen() {
        return onScreen;
    }
    
	public void move(int dx, int dy) {
		setYDir(dy);
		setXDir(dx);
	}

    @Override
    public void update(Ball ball, Paddle paddle, Clock clock, Brick[][] brick) {
        x += xDir * TIME_STEP / SLOWDOWN;
        y += yDir * TIME_STEP / SLOWDOWN;
    }
}