package data;


public class Scoreboard {
	private static int pointsPerGame,totalRounds,currentRound,leftPaddlePoints,rightPaddlePoints,leftPaddleRounds,rightPaddleRounds;
	private static boolean gameOver;
	
	public Scoreboard(int pointsPerGame, int totalRounds) {
		this.pointsPerGame = pointsPerGame;
		this.totalRounds = totalRounds;
		
	}

	public void update(){
		
	}
	
	public static void newRound(){
		if(currentRound == totalRounds){
			gameOver = true;
		}else{
			leftPaddlePoints = 0;
			rightPaddlePoints = 0;
			currentRound++;
		}	
	}

	public static void addPoint(String paddle) {
		if(paddle.equals("PaddleLeft")){
			leftPaddlePoints++;
			if(leftPaddlePoints >= pointsPerGame){
				leftPaddleRounds++;
				newRound();
			}
		}else{
			rightPaddlePoints++;
			if(rightPaddlePoints >= pointsPerGame){
				rightPaddleRounds++;
				newRound();
			}
		}
		System.out.println("Human: Points = " + leftPaddlePoints + ",Rounds = " + leftPaddleRounds);
		System.out.println("AI: Points = " + rightPaddlePoints + ",Rounds = " + rightPaddleRounds);
	}
	
}
