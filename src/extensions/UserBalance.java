//
// URL
//	Get the title of a URL
package extensions;

import core.Bot;

public class UserBalance {
	
	public static boolean test(String s) {
		return s.matches("^[.!][wW]ithdraw");
	}

	public static void run(Bot bot, String res, String sender, String message) {
		//TODO: net connection and parse title...
		bot.sendMessage(res, "Uh.. Yeah, right!");
		//bot.sendMessage(res, "I am just the right size to reach your tool, Mr. Handyman!");
	}
	
}
