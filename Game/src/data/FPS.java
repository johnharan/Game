package data;

public class FPS {
	private static boolean first = true;
	private static long nextSecond;
	private static int framesInLastSecond, framesInCurrentSecond;
	
	public static void update() {
		if(first == true){
			first = false;
			nextSecond = System.currentTimeMillis() + 1000;
		}
		if(System.currentTimeMillis() >= nextSecond){
			nextSecond += 1000;
			framesInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;
		}
		framesInCurrentSecond++;
	}

	public static int getFPS() {
		return framesInLastSecond;
	}

	
}
