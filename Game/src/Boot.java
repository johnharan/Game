import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Boot {
    
	private static final int WIDTH = 1280, HEIGHT = 720;
	
	public Boot() {
		Display.setTitle("Game");
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,WIDTH,HEIGHT,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()){
			
			glBegin(GL_LINES);
			glVertex2f(20,20);
			glVertex2f(200,200);
			glEnd();
			
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		
	}


	public static void main(String[] args) {
		new Boot();

	}

}
