package com.breakoutv2;

/*
 * Name:
 * 		Obeserver.java
 * Function:
 * 		Allows observers to receive updates when subject changes state
 * Collaborators:
 * 		Andres, Shruti, Vivek, Yash
 */

public interface Observer {
    void update(int timeStep);
}
