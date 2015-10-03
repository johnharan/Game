package data;
import java.lang.management.ManagementFactory;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Timer;
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
	private static Ball pong;

	public Boot() {
		beginSession();
		

		Texture paddle = loadTexture("res/paddle.png","PNG");
		Texture numbers = loadTexture("res/numbers.png","PNG");
		
		new Scoreboard(3, 9); // pointsPerGame, rounds
		
		paddleLeft = new Paddle(paddle, 150, 278, 64, 128);
		paddleRight = new Paddle(paddle, 1700, 277, 64, 128);
		pong = new Ball(20, 15, 1.5f, Display.getWidth()/2, 360);


		long nextSecond = System.currentTimeMillis() + 1000;
		lastTime = System.nanoTime();

		
		
		while(!shutdown){
			currentTime = System.nanoTime();
			delta = (currentTime - lastTime) / 1000000; // delta is the duration of time since last frame/iteration of loop
			lastTime = currentTime;	
	
			if(delta >= 20){ // restricting delta time to 20ms
				delta = 20;
			}
		    
			update(); // checks for escape key
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clears screen each time.. don't need this if drawing background
			
			drawNet();
			
			drawNumbers(numbers,Scoreboard.getLeftPaddlePoints(),Display.getWidth()/2 - 420,50);
			drawNumbers(numbers,Scoreboard.getLeftPaddleRounds(),Display.getWidth()/2 - 300,50);
			
			drawNumbers(numbers,Scoreboard.getRightPaddlePoints(),Display.getWidth()/2 + 180,50);
			drawNumbers(numbers,Scoreboard.getRightPaddleRounds(),Display.getWidth()/2 + 300,50);
			
			paddleLeft.update();
			paddleLeft.draw();
			paddleRight.updateAI(pong,delta);
			paddleRight.draw();
			
			if(pong.isAlive()){
				pong.update();
				pong.draw();
				// the below if block uses a new thread to decay the ai's performance over time, the performance resets after a score
				if(Thread.activeCount() <= 2){ // this allows the main thread plus max of one timer thread
					Thread timedMissAdjustment = new Thread(new Runnable() {
					     public void run() {
					          try {
								Thread.sleep(2000); // wait 2 seconds
								paddleRight.setLow(paddleRight.getLow() + 5);
								paddleRight.setMax(paddleRight.getMax() + 10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
					     }
					});  
					timedMissAdjustment.start();
				}
			}else{
				pong.update();
				if(Thread.activeCount() <= 2){ // this allows the main thread plus max of one timer thread
					Thread timedRespawn = new Thread(new Runnable() {
					     public void run() {
					          try {
								Thread.sleep(1000); // wait 1 second then respawn ball
								pong.respawn();
								paddleRight.setLow(0);
								paddleRight.setMax(0);
								paddleRight.setOffset(0); // resets the ai's offset
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
					     }
					});  
					timedRespawn.start();
				}
			}
			//System.out.println("Low: " + paddleRight.getLow() + ",High: " + paddleRight.getMax());
			
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
