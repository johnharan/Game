
public class Test {
	/*
	exercise from codingbat.com
 	Given 2 positive int values, return the larger value that is in the range 10..20 inclusive, 
 	or return 0 if neither is in that range.
	*/

	public Test(){
		long startTime = System.nanoTime();
		
		System.out.println("Result A: " + max1020(15, 9));
		
		long endTime = System.nanoTime();
		
		long durationA = endTime - startTime;
		
		System.out.println("Time A = " + durationA);
		
		
		long startTime2 = System.nanoTime();
		
		System.out.println("Result B: " + max1020B(15, 9));
		
		long endTime2 = System.nanoTime();
		
		long durationB = endTime2 - startTime2;
		
		System.out.println("Time B = " + durationB);
		
		System.out.println(durationA > durationB ? ("Test B is " + ((float)durationA / (float)durationB) + " times faster than Test A") : ("Test A is " + ((float)durationB / (float)durationA)  + " times faster than Test B"));
		
		
	}
	
	public static void main(String[] args) {
		new Test();
	}

	public int max1020(int a, int b) {
		if (a >= 10 && a <= 20) {
			if (b >= 10 && b <= 20) {
				return (a > b) ? a : b;
			}
			return a;
		} else if (b >= 10 && b <= 20) {
			return b;
		}
		return 0;
	}
	
	public int max1020B(int a, int b) {
		  // First make it so the bigger value is in a
		  if (b > a) {
		    int temp = a;
		    a = b;
		    b = temp;
		  }
		  
		  
		  
		  // Knowing a is bigger, just check a first
		  if (a >= 10 && a <= 20) return a;
		  if (b >= 10 && b <= 20) return b;
		  return 0;
		}

 }
	

