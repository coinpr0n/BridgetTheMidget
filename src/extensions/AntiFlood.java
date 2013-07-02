//
// HelloWorld.java
//	Bridget reacts to being the center of attention
package extensions;

import core.Bot;
import core.Logger;

public class AntiFlood {

	/*private static String lastMessage1 = "lastMessage1";
	private static String lastMessage2 = "lastMessage2";
	private static String lastMessage3 = "lastMessage3";*/
	private static int intTime = (int) System.currentTimeMillis() - 5000;
	private static String lastSender = "initSender";
	private static boolean warn1 = false;
	private static boolean warn2 = false;
	/*private static String lastTime = "initTime";
	private static String lastSender = "initSender";*/
	
	public static boolean test(String s) {
		return s.matches("(.*)");
	}
	
	public static void run(Bot bot, String res,  String sender, String s) {
		
		int now = (int) System.currentTimeMillis();
		int threshold = now - intTime;
		intTime = now;
		if (threshold < 1200) {
			Logger.warn("Flood WARNING!");
			//System.out.println(Colors._RED + "WARNING! Flood: " + String.format("%d", threshold) + Colors._NORMAL);
			if (warn1 == true && warn2 == true) {
				if (sender.equals(lastSender) && !lastSender.equalsIgnoreCase("SatoshiVICE")) {
					//bot.kick(res, sender, "Welcome to my demon-haunted world " + sender + "! :)~~");
					Logger.warn(">> KICKING " + sender);
					//System.out.println(Colors._RED + ">> KICKING " + sender + Colors._NORMAL);
				}
			} else {
				if (warn1 == true) warn2 = true;
				else warn1 = true;
			}
		} else {
			warn1 = false;
			warn2 = false;
			Logger.info("Flood: " + String.format("%d", threshold));
			//System.out.println(Colors._CYAN + "Flood: " + String.format("%d", threshold) + Colors._NORMAL);
		}
		lastSender = sender;
		
		//bot.log(sender + System.currentTimeMillis() + s);
		/*if (s.equals(lastMessage1) && s.equals(lastMessage2) && s.equals(lastMessage3)) {
			//kick
			System.out.println(sender + " THIRD STRIKE! YOU'RE OUT!!");
		} else {
			lastMessage3 = lastMessage2;
			lastMessage2 = lastMessage1;
			lastMessage1 = s;
		}*/
	}

}
