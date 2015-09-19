
public class Test2 {
	/*
	testing push and pull to/from eclipse
	*/

	public Test2(){
		System.out.println("Between 1 and 3 'e's ?: " + stringE("hello"));
	}
	
	public static void main(String[] args) {
		new Test2();
	}

	public boolean stringE(String str) {
  int count = str.length() - str.replace("e", "").length();
  
  if(count >= 1 && count <= 3){return true;}
  return false;
  }


 }
	

