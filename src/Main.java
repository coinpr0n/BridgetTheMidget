/**
 * Main Program
 *
 * Initialize and start a new Bot (Bridget)
 *
 */

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Main {

	/**
	 * Method main
	 *
	 * @param args
	 */
	public static void main(String[] args) {/* TODO: get args | use config file */

		Bridget bridget = new Bridget();
		bridget.start();

		//infinite loop a scanner for user input..
		Scanner inputLine = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			try {
				String line = inputLine.nextLine();
				System.out.println(line);
				bridget.sendMessage("#cvchat", line);/* TODO: get channel from config */
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		inputLine.close();

	}

}
