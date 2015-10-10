import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileWriteTest {

	// the first block i test writing to a file
	// second block i read from that file
	// finally i take input from console
	public static void main(String[] args) {
		File file = new File("test.txt");
		
		// output to file
		try{ // the PrintWriter class throws an exception, so a try/catch is needed
			PrintWriter output = new PrintWriter(file); 
			output.println("Testing");
			for(int i=0;i<10;i++){
				output.println("line " + (i+1) + " test");
			}
			output.println(42);
			output.close();
		}
		catch(IOException e){
			System.out.println("Error opening file.");
			
		}
		
		
		//input from file
		
		try{
			Scanner in = new Scanner(file);
			
			while(in.hasNext()){
				System.out.println(in.nextLine());
			}
			
			in.close();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		

		// input from console 
		
		Scanner in = new Scanner(System.in);
			
		System.out.println("Type your name: ");
		String name = in.nextLine();
			
		System.out.println("Your name is: " + name);

		
		
	}

}
