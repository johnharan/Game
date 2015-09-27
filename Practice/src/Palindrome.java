import java.util.Scanner;


public class Palindrome {
   
	public static void main(String[] args) {
		String input;
		Scanner in = new Scanner(System.in);
		
		do{
			System.out.println("Enter a string: ");
			input = in.nextLine();
			System.out.println(isPalindrome(input));
			
		}while(!input.equals("end"));
		
		in.close();
	}

	
	public static String isPalindrome(String str){
		String reverse = "";
		for(int i=str.length()-1;i>=0;i--){
			reverse += str.charAt(i);
		}

		if(str.equals(reverse)){
			return str + " is a palindrome.";
		}		
		return str + " is not a palindrome.";
	}
}
