//
// HelloWorld.java
//	Bridget reacts to being the center of attention
package extensions;

import core.Bot;

public class AntiFlood {

	/*private static String lastMessage1 = "lastMessage1";
	private static String lastMessage2 = "lastMessage2";
	private static String lastMessage3 = "lastMessage3";*/
	private static int intTime = (int) System.currentTimeMillis();
	private static String lastSender = "initSender";
	/*private static String lastTime = "initTime";
	private static String lastSender = "initSender";*/
	
	public static boolean test(String s) {
		return s.matches("(.*)");
	}
	
	public static void run(Bot bot, String res,  String sender, String s) {
		//intTime = (int) System.currentTimeMillis();
		
		int now = (int) System.currentTimeMillis();
		int threshold = now - intTime;
		intTime = now;
		if (threshold < 1500) {
			System.out.println("WARNING! Flood ...");
			if (sender.equals(lastSender) && !lastSender.equalsIgnoreCase("SatoshiVICE")) {
				bot.kick(res, sender, sender + "Welcome to my demon-haunted world! :)~~");
			}
		} else {
			System.out.println("Flood Threshold: " + String.format("%d", threshold));
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
