package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import stateManager.End;
import stateManager.GameState;
import stateManager.Play;
import stateManager.Start;
import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;


public class Boot {
    private static Paddle paddleLeft;
    private static Paddle paddleRight;
    private static boolean shutdown = false;
	private static Ball pong;
	private static int game_state;
	private static int paddleWidth = 64, paddleHeight = 128, plX = 150, plY = 278, prX = 1700, prY = 277;
	private int ballRadius = 20, ballSides = 15;
	private float ballSpeed = 1.5f;
	private float ballX, ballY;
	private static GameState start, play, end;

	public Boot() {
		beginSession();
		
		ballX = Display.getWidth()/2; // ballX and ballY must be initialised after beginSession is called
		ballY = Display.getHeight()/2;
		
		game_state = 0; // 0 is start, 1 is play, 2 is end;
		start = new Start();
		play = new Play();
		end = new End();
		
		Scoreboard.setTotalRounds(3); // total rounds
		Scoreboard.setPointsPerGame(3); // pointsPerGame
		
		Texture paddle = loadTexture("res/paddle.png","PNG");
		
		SoundPlayer.loadSounds(); // loads sounds into HashMap stored in SoundPlayer
		
		SoundPlayer.getSounds().get("ball1").play(); // plays intro sound
		
		paddleLeft = new Paddle(paddle, plX, plY, paddleWidth, paddleHeight);
		paddleRight = new Paddle(paddle, prX, prY, paddleWidth, paddleHeight);
		pong = new Ball(ballRadius, ballSides, ballSpeed, ballX, ballY);
		

		while(!shutdown){
			Clock.update();
		    
			isEscapePressed(); // checks for escape key, shuts down if so
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clears screen each time.. don't need this if drawing background
			
			if(game_state == 1){ // play state
				drawNet();
				play.updateState();
				play.drawState();
			}else if(game_state == 2){ // end game state
				end.updateState();
				end.drawState();
			}else{ // start state
				start.updateState();
			}
			
		    //////////////////////////
			
			Display.update();
			Display.sync(60);

			FPS.update(); // count frames per second
			////////////////////////////////////////////////
			
			//System.out.println("Delta: " + Clock.getDelta() + ",FPS: " + FPS.getFPS());
		}
		
		Display.destroy();
	}


	public static int getPlX() {
		return plX;
	}


	public static int getPlY() {
		return plY;
	}


	public static int getPrX() {
		return prX;
	}


	public static int getPrY() {
		return prY;
	}


	public static int getPaddleWidth() {
		return paddleWidth;
	}


	public static int getPaddleHeight() {
		return paddleHeight;
	}


	public static Ball getPong(){
		return pong;
	}

	public static int getGameState() {
		return game_state;
	}


	public static void setGameState(int state) {
		game_state = state;
	}


	public static Paddle getPaddleLeft() {
		return paddleLeft;
	}

	public static Paddle getPaddleRight() {
		return paddleRight;
	}

	public void isEscapePressed(){
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
				shutdown = true;
			}
		}	
	}

	///////// MAIN //////////
	
	public static void main(String[] args) {
		new Boot();
        
	}
	
	
}
