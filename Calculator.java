package project4;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Calculator reads a text file with infix expressions and prints results in a specified output file
 * Prints Undefined or Invalid message in text file if expression cannot be evaluated
 * @author Helen Chang
 * @version Nov 17, 2015 
 *
 */

public class Calculator {
	
	
	public static void main(String[] args) throws FileNotFoundException{
		Calculator calculator = new Calculator();
	
		//check arguments are ok
		calculator.checkFiles(args);
		
		//new scanner reading input file
		File file = new File(args[0]);
		Scanner inputFile = new Scanner(file);
		
		//Calculations start
		ArrayList<String> toPrint = new ArrayList<String>();
		//scans every line in text file
		while(inputFile.hasNext()){
			String line = inputFile.nextLine();
			try{
				toPrint.add(""+ExpressionTools.evaluatePostfix(ExpressionTools.infixToPostfix(line)));
			}
			//catches divide by 0 error
			catch (java.lang.ArithmeticException e){
				toPrint.add("UNDEF");
			}
			catch (PostFixException e){
				toPrint.add("INVALID");
			}
			catch (Exception e){
				toPrint.add("INVALID");
			}
		}
		
		for(String line:toPrint){
			System.out.println(line);
		}
		
		//creates output file and printWriter for output file
		File outputFile = new File(args[1]);
		
		try{
			PrintWriter outputWriter= new PrintWriter(outputFile);
			//Writes output in new file
			for(String line:toPrint){
				outputWriter.println(line);
			}
			outputWriter.close();
			
		}
		catch(FileNotFoundException e){
			System.err.println("cannot write in file");
			System.exit(0);
		}
		
		
		
		inputFile.close();
	}
	
	/**
	 * Checks user entered valid file path and output file name
	 * Terminates program if not
	 * @param args arguments ran from command line
	 */
	public void checkFiles(String[] args){
		//check arguments exist
		if (args.length <2){
			System.err.println("Error: missing name of the input file or output file");
			System.exit(0);
		}

		File inputFile = new File(args[0]);

		//check file can be read
		if (!inputFile.canRead()){
			System.err.printf("Error: file %s does not exist or cannot be read", inputFile.getName());
			System.exit(0);
		}

	}//checkfiles
}
