/**
 * SubmitLink
 * 
 * Allows users to submit a link that will be stored in memory and maybe
 * sent to the channel later (if it's chosen).
 * 
 */
package extensions;

import core.Bot;

public class SubmitLink {

	private static int MAX_LINKS = 10;
  private static String[] links = new String[MAX_LINKS];
	private static int index = 0;
	private static boolean init = false;
	
	/**
	 * Tests message for commands "!submit" and "!view"
	 * 
	 * @param message
	 * @return message is a SubmitLinks command (true or false)
	 */
	public static boolean test(String message) {
		return message.matches("^[.!][sS]ubmit(.*)|^[.!][vV]iew(.*)");
	}
	
	/**
	 * Responds with a random "Cabin Crew" member to the channel
	 * 
	 * @param bot
	 * @param channel
	 * @param sender
	 * @param message
	 */
	public static void run(Bot bot, String channel, String sender, String message) {
		
		//initialize array on first run
		if (init == false) {
			for (int i = 0; i < 10; i++) links[i] = "*feed me*";
			init = true;
		}
		
		// command "!submit"
		if (message.matches("^[.!][sS]ubmit(.*)")) {
			String[] words = message.split("\\s");
			for (String word : words) {
				if (word.matches("^http(s)?://(.*)")) {
					links[index] = word;
					index++;
					if (index == MAX_LINKS) index = 0;
				}
			}
			
		// command "!view"
		} else if (message.matches("^[.!][vV]iew[aA]ll(.*)")) {
			for (int i = 0; i < 9; i++) bot.sendMessage(channel, "> " + links[i]);
		} else if (message.matches("^[.!][vV]iew(.*)")) {
					bot.sendRandomMessage(links, channel, "Link:");
		}

	}

}
