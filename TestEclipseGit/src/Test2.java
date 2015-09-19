
public class Test2 {
	/*
	testing push and pull to/from eclipse
	testing push @ 12:07
	*/

	public Test2(){
		System.out.println("Between 1 and 3 'e's ?: " + stringE("hello"));
		
		System.out.println("Same last digit ?: " + lastDigit(23,1643));
		
		System.out.println("Last 3 chars to upper: " + endUp("hello"));
	}
	
	public static void main(String[] args) {
		new Test2();
	}

  	public boolean stringE(String str) {
  		int count = str.length() - str.replace("e", "").length();
  
  		if(count >= 1 && count <= 3){return true;}
  		return false;
  	}

  	public boolean lastDigit(int a, int b) {
  	  return (Math.abs(a % 10) == Math.abs(b % 10)) ? true:false;
  	}
  	
  	public String endUp(String str) { // turns last three characters of string to uppercase
  	  if(str.length() <= 3){return str.toUpperCase();}
  	  else{
  	     return str.substring(0,str.length()-3) + str.substring(str.length()-3).toUpperCase();
  	  }
  	}


 }
	

