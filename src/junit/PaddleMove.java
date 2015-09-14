package junit;

import static org.junit.Assert.*;

import java.awt.Color;

import com.breakoutv2.Paddle;

import org.junit.Before;
import org.junit.Test;

public class PaddleMove {
	Color c1;
	Paddle paddle;
	//int Paddlex=225;
	int Paddledx=275;
	int dx=50;
	int dy=0;

    @Before
	public void executedBeforeEach() {
    	paddle = new Paddle(225, 450, 1, 1, c1);
	}
    
	@Test
	public void test() {
		
		paddle.move(dx,dy);
		Paddledx=paddle.getX();
		System.out.println("paddledx");
		System.out.println(Paddledx);
		assertEquals(275, Paddledx);
		//fail("Not yet implemented");
	}

}
