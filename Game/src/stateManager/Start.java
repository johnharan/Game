package stateManager;

import data.Boot;

public class Start implements GameState{

	public void updateState() {
		Boot.getSplashscreen().update();
		Boot.getSplashscreen().detectClick();
		checkGameReset();
	}

	public void drawState() {
		// TODO Auto-generated method stub
	}

	public void checkGameReset(){
		if(Boot.getSplashscreen().getPlay() == true){
			Boot.getSfx().get("slot_machine").play();
			Boot.setGameState(1);
			Boot.getSplashscreen().setPlay(false);
		}
	}
}
