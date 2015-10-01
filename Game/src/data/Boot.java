package data;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;
import static data.Ball.*;

public class Boot {
    
	static final int WIDTH = Display.getWidth(),HEIGHT = Display.getHeight(), MAX_FRAMES_SKIPPED = 5;
	static final float MS_PER_UPDATE = 16.6f;
	
	private long currentTime,lastTime;
	private static long delta;
	private int framesInLastSecond = 0,framesInCurrentSecond = 0;
    private static Paddle paddleLeft;
    private static Paddle paddleRight;
    private static boolean shutdown = false;
	
	
	public Boot() {
		beginSession();
		

		Texture paddle = loadTexture("res/paddle.png","PNG");
		
		paddleLeft = new Paddle(paddle, 150, 278, 64, 128);
		paddleRight = new Paddle(paddle, 1700, 277, 64, 128);
		Ball pong = new Ball(20, 15, 1.7f, 250, 360);
		//pong.update();
		//pong.draw();
		
		//drawCircle(360, 360, 20, 10);
		
		
		
		
		long nextSecond = System.currentTimeMillis() + 1000;
		lastTime = System.nanoTime();
		while(!shutdown){
			currentTime = System.nanoTime();
			delta = (currentTime - lastTime) / 1000000;
			lastTime = currentTime;	
	
			if(delta >= 20){
				delta = 20;
			}
		    
			update(); // checks for escape key
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clears screen each time.. don't need this if drawing background
			
			paddleLeft.update();
			//paddleLeft.updateAI(pong);
			paddleLeft.draw();
			paddleRight.updateAI(pong);
			paddleRight.draw();
			
			pong.update();
			pong.draw();
			
			
			
		    
		    //////////////////////////
			
			Display.update();
			Display.sync(60);
		
			/////////////////////FPS///////////////////////// 
			long currentTimeT = System.currentTimeMillis();
			if (currentTimeT > nextSecond) {
				nextSecond += 1000;
				framesInLastSecond = framesInCurrentSecond;
				framesInCurrentSecond = 0;
			}
			framesInCurrentSecond++;
			////////////////////////////////////////////////
			
			//System.out.println("Delta: " + delta + ",FPS: " + framesInLastSecond);
		}
		
		Display.destroy();
		
	}


	public static Paddle getPaddleLeft() {
		return paddleLeft;
	}

	public static Paddle getPaddleRight() {
		return paddleRight;
	}

	public static void main(String[] args) {
		new Boot();
        
	}
	
	public void update(){
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
				shutdown = true;
			}
		}	
	}

	public static Long getDelta(){
		return delta;
	}

}
