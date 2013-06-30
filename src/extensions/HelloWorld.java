//
// HelloWorld.java
//	Bridget reacts to being the center of attention
package extensions;

import core.Bot;

public class HelloWorld {
	
	private static String[] quotes = {
		"I am just the right size to reach your tool, Mr. Handyman!",
		"Yes, this is Bridget the Midget.",
		"Your girlfriend Amy fucked up!",
		"Welcome to my demon-haunted world! :)~~",
		"I just wonder how you can get inside of there."
	};

	
	public static boolean test(String s) {
		return s.matches("(.*)[bB]ridget(.*)!|(.*)[bB]ridget(.*)");
	}
	
	public static void run(Bot bot, String channel,  String sender, String message) {

		if(message.matches("^[bB]ridget(.*)!"))
			bot.sendMessage(channel, sender + " don't rape me! :( ..");

		else if(message.matches("(.*)[hH]ey(.*)|(.*)[hH]ello(.*)"))
			bot.sendMessage(channel, "hey " + sender + "!");

		else if(message.matches("(.*)[bB]ridget(.*)"))
			bot.sendMessage(channel, randomQuote());

	}
	
	public static String randomQuote() {
		//grab random quote from quotes array
		int i = (int) (Math.random() * quotes.length);
		return quotes[i];
	}

}
