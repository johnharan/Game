package data;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.drawQuadTexture;

public class Paddle {

	private float x,y,width,height;
	private Texture texture;
	
	public Paddle(Texture texture, float x, float y, float width, float height) {
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		
		//x = Mouse.getX();
		y = Display.getHeight() - Mouse.getY();
		
		if(x >= Display.getWidth()/2 - width){
			x = Display.getWidth()/2 - width;
		}
		if(y >= Display.getHeight() - height){
			y = Display.getHeight() - height;
		}
	}
	
	public void updateAI(Ball ball){
		int randomNum = 0,randomMiss = 0, offset = 0;

		
		if(ball.getX() > Display.getWidth()/2 + 100){
			randomMiss = 100 + (int)(Math.random() * ((200 - 1) + 1));
			randomNum = 1 + (int)(Math.random() * ((2 - 1) + 1));
			offset = randomNum == 1? randomMiss:-randomMiss; 
			System.out.println("randomMiss: " + randomMiss + ", randomNum: " + randomNum + ", offset: " + offset);
			y +=  offset;
		}
		
		//System.out.println("ball x: " + (int)ball.getX() + ", display width: " + Display.getWidth()/2);
		if(ball.getX() > Display.getWidth()/2){ 
			
			y = ball.getY(); // problem is paddle still updates to ball.getY
				// need to make paddle move in direction of ball, not equal position of ball
	
		}
		
		if(y >= Display.getHeight() - height){
			y = Display.getHeight() - height;
		}
		
	}
	
	public void draw(){
		drawQuadTexture(texture,x,y,width,height);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	

}
