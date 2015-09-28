import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;

public class Boot {
    
	private static final int WIDTH = 1280, HEIGHT = 720;
	
	public Boot() {
		beginSession();
		

		Texture paddle = loadTexture("res/paddle.png","PNG");
	
		while(!Display.isCloseRequested()){
			
			
			drawQuadTexture(paddle,250,250,64,128);

			//drawQuad(600,300,200,200);
			
			//drawCircle(400,500,20,40);
			
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		
	}


	public static void main(String[] args) {
		new Boot();

	}

}
