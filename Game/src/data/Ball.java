package data;

import static helpers.Artist.drawCircle;

import org.lwjgl.opengl.Display;



public class Ball {

	private int radius,sides;
	private float speed,x,y;
	private boolean alive = true;
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
		if(isAlive()){ // if ball is alive / in play
			findDirection();
			
			if(direction[1] > -1 && direction[1] < 1){ // this evens out the speed difference between inner/outer paddle shots, without this, shots with a smaller angle travel much slower than higher angled shots
				x += Clock.getDelta() * direction[0] * (speed * 1.5); // speed is 50% faster
			}else{
				x += Clock.getDelta() * direction[0] * speed;
			}
			y += Clock.getDelta() * direction[1] * speed;
		}
		
	}
	
	public void draw(){
		drawCircle(x,y,radius,sides);
	}
	
	public void findDirection(){
		float lastY = 1 + (int)(Math.random() * ((2 - 1) + 1)) == 1? 1:-1; // random number used to choose random sign, also determines ball y direction for first collision at start of round
		 
		String[] sound = {"ball1","ball2"};
		
		
		if(direction[1] != 0){ // gets the last Y direction - which is then used below to ensure ball follows correct path
			lastY = direction[1];
		}
		
		/////////////////////
		//// collision detection for left screen border and front middle half of left paddle
		if(x - 17 <= 0 || (x-25 <= Boot.getPaddleLeft().getX() + Boot.getPaddleLeft().getWidth() && x-25 >= Boot.getPaddleLeft().getX() && y+19 > Boot.getPaddleLeft().getY() + (Boot.getPaddleLeft().getHeight() / 4) - 10 && y-21 < Boot.getPaddleLeft().getY() + 10 + (Boot.getPaddleLeft().getHeight() /4) * 3 )){
			float randInnerAngle = (float) (Math.random() * ((0.7f - 0.1f) + 0.1f)); // random rebound angle for inner quarters of paddles
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			
			direction[0] = 1; // puts ball x in opposite direction
			if(lastY <= 0){ // puts ball y in same direction
				direction[1] = -randInnerAngle;
			}else{
				direction[1] = randInnerAngle;
			}
		}
		//// collision detection for front outer quarters of left paddle
		if((x-25 <= Boot.getPaddleLeft().getX() + Boot.getPaddleLeft().getWidth() && x-25 >= Boot.getPaddleLeft().getX() && ((y+19 > Boot.getPaddleLeft().getY() && y-21 < Boot.getPaddleLeft().getY() - 10 + Boot.getPaddleLeft().getHeight() / 4 ) || (y+19 > Boot.getPaddleLeft().getY() + 10 + (Boot.getPaddleLeft().getHeight() / 4) * 3 && y-21 < Boot.getPaddleLeft().getY() + Boot.getPaddleLeft().getHeight())) )){
			float randOuterAngle = (float) (Math.random() * ((1.5f - 0.4f) + 0.4f)); // random rebound angle for outer quarters of paddles
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			
			direction[0] = 1; // puts ball x in opposite direction
			if(lastY <= 0){ // puts ball y in same direction
				direction[1] = -randOuterAngle;
			}else{
				direction[1] = randOuterAngle;
			}
		}
		//// collision detection for right screen border and front middle half of right paddle
		if(x + 25 >= Display.getWidth() || (x+19 >= Boot.getPaddleRight().getX() && x+19 <= Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth() && y+18 > Boot.getPaddleRight().getY() + (Boot.getPaddleRight().getHeight() / 4) - 10  && y-17 < Boot.getPaddleRight().getY() + 10 + (Boot.getPaddleRight().getHeight() /4) * 3)){
			float randInnerAngle = (float) (Math.random() * ((0.7f - 0.1f) + 0.1f)); // random rebound angle for inner quarters of paddles
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			direction[0] = -1; // puts ball x in opposite direction
			if(lastY <= 0){ // puts ball y in same direction
				direction[1] = -randInnerAngle; 
				
			}else{
				direction[1] = randInnerAngle;
			}
			
		}
		
		//// collision detection for front outer quarters of right paddle
		if((x+19 >= Boot.getPaddleRight().getX() && x+19 <= Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth() && ((y+19 > Boot.getPaddleRight().getY() && y-21 < Boot.getPaddleRight().getY() - 10 + Boot.getPaddleRight().getHeight() / 4 ) || (y+19 > Boot.getPaddleRight().getY() + 10 + (Boot.getPaddleRight().getHeight() / 4) * 3 && y-21 < Boot.getPaddleRight().getY() + Boot.getPaddleRight().getHeight())))){
			float randOuterAngle = (float) (Math.random() * ((1.5f - 0.4f) + 0.4f)); // random rebound angle for outer quarters of paddles
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			direction[0] = -1; // puts ball x in opposite direction
			if(lastY <= 0){ // puts ball y in same direction
				direction[1] = -randOuterAngle; 
			}else{
				direction[1] = randOuterAngle;
			}
			
		}
		////
		/////////////////////
		
		if(y >= Display.getHeight() - 15 && direction[1] >= 0){ // reverses ball direction if moving past bottom of screen border, must be moving south
			direction[1] = -lastY;
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
		}
		if(y <= 15  && direction[1] <= 0){ // reverses ball direction if moving past top of screen border, must be moving north
			direction[1] = -lastY;
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
		}
		
		// collision detection for top and bottom of left paddle, a ball hitting top of paddle approaches with a +1 direction,  a ball hitting top of paddle approaches with a -1 direction
		if(y >= Boot.getPaddleLeft().getY()-28 && y <= Boot.getPaddleLeft().getY()-5 && direction[1] > 0 && x <= (Boot.getPaddleLeft().getX() + Boot.getPaddleLeft().getWidth()) && x >= Boot.getPaddleLeft().getX()){
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			direction[1] = -1; // ball hit top of paddle, so reverse y direction
		}
		if(y <= (Boot.getPaddleLeft().getY()+28 + Boot.getPaddleLeft().getHeight()) && y >= (Boot.getPaddleLeft().getY()+5 + Boot.getPaddleLeft().getHeight()) && direction[1] < 0  && x <= (Boot.getPaddleLeft().getX() + Boot.getPaddleLeft().getWidth()) && x >= Boot.getPaddleLeft().getX()){
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			direction[1] = 1; // ball hit bottom of paddle, so reverse y direction
		}
		/////
		// collision detection for top and bottom of right paddle
		if(y >= Boot.getPaddleRight().getY()-28 && y <= Boot.getPaddleRight().getY()-5 && direction[1] > 0 && x <= (Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth()) && x >= Boot.getPaddleRight().getX()){
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			direction[1] = -1;
		}
		if(y <= (Boot.getPaddleRight().getY()+28 + Boot.getPaddleRight().getHeight()) && y >= (Boot.getPaddleRight().getY()+5 + Boot.getPaddleRight().getHeight()) && direction[1] < 0  && x <= (Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth()) && x >= Boot.getPaddleRight().getX()){
			int rand = 1 + (int)(Math.random() * ((2 - 1) + 1)); // for choosing ball sound
			Boot.getSfx().get(sound[rand-1]).play();
			direction[1] = 1;
		}
		//////
		
		// below checks scores
		if(x <= Boot.getPaddleLeft().getX()){ // paddle right scores a point
			long currentTime = System.currentTimeMillis();
			if(System.currentTimeMillis() == currentTime){ // hack used to make sure block is called only once per score
				Boot.getScores().addPoint("PaddleRight");
				die();
			}
		}
		if(x >= Boot.getPaddleRight().getX() + Boot.getPaddleRight().getWidth()){  // paddle left scores a point
			long currentTime = System.currentTimeMillis();
			if(System.currentTimeMillis() == currentTime){
				Boot.getScores().addPoint("PaddleLeft");
				die();
			}
		}
	}
	
	public void respawn(){
		int randSignX = 1 + (int)(Math.random() * ((2 - 1) + 1)) == 1? 1:-1; // if random number between 1 and 2 = 1, then sign is positive, else its negative
		int randSignY = 1 + (int)(Math.random() * ((2 - 1) + 1)) == 1? 1:-1;
		int randomY = 300 + (int)(Math.random() * ((900 - 300) + 1)); // random Y position
		
		x = Display.getWidth()/2;
		y = randomY;
		direction[0] = randSignX;
		direction[1] = randSignY;
		//System.out.println("randSignX: " + randSignX + ",randSignY: " + randSignY + ", randomY: " + randomY);
		alive = true;
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
