package data;

import static helpers.Artist.drawQuadTexture;
import static helpers.Artist.loadTexture;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class Splashscreen {
	
	private static boolean play = false;
	private static Texture game_over = loadTexture("res/game_over.png","PNG");
	private static Texture pong = loadTexture("res/pong.png","PNG");
	private static Texture play_again = loadTexture("res/play_again.png","PNG");
	private static Texture start_game = loadTexture("res/start_game.png","PNG");
	
	
	public static void update(){
		if(Boot.getGameState() == 0){
			drawQuadTexture(pong,Display.getWidth()/2 - 280,Display.getHeight()/2 - 300,743,198);
			drawQuadTexture(start_game,Display.getWidth()/2 - 150,Display.getHeight()/2,366,143);
		}else if(Boot.getGameState() == 2){
			drawQuadTexture(game_over,Display.getWidth()/2 - 300,Display.getHeight()/2 - 200,747,103);
			drawQuadTexture(play_again,Display.getWidth()/2 - 150,Display.getHeight()/2,366,143);
		}
	}


	public static void detectClick(){
		int mouseY = Display.getHeight() - Mouse.getY(); // need to invert mouse y
		// below checks for mouse click inside playAgain box

		if (Mouse.isButtonDown(0)
				&& Mouse.getX() >= Display.getWidth() / 2 - 148
				&& Mouse.getX() <= Display.getWidth() / 2 - 150 + 261
				&& mouseY >= Display.getHeight() / 2
				&& mouseY <= Display.getHeight() / 2 + 75) {
			play = true;
		}
	
	}

	public static boolean getPlay() {
		return play;
	}

	public static void setPlay(boolean play) {
		Splashscreen.play = play;
	}

	
}
