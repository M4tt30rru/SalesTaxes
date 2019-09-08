package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client {

	private String input;
	private IParser parser;
	private List<String> inputList;
	private static Client client;
	
	// C&P
	
//	private String server; // should be final?
//	private int portNumber; // should be final?
//	private boolean wasSomethingDone;
	
	public Client(IParser parser) {
		this.parser = parser;
	}

    public static void main(String[] args) {
    	
    	client = new Client(new Parser(new SalesTaxesCalculator(), new ItemFactory()));
    	
    	run(args);
        System.exit(0);
    	
//      if (args.length < 2 && args.length > 1) {
//         System.out.println("port must be supplied");
//         System.out.println("Usage:ntjava App server port");
//         System.exit(1);
//      } else if (args.length > 2) {
//         System.out.println("too many arguments");
//         System.out.println("Usage:ntjava App server port");
//         System.exit(1);
//      } else {
//    	  run(args);
//          System.exit(0);
//      }

//      String server = args[0];
//      String port = args[1];
//      int portNumber = -1;
      
//      try {
//         portNumber = Integer.parseInt(port);
//      } catch (NumberFormatException e) {
//         System.out.println("Usage:ntjava App server port");
//         System.exit(1);
//      }

      // new App(server, portNumber).execute();
    }	
    
    private static List<String> inputCache = new ArrayList<String>();
	private static Scanner scanner;
    
    public static void run(String[] args)
    {
      
      prettyPrint("SalesTaxes");
      
      input(); 
      
      process();
      
      System.exit(0);
      
      // get their input as a String
//      String username = scanner.next();
      
//      boolean enter = false;

      // prompt for their age
//      if(enter) {
//	      System.out.print("Enter your age: ");
//	
//	      // get the age as an int
//	      
//	      int age = 0;
//	      try {
//	         age = scanner.nextInt();
//	      } catch (InputMismatchException e) {
//	    	  System.err.println("ERROR! Input Mismatch for field age");
//	    	  System.exit(1);
//	      }
//	
//	      System.out.println(String.format("%s, your age is %d", username, age));
//      }

    }

	private static void input() {

	 scanner = new Scanner(System.in);
     String answer, start;
     
//	 System.out.print("Start adding items?[Yn]: ");
//     start = scanner.next();
//     answer = start;
//	 
//	 while(start.toLowerCase().equals("y") && answer.toLowerCase().equals("y")) {
//	     System.out.print("Please insert the item: ");
//	  	 String newItem = scanner.nextLine();
//		 cart.add(newItem);
//		 scanner.nextLine();
//         System.out.println("Add another product?[Yn]: ");
//         answer = scanner.next();
//	 }
//	 
//	 System.out.println(cart.size());
//	 System.out.println(cart.get(0));
	 
     System.out.print("Please insert the first item: ");
	  	do {
	  		inputCache.add(scanner.nextLine());
	  		System.out.print("Press Enter to send the entry");
	        scanner.nextLine();
	  		System.out.print("Add another product?[Yn]: ");
	  		answer = scanner.nextLine();
	  		if(answer.toLowerCase().equals("y")) {
	  			System.out.print("Please insert the item: ");
//	  			cart.add(scanner.nextLine());
	  		}
	  	} while(answer.toLowerCase().equals("y"));
	  	
	  	printInput();
	}

	private static void printInput() {
		prettyPrint("INPUT");
		String str = "";
		for(String s: inputCache) {
			str += s + "\n";
		}
		System.out.println(str);
	}

	private static void prettyPrint(String heading) {
		String str = heading.toUpperCase();
		
		printMargin(3);
		printHeading(str,"-");
        printMargin(3);
	}

	private static void printMargin(int space) {
		for(int i = space; i <= space; i++)
			System.out.println("\n");
	}

	private static void printHeading(String str, String sign) {
		for(int i = 0; i < str.length(); i++)
			System.out.print(sign);
		System.out.println("\n" + str);
		for(int i = 0; i < str.length(); i++)
			System.out.print(sign);
	}

	private static void process() {
		String output = "";
		for(String s: inputCache) {
			output += s + "\n";
		}
		output = output.substring(0, output.length()-1); 
		// System.out.println(output);
		// client.parser.parse(string);
		printOutput(client.parser.parse(output));
	}
	
	private static void printOutput() {
		System.out.println("\n\nThanks, for your input. We are processing...");
		prettyPrint("OUTPUT");
		for(String s: inputCache) {
			System.out.println(s);
		}
  	  	//System.exit(0);
	}

	private static void printOutput(String output) {
		System.out.println("\n\nThanks, for your input. We are processing...");
		prettyPrint("OUTPUT");
		// for(String s: cart) {
			System.out.println(output);
		// }
  	  	//System.exit(0);
	}

	public void input(String input) {
		this.input = input;
		if(inputList == null)
			inputList = new ArrayList<String>();
		inputList.add(input);
		// parser.parse(input);
	}

	public String output() {
		String temp = String.join("\n", this.inputList);
		return parser.parse(temp);// "1 book: 12.49\nSales Taxes: 0\nTotal: 29.83";
	}

}
