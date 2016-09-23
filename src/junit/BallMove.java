package junit;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.breakoutv2.Ball;



public class BallMove {
	Ball ball;
	int x_default = 245;
    int y_default = 245;
    int xDir_default = 1;
    int yDir_default = -1;
    Color c1;
    int x_reset = 245;
    int y_reset = 245;
    int xDir_reset = 1;
    int yDir_reset = -1;
    
    @Before
	public void executedBeforeEach() {
		ball = new Ball(1, 1, 1, 1, c1);
	}
    
	@Test
	public void test() {
		ball.reset();
		x_reset=ball.getX();
		assertEquals(x_default, x_reset);
		//fail("Not yet implemented"); // TODO
	}

}
