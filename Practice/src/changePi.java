
public class changePi {

	/** Given a string, compute recursively (no loops) 
	 * a new string where all appearances of "pi" have 
	 * been replaced by "3.14".  Exercise from codingbat.com
	 * **/
	
	public static void main(String[] args) {
		System.out.println(newPiString("pippi")); // result = "3.14p3.14"
	}
	
	public static String newPiString(String str){
		if(str.length()<2){
			return str;
		}else{
			if(str.substring(0,2).equals("pi")){
				return "3.14" + newPiString(str.substring(2));
			}
			return str.substring(0,1) + newPiString(str.substring(1));
		}
	}
}
