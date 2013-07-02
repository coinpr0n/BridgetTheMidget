/**
 * Main Program
 * 
 * Initialize and start a new Bot (Bridget)
 * 
 */
package core;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	/**
	 * Method main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* TODO: Read args from the commandline OR use a config file. */
		
		Bridget bridget = new Bridget();
		bridget.start();
		
		Scanner inputLine = new Scanner(System.in);
		boolean loop = true;
		while (loop == true) {

			try {
				
				String line = inputLine.nextLine();
				System.out.println(line);
				bridget.sendMessage("#cvchat", line);
				
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}

		}
		inputLine.close();
		
	}
	
}
