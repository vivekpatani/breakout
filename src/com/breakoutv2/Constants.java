package com.breakoutv2;

/*
 * Name:
 * 		Constants.java
 * Function:
 * 		Constants used throughout the game.
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public interface Constants {
    // Window Size
    public static final int WINDOW_WIDTH = 510;
    public static final int WINDOW_HEIGHT = 500;
    public static final int PANEL_WIDTH = 100;

    // Lives
    public static final int MAX_LIVES = 5;
    public static final int MIN_LIVES = 0;

    // Ball
    public static final int BALL_WIDTH = 10;
    public static final int BALL_HEIGHT = 10;
    public static final int BALL_RIGHT_BOUND = 490;
    public static final int BALL_X_START = 245;
    public static final int BALL_Y_START = 245;

    // Paddle
    public static final int PADDLE_WIDTH = 70;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_RIGHT_BOUND = 430;
    public static final int PADDLE_X_START = 225;
    public static final int PADDLE_Y_START = 450;
    public static final int PADDLE_MIN = 35;
    public static final int PADDLE_MAX = 140;

    // Bricks
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 25;
    public static final int MAX_BRICKS = 50;
    public static final int NO_BRICKS = 0;
    public static final int BRICK_ROWS = 5;
    public static final int BRICK_COLUMNS = 10;

}