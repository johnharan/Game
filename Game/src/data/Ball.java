package data;

import static helpers.Artist.drawCircle;

import org.lwjgl.opengl.Display;



public class Ball {

	private int radius,sides;
	private float speed,x,y;
	private boolean alive;
	private float[] direction;

	
	
	public Ball(int radius, int sides, float speed, float x, float y) {
		this.radius = radius;
		this.sides = sides;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.direction = new float[2];
		this.direction[0] = 1.0f;
		this.direction[1] = 0.0f;
	}

	public void update(){
		findDirection();
		x += Boot.getDelta() * direction[0] * speed;
		y += Boot.getDelta() * direction[1] * speed;
		
	}
	
	public void draw(){
		drawCircle(x,y,radius,sides);
	}
	
	public void findDirection(){
		int randomNum = 1 + (int)(Math.random() * ((2 - 1) + 1)); // random number between 1 and 2
		float lastY = randomNum == 1? 1:-1; // random number used to choose random sign, also determines ball y direction for first collision at start of round
		
		if(direction[1] != 0.0){ // gets the last Y direction - which is then used below to ensure ball follows correct path
			lastY = direction[1];
		}
		
		//// collision detection for left screen border and front of left paddle
		if(x - 17 <= 0 || (x-25 <= Boot.getPaddleLeft().getX() + Boot.getPaddleLeft().getWidth() && x-25 >= Boot.getPaddleLeft().getX() && y+19 > Boot.getPaddleLeft().getY() && y-21 < Boot.getPaddleLeft().getY() + Boot.getPaddleLeft().getHeight() )){
			float randomAngle = 0.5f + (float)(Math.random() * ((2.0f - 1.0f) + 1.0f));
			System.out.println(randomAngle);
			direction[0] = 1.0f; // puts ball x in opposite direction
			if(lastY <= -1){ // puts ball y in same direction
				direction[1] = -1.0f;
			}else{
				direction[1] = 1.0f;
			}
		}
		//// collision detection for right screen border and front of right paddle
		if(x + 25 >= Display.getWidth() || (x+19 >= Boot.getPaddleRight().getX() && x+19 <= Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth() && y+18 > Boot.getPaddleRight().getY() && y-17 < Boot.getPaddleRight().getY() + Boot.getPaddleRight().getHeight())){
			direction[0] = -1.0f; // puts ball x in opposite direction
			if(lastY <= -1){ // puts ball y in same direction
				direction[1] = -1.0f; 
			}else{
				direction[1] = 1.0f;
			}
			
		}
		////
		
		if(y >= Display.getHeight()){ // reverses ball direction if moving past bottom of screen border
			direction[1] = -1.0f;
		}
		if(y <= 0){ // reverses ball direction if moving past top of screen border
			direction[1] = 1.0f;
		}
		
		// collision detection for top and bottom of left paddle, a ball hitting top of paddle approaches with a +1 direction,  a ball hitting top of paddle approaches with a -1 direction
		if(y >= Boot.getPaddleLeft().getY()-28 && y <= Boot.getPaddleLeft().getY()-5 && direction[1] == 1.0f && x <= (Boot.getPaddleLeft().getX() + Boot.getPaddleLeft().getWidth()) && x >= Boot.getPaddleLeft().getX()){
			direction[1] = -1.0f; // ball hit top of paddle, so reverse y direction
		}
		if(y <= (Boot.getPaddleLeft().getY()+28 + Boot.getPaddleLeft().getHeight()) && y >= (Boot.getPaddleLeft().getY()+5 + Boot.getPaddleLeft().getHeight()) && direction[1] == -1.0f  && x <= (Boot.getPaddleLeft().getX() + Boot.getPaddleLeft().getWidth()) && x >= Boot.getPaddleLeft().getX()){
			direction[1] = 1.0f; // ball hit bottom of paddle, so reverse y direction
		}
		/////
		// collision detection for top and bottom of right paddle
		if(y >= Boot.getPaddleRight().getY()-28 && y <= Boot.getPaddleRight().getY()-5 && direction[1] == 1.0f && x <= (Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth()) && x >= Boot.getPaddleRight().getX()){
			direction[1] = -1.0f;
		}
		if(y <= (Boot.getPaddleRight().getY()+28 + Boot.getPaddleRight().getHeight()) && y >= (Boot.getPaddleRight().getY()+5 + Boot.getPaddleRight().getHeight()) && direction[1] == -1.0f  && x <= (Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth()) && x >= Boot.getPaddleRight().getX()){
			direction[1] = 1.0f;
		}
		//////
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void die(){
		alive = false;
	}

	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public float getSpeed() {
		return speed;
	}


	public void setSpeed(float speed) {
		this.speed = speed;
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}
	
	public float[] getDirection(){
		return direction;
	}

}
