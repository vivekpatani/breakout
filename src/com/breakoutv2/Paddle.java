package com.breakoutv2;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Name:
 * 		Paddle.java
 * Function:
 * 		Extends the static structure. Special case that is controlled by the user
 * 		through the keyboard. In the game, we move paddle from right to left, and
 * 		vice-versa. Draws the item on the board.
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public class Paddle extends Structure {
    // Variables
    private int xSpeed;

    // Constructor
    public Paddle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    // Draws the paddle
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Places the paddle back in starting position at center of screen
    public void reset() {
        x = PADDLE_X_START;
        y = PADDLE_Y_START;
    }

    // Checks if the ball hit the paddle
    public boolean hitPaddle(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width)
                && ((ballY >= y) && (ballY <= y + height))) {
            return true;
        }
        return false;
    }

}