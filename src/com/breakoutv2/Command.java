package com.breakoutv2;

/*
 * Command.java
 * 
 * interface for movement commands to help save state
 * 
 * Andres, Shruti, Vivek, and Yash
 */
public interface Command {
	public void execute(int dx, int dy);
	public void undo();
	public void save();
}
