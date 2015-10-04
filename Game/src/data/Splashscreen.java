package data;

import static helpers.Artist.drawQuadTexture;
import static helpers.Artist.loadTexture;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class Splashscreen {
	
	private static boolean playAgain = false;
	
	public Splashscreen() {
		
		
	}
	
	public static void displayGameOver(){
		Texture game_over = loadTexture("res/game_over.png","PNG");
		drawQuadTexture(game_over,Display.getWidth()/2 - 300,Display.getHeight()/2 - 200,747,103);
		
	}
	
	public static void displayPlayAgain(){
		Texture play_again = loadTexture("res/play_again.png","PNG");
		drawQuadTexture(play_again,Display.getWidth()/2 - 150,Display.getHeight()/2,366,143);
	}

	public static void detectClick(){
		int mouseY = Display.getHeight() - Mouse.getY(); // need to invert mouse y
		// below checks for mouse click inside playAgain box
		if(Mouse.isButtonDown(0) && Mouse.getX() >= Display.getWidth()/2 - 148 && Mouse.getX() <= Display.getWidth()/2 - 150 + 261 && mouseY >= Display.getHeight()/2 && mouseY <= Display.getHeight()/2 + 75){
			playAgain = true;
		}	
	}

	public static boolean getPlayAgain() {
		return playAgain;
	}

	public static void setPlayAgain(boolean playAgain) {
		Splashscreen.playAgain = playAgain;
	}

	
}
