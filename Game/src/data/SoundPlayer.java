package data;


import java.util.HashMap;

import javax.sound.sampled.*;


public class SoundPlayer {
	private Clip clip;
	private static HashMap<String,SoundPlayer> sounds;
	
	public SoundPlayer(String s) {	
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),16,baseFormat.getChannels(),baseFormat.getChannels() * 2,baseFormat.getSampleRate(),false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void loadSounds(){
		sounds = new HashMap<String,SoundPlayer>();
		sounds.put("ball1", new SoundPlayer("/res/ball1.mp3"));
		sounds.put("ball2", new SoundPlayer("/res/ball2.mp3"));
		sounds.put("clapping", new SoundPlayer("/res/clapping.mp3"));
		sounds.put("booing", new SoundPlayer("/res/booing.mp3"));
		sounds.put("slot_machine", new SoundPlayer("/res/slot_machine.mp3"));
		sounds.put("score", new SoundPlayer("/res/score.mp3"));
		sounds.put("round", new SoundPlayer("/res/round.mp3"));
	}
	
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
	public static HashMap<String,SoundPlayer> getSounds(){
		return sounds;
	}
}
