package stateManager;

import data.Boot;

public class End implements GameState{

	public void updateState() {
		Boot.getScores().update();
		Boot.getSplashscreen().update();
		Boot.getSplashscreen().detectClick();
		checkGameReset();
	}

	public void drawState() {
		Boot.getPaddleLeft().draw();
		Boot.getPaddleRight().draw();
	}

	public void checkGameReset(){
		if(Boot.getSplashscreen().getPlay() == true){
			Boot.getSfx().get("slot_machine").play();
			Boot.getScores().reset();
			Boot.getPaddleLeft().resetPaddle(Boot.getPlX(), Boot.getPlY(), Boot.getPaddleWidth(), Boot.getPaddleHeight());
			Boot.getPaddleRight().resetPaddle(Boot.getPrX(), Boot.getPrY(), Boot.getPaddleWidth(), Boot.getPaddleHeight());
			Boot.setGameState(1);
			Boot.getSplashscreen().setPlay(false);
		}
	}
}
