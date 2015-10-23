package stateManager;

import data.Boot;
import data.Clock;
import data.Scoreboard;

public class Play implements GameState{

	public void updateState() {
		Boot.getPaddleLeft().update();
		Boot.getPaddleRight().updateAI(Boot.getPong(), Clock.getDelta());
		updatePong();
		Scoreboard.update();
	}

	public void drawState() {
		Boot.getPaddleLeft().draw();
		Boot.getPaddleRight().draw();
	}
	
	public void updatePong(){
		if (Boot.getPong().isAlive()) {
			Boot.getPong().update();
			Boot.getPong().draw();
			timedMissAdjustment();
		}else{
			Boot.getPong().update();
			timedRespawn();
		}
	}
	
	public void timedMissAdjustment(){
		if (Thread.activeCount() <= 10) { // this allows the main thread plus max of one timer thread. need to account for sound effect threads also
			Thread timedMissAdjustment = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2000); // wait 2 seconds
						Boot.getPaddleRight().setLow(Boot.getPaddleRight().getLow() + 15);
						Boot.getPaddleRight().setMax(Boot.getPaddleRight().getMax() + 30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			timedMissAdjustment.start();
		}
	}
	
	public void timedRespawn(){
		if (Thread.activeCount() <= 10) { // this allows the main thread plus max of one timer thread																											
			Thread timedRespawn = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1500); // wait 1 second then respawn ball
						Boot.getPong().respawn();
						//testPong.respawn();
						Boot.getPaddleRight().setLow(0);
						Boot.getPaddleRight().setMax(0);
						Boot.getPaddleRight().setOffset(0); // resets the ai's offset
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			timedRespawn.start();
		}
	}

}
