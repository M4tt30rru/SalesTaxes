package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client {

	private String input;
	private IParser parser;
	private List<String> inputList;
	
	// C&P
	
	private String server; // should be final?
	private int portNumber; // should be final?
	private boolean wasSomethingDone;
	
	public Client(IParser parser) {
		this.parser = parser;
	}

    public static void main(String[] args) {
      if (args.length < 2 && args.length > 1) {
         System.out.println("port must be supplied");
         System.out.println("Usage:ntjava App server port");
         System.exit(1);
      } else if (args.length > 2) {
         System.out.println("too many arguments");
         System.out.println("Usage:ntjava App server port");
         System.exit(1);
      } else {
    	  run(args);
          System.exit(0);
      }

      String server = args[0];
      String port = args[1];
      int portNumber = -1;
      
      try {
         portNumber = Integer.parseInt(port);
      } catch (NumberFormatException e) {
         System.out.println("Usage:ntjava App server port");
         System.exit(1);
      }

      // new App(server, portNumber).execute();
      System.exit(0);
    }	
    
    private static List<String> cart = new ArrayList<String>();
    
    public static void run(String[] args)
    {
      // create a scanner so we can read the command-line input
      Scanner scanner = new Scanner(System.in);
      
      System.out.println("--------------------------");
      System.out.println("SalesTaxes".toUpperCase());
      System.out.println("--------------------------");
      
      //  prompt for the user's name
      System.out.print("Please insert the item: ");
      cart.add(scanner.next());
      scanner.nextLine();
      
      String answer;
      do {
          System.out.print("Add another product?[Yn]: ");
    	  answer = scanner.next();
    	  if(answer.toLowerCase().equals("y")) {
    			  System.out.print("Please insert the item: ");
    			  cart.add(scanner.next());
    	  }
      } while(answer.toLowerCase().equals("y")); 
      
      elaborate();
      
      // get their input as a String
      String username = scanner.next();
      
      boolean enter = false;

      // prompt for their age
      if(enter) {
	      System.out.print("Enter your age: ");
	
	      // get the age as an int
	      
	      int age = 0;
	      try {
	         age = scanner.nextInt();
	      } catch (InputMismatchException e) {
	    	  System.err.println("ERROR! Input Mismatch for field age");
	    	  System.exit(1);
	      }
	
	      System.out.println(String.format("%s, your age is %d", username, age));
      }

    }

	private static void elaborate() {
		System.out.println("Thanks, for your input. We are processing...");
		System.out.println("OUTPUT");
		for(String s: cart) {
			System.out.println(s);
		}
  	  	System.exit(0);
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
