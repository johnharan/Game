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
			int sides = 40; // number of lines to draw
			double radius = 200;
			
			glBegin(GL_LINE_LOOP);
				
			for (int i = 0; i < 360; i += 360 / sides)
			{
			    double angle = i * 3.1415926535897932384626433832795 / 180; // increment angle by i each iteration
			    glVertex2d(640 + Math.cos(angle) * radius, 360 + Math.sin(angle) * radius);
			}
			
			glEnd();
			
			glBegin(GL_POINTS);
			glVertex2d(640,360);
			
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
