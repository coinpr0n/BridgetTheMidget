//
//	RandomPrize
//	Returns the current date and time
package extensions;

import core.Bot;

import org.jibble.pircbot.Colors;

import java.util.HashMap;

public class RandomPrize {
	
	private static HashMap<String, Double> users = new HashMap<String, Double>();
	
	private static int intTime = 0;
	

	public static boolean test(String s) {
		return s.matches("(.*)");
	}
	
	public static void run(Bot bot, String res, String sender, String s) {
		
		try {
			
			double balance = users.get(sender);
			
			if (!sender.equalsIgnoreCase("SatoshiVICE") && !s.matches("^[.!](.*)")) {
				
				int now = (int) System.currentTimeMillis();
				int threshold = now - intTime;
				intTime = now;
				
				int rnd = (int) (Math.random() * 1000) + 1;
				
				if (rnd > 425 && rnd < 575) {
					if (threshold < 15000) {
						System.out.println("BELOW Threshold: " + String.format("%d", threshold));
					} else {
						System.out.println("ABOVE Threshold: " + String.format("%d", threshold));
					}
					System.out.println(sender + " - balance: " + String.format("%.8f", balance) + " BTC");
					users.put(sender, balance + 0.0000234);
					bot.sendMessage(res, Colors.BOLD + sender + Colors.NORMAL + " (+ 0.0234 mBTC)");
				
				} else if (rnd > 900) {
					System.out.println(sender + " - balance: " + String.format("%.8f", balance) + " BTC");
					users.put(sender, balance + 0.0001);
					bot.sendMessage(res, Colors.BOLD + sender + Colors.NORMAL + " (+ " + Colors.BOLD + "0.0001 BTC" + Colors.NORMAL + ")");
				}
			
			}
		
		} catch (NullPointerException e) {
			//System.out.println(e);
			users.put(sender, 0.00000000);
			System.out.println("New User: " + sender);
		}
		
		if (s.matches("^[.!][bB]alance")) {
			try {
				bot.sendMessage(res, sender + " - balance: " + String.format("%.8f", users.get(sender)) + " BTC");
			} catch (NullPointerException e) {
				bot.sendMessage(res, sender + " - balance: 0.00000000 BTC");
			}
		}
		
		
	}

}
