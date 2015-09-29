package data;

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
