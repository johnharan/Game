package data;

import java.util.HashMap;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import stateManager.GameState;
import stateManager.Play;
import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;


public class Boot {
    
	static final int WIDTH = Display.getWidth(),HEIGHT = Display.getHeight(), MAX_FRAMES_SKIPPED = 5;
	static final float MS_PER_UPDATE = 16.6f;
	
	private long currentTime,lastTime;
	private int framesInLastSecond = 0,framesInCurrentSecond = 0;
    private static Paddle paddleLeft;
    private static Paddle paddleRight;
    private static boolean shutdown = false;
	private static Ball pong;
	private static Ball testPong;
	private static int game_state;
	private int plX;
	private int plY;
	private int prX;
	private int prY;
	private int paddleWidth;
	private int paddleHeight;
	private int ballRadius;
	private int ballSides;
	private int ballX;
	private int ballY;
	private float ballSpeed;
	private static Scoreboard scores;
	private static Splashscreen splash;
	private static HashMap<String, SoundPlayer> sfx;
	private static GameState play;

	public Boot() {
		beginSession();
		
		game_state = 0; // 0 is start, 1 is play, 2 is end;
		
		play = new Play();
		
		paddleWidth = 64;
		paddleHeight = 128;
		plX = 150; 
		plY = 278;
		prX = 1700; 
		prY = 277;
		
		ballRadius = 20;
		ballSides = 15;
		ballX = Display.getWidth()/2;
		ballY = 360;
		ballSpeed = 1.5f;
		
		Texture paddle = loadTexture("res/paddle.png","PNG");
		
		//sfx1 = new SoundPlayer("/res/ball1.mp3");
		//sfx1.play();
		
		/// loading sounds ////
		// each sound creates new thread
		sfx = new HashMap<String, SoundPlayer>();
		sfx.put("ball1", new SoundPlayer("/res/ball1.mp3"));
		sfx.put("ball2", new SoundPlayer("/res/ball2.mp3"));
		sfx.put("clapping", new SoundPlayer("/res/clapping.mp3"));
		sfx.put("booing", new SoundPlayer("/res/booing.mp3"));
		sfx.put("slot_machine", new SoundPlayer("/res/slot_machine.mp3"));
		sfx.put("score", new SoundPlayer("/res/score.mp3"));
		sfx.put("round", new SoundPlayer("/res/round.mp3"));
		/////////
		
		sfx.get("ball1").play();
		
		/////////
		
		splash = new Splashscreen();
		scores = new Scoreboard(3, 2); // pointsPerGame, rounds
		
		paddleLeft = new Paddle(paddle, plX, plY, paddleWidth, paddleHeight);
		paddleRight = new Paddle(paddle, prX, prY, paddleWidth, paddleHeight);
		pong = new Ball(ballRadius, ballSides, ballSpeed, ballX, ballY);
		//testPong = new Ball(20,15,1.5f,900,600);

		

		while(!shutdown){
			Clock.update();
		    
			update(); // checks for escape key
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clears screen each time.. don't need this if drawing background
			
			drawNet();
			
			
			
			if(game_state == 1){ // play state
				play.updateState();
				play.drawState();
				//System.out.println("Low: " + paddleRight.getLow() + ",Max: " + paddleRight.getMax());
			    //System.out.println("Threads: " + Thread.activeCount());
			
			}else if(game_state == 2){ // end game state
				scores.update();
				paddleLeft.draw();
				paddleRight.draw();
				splash.update();
				splash.detectClick();
				
				// if reset button clicked , reset game and go to state 0
				if(splash.getPlay() == true){
					sfx.get("slot_machine").play();
					scores.reset();
					paddleLeft.resetPaddle(plX, plY, paddleWidth, paddleHeight);
					paddleRight.resetPaddle(prX, prY, paddleWidth, paddleHeight);
					game_state = 1;
					splash.setPlay(false);
				}
			}else{ // start state
				splash.update();
				splash.detectClick();
				
				
				if(splash.getPlay() == true){
					sfx.get("slot_machine").play();
					game_state = 1;
					splash.setPlay(false);
				}
				
				
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


	public static HashMap<String, SoundPlayer> getSfx() {
		return sfx;
	}


	public static Scoreboard getScores() {
		return scores;
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


	
}
