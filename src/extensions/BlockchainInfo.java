package extensions;

import core.Bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BlockchainInfo {

	/**
	 * Flag when URL's title has been found so as to stop reading more HTML.
	 */
	private static boolean titleFound = false;

	/**
	 * Test messages for URLs (has "http://" or "https://")
	 *
	 * @param message
	 * @return message contains a URL (true or false)
	 */
	public static boolean test(String message) {
		return message.matches("(.*)http(s)?://(.*)");
	}

	/**
	 * Grabs the titles from URLs in a message and sends them to the channel
	 *
	 * @param bot
	 * @param channel
	 * @param sender
	 * @param message
	 */
	public static void run(Bot bot, String channel, String sender, String message) {

		// split message into (space-delimited) words
		String[] words = message.split("\\s");

		// every word
		for (String word : words) {

			// check that it starts with "http://" or "https://"
			if (word.matches("^http(s)?://(.*)")) {

				// exclude URLs are images
				if(!word.matches("(.*).png") && !word.matches("(.*).jpg") && !word.matches("(.*).gif")) {

					try {
						URL website = new URL(word);
						BufferedReader in = null;
						String inputLine;

						try {
							in = new BufferedReader(new InputStreamReader(website.openStream()));
						} catch (IOException e) {/*e*/}

						try {
							while ((inputLine = in.readLine()) != null && titleFound == false) {

								if (inputLine.matches("(.*)/title>(.*)")) {
									//System.out.println(inputLine.split(">")[1].split("<")[0]);
									try {
										bot.sendMessage(channel, "[ " + inputLine.split(">")[1].split("<")[0] + " ]");
										titleFound  = true;
									} catch (ArrayIndexOutOfBoundsException e) {/*e*/}
								}

							}
						} catch (IOException e) {/*e*/}

						try {
							in.close();
						} catch (IOException e) {/*e*/}
					} catch (MalformedURLException e) {/*e*/}

				}

			}

		}

	}

}
