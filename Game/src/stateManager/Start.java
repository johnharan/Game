package stateManager;

import data.Boot;
import data.SoundPlayer;
import data.Splashscreen;

public class Start implements GameState{

	public void updateState() {
		Splashscreen.update();
		Splashscreen.detectClick();
		checkGameReset();
	}

	public void drawState() {
		// TODO Auto-generated method stub
	}

	public void checkGameReset(){
		if(Splashscreen.getPlay() == true){
			SoundPlayer.getSounds().get("slot_machine").play();
			Boot.setGameState(1);
			Splashscreen.setPlay(false);
		}
	}
}
