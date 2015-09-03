package com.breakoutv2;

/*
 * Name:
 * 		Subject.java
 * Function:
 * 		Interface for subjects. Allows observers to subscribe and unsubscribe
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public interface Subject {
	
	public void register(Observer o);
	public void removeRegister(Observer o);
	public void notifyObservers();
}
