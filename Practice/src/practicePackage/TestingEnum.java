package practicePackage;

public class TestingEnum {

	Rating rate;
	Grade stage;
	int val;
	public static void main(String[] args) {
		TestingEnum excellent = new TestingEnum(Rating.EXCELLENT), first = new TestingEnum(Grade.FIRST), house = new TestingEnum(2);
		excellent.printRating();
		first.printGrade();
		house.printType();
	}
	
	public enum Grade{ // this enum is created inside class TestingEnum while the Rating enum is created in its own file, inside same package.
		FIRST,SECOND,THIRD
	}
	
	// overloading constructor
	public TestingEnum(Rating rate){
		this.rate = rate;
	}
	
	public TestingEnum(Grade stage){
		this.stage = stage;
	}
	
	public TestingEnum(int type){
		this.val= type;
	}
	
	public void printRating(){
		switch (rate) {
		case POOR:
		case NOT_BAD:
			System.out.println("Not Bad");
			break;
		case OK:
			System.out.println("Ok");
			break;
		case GOOD:
		case EXCELLENT:
			System.out.println("Excellent");
			break;
		default:
			System.out.println("Triggered default");
		}
	}
	
	public void printGrade(){
		switch (stage) {
		case FIRST:
			System.out.println("First");
			break;
		case SECOND:
			System.out.println("Second");
			break;
		case THIRD:
			System.out.println("Third");
			break;
		}
	}
	
	public void printType() {
		switch (val) {
		case 0:
			System.out.println("Flat");
			break;
		case 1:
			System.out.println("Apartment");
			break;
		case 2:
			System.out.println("House");
			break;
		default:
			System.out.println("Case not defined: default triggered");
		}
	}

}
