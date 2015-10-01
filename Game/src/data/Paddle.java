package data;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.drawQuadTexture;

public class Paddle {

	private float x,y,width,height;
	private Texture texture;
	private int randomPosNeg = 0,randomMiss = 0, offset = 0;
	
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
		
		if(ball.getX() > Display.getWidth()/2 - 30 && ball.getX() < Display.getWidth()/2 && ball.getDirection()[0] > 0){
			randomMiss = 50 + (int)(Math.random() * ((150 - 50) + 1)); // number of pixels that paddle will miss ball by
			randomPosNeg = 1 + (int)(Math.random() * ((2 - 1) + 1)); // 1 is positive offset, 2 is negative
			offset = randomPosNeg == 1? randomMiss:-randomMiss; 
			//System.out.println("randomMiss: " + randomMiss + ", randomNum: " + randomNum + ", offset: " + offset + ",y: " + y);
		}
		

		if(ball.getX() > Display.getWidth()/2){ 
			y = ball.getY() - height/2 + offset; // height/2 ensures ball hits center of paddle, offset adds random adjustment
			
		}
		System.out.println("ball x: "+ball.getX() + ",ball y: " + ball.getY() + ",offset: " + offset);

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
