package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

	private IParser parser;
	private List<String> inputList;
	private static Client client;
	
	public Client(IParser parser) {
		this.parser = parser;
	}

    public static void main(String[] args) {
    	
    	client = new Client(new Parser(new SalesTaxesCalculator(), new ItemFactory()));
      	run(args);
        System.exit(0);
    }	
    
    private static List<String> inputCache = new ArrayList<String>();
	private static Scanner scanner;
    
    public static void run(String[] args) {
      
      prettyPrint("SalesTaxes");
      input(); 
      process();
      
    }

	private static void input() {

	 scanner = new Scanner(System.in);
     String answer;
	 
     System.out.print("Please insert the first item: ");
	  	do {
	  		inputCache.add(scanner.nextLine());
	  		System.out.print("Press Enter to send the entry");
	        scanner.nextLine();
	  		System.out.print("Add another product?[Yn]: ");
	  		answer = scanner.nextLine();
	  		if(answer.toLowerCase().equals("y")) {
	  			System.out.print("Please insert the item: ");
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
	
	private static void printOutput(String output) {
		System.out.println("\n\nThanks, for your input. We are processing...");
		prettyPrint("OUTPUT");
		System.out.println(output);
	}

	public void input(String input) {
		if(inputList == null)
			inputList = new ArrayList<String>();
		inputList.add(input);
	}

	public String output() {
		String temp = String.join("\n", this.inputList);
		return parser.parse(temp);
	}

}
