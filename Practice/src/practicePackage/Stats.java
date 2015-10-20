package practicePackage;


public enum Stats {

	BOB(17, 60, "Ireland", 1.75f), TOM(30, 80, "Scotland", 2.05f); // constants

	private final int age, weight; // all variables in same order as constants above
	private final String location;
	private final float height;

	Stats(int age, int weight, String location, float height) { // constructor called automatically
		this.age = age;
		this.weight = weight;
		this.location = location;
		this.height = height;
	}

	public static void printStats() {
		System.out.println(); // print space
		for (Stats s : Stats.values()) {
			System.out.println("Name: " + s + ", Age: " + s.age + ", Weight: " + s.weight + ", Location: " + s.location + ", Height: " + s.height);

		}
	}

	public static void main(String[] args) {
		printStats();

	}

}
