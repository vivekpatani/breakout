package com.breakoutv2;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JPanel;

/*
 * Name:
 * 		Board.java
 * Function:
 * 		Board is the concrete subject class that updates its observers
 * 		once changes have been made. 
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public class Board extends JPanel implements Runnable, Constants, Subject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int STATUS_LOCATION_X = 10;
    //commands
    private MacroUndoCommand macroUndoCommand;
    private PaddleMoveCommand paddleMoveCommand;
    private ClockIncrementCommand clockIncrementCommand;
    private BallMoveCommand ballMoveCommand;
    private BrickCommand brickCommand;
    // Items on-screen
    private Paddle paddle;
    private Ball ball;
    private Clock clock;
    private Brick[][] brick = new Brick[BRICK_COLUMNS][BRICK_ROWS];
    private List<Observer> observers;

    // Initial Values for some important variables
    private int score = 0, lives = MAX_LIVES, bricksLeft = MAX_BRICKS,
             xSpeed, ySpeed, level = 1;

    // Player's name
    //private String playerName;

    // The game
    private Thread game;

    // Paused state
    private volatile boolean isPaused = true;

    // Constructor
    public Board(int width, int height) {
        super.setSize(width, height);
        addKeyListener(new BoardListener());
        setFocusable(true);

        makeBricks();
        paddle = new Paddle(PADDLE_X_START, PADDLE_Y_START, PADDLE_WIDTH,
                PADDLE_HEIGHT, Color.BLACK);
        ball = new Ball(BALL_X_START, BALL_Y_START, BALL_WIDTH, BALL_HEIGHT,
                Color.BLACK);
        clock = new Clock(STATUS_LOCATION_X, getHeight() - getHeight()/2, BALL_WIDTH, BALL_HEIGHT,
                Color.red);
        this.paddleMoveCommand = new PaddleMoveCommand(this, paddle);
        this.clockIncrementCommand = new ClockIncrementCommand(clock);
        this.ballMoveCommand = new BallMoveCommand(this, ball);
        this.brickCommand = new BrickCommand(this, this.brick);
        //undo macro registering elements in command list
        this.macroUndoCommand = new MacroUndoCommand();
        this.macroUndoCommand.add(this.paddleMoveCommand);
        this.macroUndoCommand.add(this.ballMoveCommand);
        this.macroUndoCommand.add(this.clockIncrementCommand);
        this.macroUndoCommand.add(brickCommand);
        
        observers = new ArrayList<Observer>();
        register(ball);
        register(clock);
        register(this.paddleMoveCommand);
        register(this.clockIncrementCommand);
        register(this.ballMoveCommand);
        register(this.brickCommand);
        game = new Thread(this);
        game.start();

    }
    
    //Accessors
    public boolean getIsPaused() {
    	return this.isPaused;
    }
    public int getLives() {
    	return this.lives;
    }
    public int getBricksLeft() {
    	return this.bricksLeft;
    }
    public int getScore() {
    	return this.score;
    }
    public Ball getBall() {
    	return this.ball;
    }
    public Clock getClock() {
    	return this.clock;
    }
    public BallMoveCommand getBallMoveCommand() {
    	return this.ballMoveCommand;
    }
    public MacroUndoCommand getMacroUndoCommand() {
    	return this.macroUndoCommand;
    }
    public Thread getThread() {
    	return this.game;
    }
    public void setThread(Thread thread) {
    	this.game = thread;
    }

    //Setters
    public void setBricksLeft(int num) {
    	this.bricksLeft = num;
    }
    public void setLives(int num) {
    	this.lives = num;
    }
    public void setLevel(int num) {
    	this.level = num;
    }
    public void setScore(int num) {
    	this.score = num;
    }
    protected void setBrick(Brick[][] brick) {
    	this.brick = brick;
    }
    protected void setBrick(Brick brick, int i, int j) {
    	this.brick[i][j] = brick;
    }
    protected void setBall(Ball ball) {
    	this.ball = ball;
    }
    protected void setPaddle(Paddle paddle) {
    	this.paddle = paddle;
    }
    protected void setClock(Clock clock) {
    	this.clock = clock;
    }
    // fills the array of bricks
    protected void makeBricks() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                brick[i][j] = new Brick((i * BRICK_WIDTH) + 10,
                        ((j * BRICK_HEIGHT) + (BRICK_HEIGHT / 2)),
                        BRICK_WIDTH - 5, 
                        BRICK_HEIGHT - 5, 
                        Color.blue);
            }
        }
    }

    // starts the thread
    protected void start() {
        isPaused = false;
        if(this.game == null) {
        	System.out.println("Game is null");
        	this.game = new Thread(this);
        	this.game.start();
        }
    }

    // stops the thread
    void stop() {
        isPaused = true;
    }

    // runs the game
    public void run() {
        xSpeed = 1;
        while (true) {
            while (!isPaused) {
                int x1 = ball.getX();
                int y1 = ball.getY();

                checkPaddle(x1, y1);
                checkWall(x1, y1);
                checkBricks(x1, y1);
                checkLives();
                checkIfOut(y1);
                notifyObservers();
                repaint();

                try {
                    Thread.sleep(TIME_STEP);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    private void checkLives() {
        if (bricksLeft == NO_BRICKS) {
            ball.reset();
            bricksLeft = MAX_BRICKS;
            makeBricks();
            lives++;
            level++;
            score += 100;
            repaint();
            stop();
            //isPaused = true;
        }
        if (lives == MIN_LIVES) {
            repaint();
            stop();
            //isPaused = true;
        }
    }

    private void checkPaddle(int x1, int y1) {
        if (paddle.hitPaddle(x1, y1) && ball.getXDir() < 0) {
            xSpeed = -1;
            ySpeed = -1;
            /****************************************/
        	macroUndoCommand.save();
            ballMoveCommand.execute(xSpeed, ySpeed);
        }
        if (paddle.hitPaddle(x1, y1) && ball.getXDir() > 0) {
            xSpeed = 1;
            ySpeed = -1;
            /****************************************/
        	macroUndoCommand.save();
            ballMoveCommand.execute(xSpeed, ySpeed);
        }
        if (paddle.getX() <= 0) {
            paddle.setX(0);
        }
        if (paddle.getX() + paddle.getWidth() >= getWidth()) {
            paddle.setX(getWidth() - paddle.getWidth());
        }
    }

    private void checkWall(int x1, int y1) {
        if (x1 >= getWidth() - ball.getWidth()) {
            xSpeed = -Math.abs(xSpeed);
            /****************************************/
        	macroUndoCommand.save();
            ballMoveCommand.execute(xSpeed, ball.getYDir());
        }
        if (x1 <= 0) {
            xSpeed = Math.abs(xSpeed);
            /****************************************/
        	macroUndoCommand.save();
        	ballMoveCommand.execute(xSpeed, ball.getYDir());
        }
        if (y1 <= 0) {
            ySpeed = 1;
            /****************************************/
        	macroUndoCommand.save();
            ballMoveCommand.execute(ball.getXDir() , ySpeed);
        }
        if (y1 >= getHeight()) {
        	ySpeed = -1;
        	/****************************************/
        	macroUndoCommand.save();
        	ballMoveCommand.execute(ball.getXDir(), ySpeed);
        }
    }

    private void checkBricks(int x1, int y1) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (brick[i][j].hitBottom(x1, y1)) {
                	ySpeed = 1;
                	/****************************************/
                	brickCommand.saveBrick(i, j);
                	macroUndoCommand.save();
                    ballMoveCommand.execute(ball.getXDir(), ySpeed);
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
                if (brick[i][j].hitLeft(x1, y1)) {
                    xSpeed = -xSpeed;
                    /****************************************/
                    brickCommand.saveBrick(i, j);
                    macroUndoCommand.save();
                    ballMoveCommand.execute(xSpeed, ball.getYDir());
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
                if (brick[i][j].hitRight(x1, y1)) {
                    xSpeed = -xSpeed;
                    /****************************************/
                    brickCommand.saveBrick(i, j);
                    macroUndoCommand.save();
                    ballMoveCommand.execute(xSpeed, ball.getYDir());
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
                if (brick[i][j].hitTop(x1, y1)) {
                    //ball.setYDir(-1);
                    ySpeed = -1;
                    /****************************************/
                    brickCommand.saveBrick(i, j);
                    macroUndoCommand.save();
                    ballMoveCommand.execute(ball.getXDir(), ySpeed);
                    if (brick[i][j].isDestroyed()) {
                        bricksLeft--;
                        score += 50;
                    }
                }
            }
        }
    }

    private void checkIfOut(int y1) {
        if (y1 > PADDLE_Y_START + 10) {
            lives--;
            score -= 100;
            ball.reset();
            clock.reset();
            repaint();
            stop();
        }
    }

    // fills the board
    @Override
    public void paintComponent(Graphics g) {
     // Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        clock.draw(g);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                brick[i][j].draw(g);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("Lives: " + lives, STATUS_LOCATION_X, getHeight() - (getHeight() / 10));
        g.drawString("Score: " + score, STATUS_LOCATION_X, getHeight()
                - (2 * (getHeight() / 10)) + 25);
        g.drawString("Level: " + level, STATUS_LOCATION_X, getHeight()
                - (3 * (getHeight() / 10)) + 50);

        if (lives == MIN_LIVES) {
            g.drawString("Game Over. Press the 'Play' on the right Button to play again.",
                    getWidth() / 5, getHeight() - 20);
        }
    }

    public void register(Observer observer) {
        observers.add(observer);
    }
    
    public void removeRegister(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers() {
    	//clockIncrementCommand.execute(0, 0);
        for (Observer observer : observers) {
            observer.update(this.ball, this.paddle, this.clock, this.brick);
        }
    }
    // Private class that handles gameplay and controls
    private class BoardListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
            	/****************************************/
                macroUndoCommand.save();
                paddleMoveCommand.execute(-50, 0);
            }
            if (key == KeyEvent.VK_RIGHT) {
            	/****************************************/
                macroUndoCommand.save();
                paddleMoveCommand.execute(50, 0);
            }
        }
        
    }
    
}
