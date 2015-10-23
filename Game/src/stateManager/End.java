package stateManager;

import data.Boot;
import data.Scoreboard;
import data.SoundPlayer;
import data.Splashscreen;

public class End implements GameState{

	public void updateState() {
		Scoreboard.update();
		Splashscreen.update();
		Splashscreen.detectClick();
		checkGameReset();
	}

	public void drawState() {
		Boot.getPaddleLeft().draw();
		Boot.getPaddleRight().draw();
	}

	public void checkGameReset(){
		if(Splashscreen.getPlay() == true){
			SoundPlayer.getSounds().get("slot_machine").play();
			Scoreboard.reset();
			Boot.getPaddleLeft().resetPaddle(Boot.getPlX(), Boot.getPlY(), Boot.getPaddleWidth(), Boot.getPaddleHeight());
			Boot.getPaddleRight().resetPaddle(Boot.getPrX(), Boot.getPrY(), Boot.getPaddleWidth(), Boot.getPaddleHeight());
			Boot.setGameState(1);
			Splashscreen.setPlay(false);
		}
	}
}
