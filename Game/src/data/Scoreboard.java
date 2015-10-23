package data;

import static helpers.Artist.drawNumbers;
import static helpers.Artist.loadTexture;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;


public class Scoreboard {
	private static int leftPaddlePoints,rightPaddlePoints,leftPaddleRounds,rightPaddleRounds;
	private static boolean gameOver = false;
	private static Texture numbers = loadTexture("res/numbers.png","PNG");
	private static int pointsPerGame,totalRounds;


	public static void update(){
		drawNumbers(numbers,leftPaddlePoints,Display.getWidth()/2 - 420,50);
		drawNumbers(numbers,leftPaddleRounds,Display.getWidth()/2 - 300,50);
		
		drawNumbers(numbers,rightPaddlePoints,Display.getWidth()/2 + 180,50);
		drawNumbers(numbers,rightPaddleRounds,Display.getWidth()/2 + 300,50);
	}
	
	public static void newRound(){
		if(leftPaddleRounds >= totalRounds && leftPaddleRounds > rightPaddleRounds || rightPaddleRounds >= totalRounds && rightPaddleRounds > leftPaddleRounds){
			if(leftPaddleRounds > rightPaddleRounds){
				SoundPlayer.getSounds().get("clapping").play();
			}else{
				SoundPlayer.getSounds().get("booing").play();
			}
			gameOver = true;
			Boot.setGameState(2);
		}
	}

	public static void addPoint(String paddle) {
		if(paddle.equals("PaddleLeft")){
			leftPaddlePoints++;
			if(leftPaddlePoints >= pointsPerGame){
				SoundPlayer.getSounds().get("round").play();
				leftPaddlePoints = 0;
				leftPaddleRounds++;
				newRound();
			}else{
				SoundPlayer.getSounds().get("score").play();
			}
		}else{
			rightPaddlePoints++;
			if(rightPaddlePoints >= pointsPerGame){
				SoundPlayer.getSounds().get("round").play();
				rightPaddlePoints = 0;
				rightPaddleRounds++;
				newRound();
			}else{
				SoundPlayer.getSounds().get("score").play();
			}
		}
		System.out.println("Human: Points = " + leftPaddlePoints + ",Rounds = " + leftPaddleRounds);
		System.out.println("AI: Points = " + rightPaddlePoints + ",Rounds = " + rightPaddleRounds);
		System.out.println();
	}
	
	public static boolean isGameOver() {
		return gameOver;
	}
	
	public static void reset(){
		leftPaddlePoints = 0;
		rightPaddlePoints = 0;
		leftPaddleRounds = 0;
		rightPaddleRounds = 0;
	}

	public static int getPointsPerGame() {
		return pointsPerGame;
	}

	public static void setPointsPerGame(int pointsPerGame) {
		Scoreboard.pointsPerGame = pointsPerGame;
	}

	public static int getTotalRounds() {
		return totalRounds;
	}

	public static void setTotalRounds(int totalRounds) {
		Scoreboard.totalRounds = totalRounds;
	}
}
