package project;

import java.io.IOException;
import java.util.Scanner;

public class TEST {

	
	public static int inputintegercheck(Scanner input, String command) {
		int output = 0;
		while(1==1) {
			try {
				System.out.println(command);
	            output = Integer.parseInt(input.next());
	            System.out.println("User Input: "+output);
	            break;
	        } catch (Exception e) {
	            System.out.println("Invalid Entry, Try Again.");
	        }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static Double inputdoublecheck(Scanner input, String command) {
		Double output = 0.0;
		while(1==1) {
			try {
				System.out.println(command);
	            output = Double.parseDouble(input.next());
	            System.out.println("User Input: "+output);
	            break;
	        } catch (Exception e) {
	            System.out.println("Invalid Entry, Try Again.");
	        }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static String inputyesnocheck(Scanner input, String command) {
		String output = null;
		while(1==1) {
			System.out.println(command);
            output = input.next();
            System.out.println("User Input: "+output);
            if(output.equalsIgnoreCase("yes") || output.equalsIgnoreCase("no")) {
            	break;
            }else {
            	System.out.println("Invalid Entry, Try Again.");
            }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static Boolean inputbooleancheck(Scanner input, String command) {
		Boolean output = null;
		while(1==1) {
			try {
				System.out.println(command);
	            output = Boolean.parseBoolean(input.next());
	            System.out.println("User Input: "+output);
	            break;
	        } catch (Exception e) {
	            System.out.println("Invalid Entry, Try Again.");
	        }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int intoutput = inputintegercheck(input, "Input an Integer");
		Double doubleoutput = inputdoublecheck(input, "Input a Double");
		String yesnooutput = inputyesnocheck(input, "Input a yes or no");
		Boolean booleaninput = inputbooleancheck(input, "Input true or false");
		
		
		
	}

}
