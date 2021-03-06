package com.breakoutv2;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Name:
 * 		Brick.java
 * Function:
 * 		Extends the functionality of Structure. Brick is one of the
 * 		static objects on the board.
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public class Brick extends Structure implements Constants {
    // Variables
    private int lives, hits;
    private boolean destroyed;

    // Constructor
    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        setHits(0);
        setDestroyed(false);

    }

    // Draws a brick
    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    // Add a hit to the brick, and destroy the brick when hits == lives
    public void addHit() {
        setDestroyed(true);
    }

    // Detect if the brick has been hit on its bottom, top, left, or right sides
    public boolean hitBottom(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y + height)
                && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    public boolean hitTop(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y)
                && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    public boolean hitLeft(int ballX, int ballY) {
        if ((ballY >= y) && (ballY <= y + height) && (ballX == x)
                && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    public boolean hitRight(int ballX, int ballY) {
        if ((ballY >= y) && (ballY <= y + height) && (ballX == x + width)
                && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    // Mutator methods
    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    // Accessor methods
    public int getLives() {
        return lives;
    }

    public int getHits() {
        return hits;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}