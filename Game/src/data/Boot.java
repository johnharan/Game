package data;
import org.lwjgl.LWJGLException;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;
import static data.Ball.*;

public class Boot {
    
	static final int WIDTH = 1280,HEIGHT = 720, MAX_FRAMES_SKIPPED = 5;
	static final float MS_PER_UPDATE = 16.6f;
	
	private long currentTime,lastTime;
	private static long delta;
	private int framesInLastSecond = 0,framesInCurrentSecond = 0;

	
	
	public Boot() {
		beginSession();
		

		Texture paddle = loadTexture("res/paddle.png","PNG");
			
		
		Ball pong = new Ball(15, 10, 0.1f, 640, 360);
		//pong.update();
		//pong.draw();
		
		//drawCircle(360, 360, 20, 10);
		
		
		
		long nextSecond = System.currentTimeMillis() + 1000;
		lastTime = System.nanoTime();
		while(!Display.isCloseRequested()){
			currentTime = System.nanoTime();
			delta = (currentTime - lastTime) / 1000000;
			lastTime = currentTime;	
	
			if(delta >= 20){
				delta = 20;
			}
			
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clears screen each time.. don't need this if drawing background
			
			drawQuadTexture(paddle, 100, 300, 64, 128);
			
			pong.update();
			pong.draw();
			
			
			
		    
		    //////////////////////////
			
			Display.update();
			Display.sync(60);
		
			// //////////////////FPS///////////////////////// 
			long currentTimeT = System.currentTimeMillis();
			if (currentTimeT > nextSecond) {
				nextSecond += 1000;
				framesInLastSecond = framesInCurrentSecond;
				framesInCurrentSecond = 0;
			}
			framesInCurrentSecond++;
			////////////////////////////////////////////////
			
			System.out.println("Delta: " + delta + ",FPS: " + framesInLastSecond);
		}
		
		Display.destroy();
		
	}


	public static void main(String[] args) {
		new Boot();

	}

	public static Long getDelta(){
		return delta;
	}

}
