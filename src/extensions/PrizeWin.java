/**
 * PrizeWin
 * 
 * Random prizes for some of the messages sent to a channel 
 * 
 */
package extensions;

import core.Bot;
import core.Config;
import core.Logger;

import org.jibble.pircbot.Colors;

import users.Balance;
import users.Users;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PrizeWin {
	
	/**
	 * Keep track of time between prize wins (threshold)
	 */
	private static int intTime = 0;
	
	/**
	 * Tests for any message
	 * 
	 * @param message
	 * @return always returns true if there is a message
	 */
	public static boolean test(String message) {
		return message.matches("(.*)");
	}
	
	/**
	 * Random prize wins to active senders in the channel.
	 * 
	 * @param bot
	 * @param channel
	 * @param sender
	 * @param message
	 */
	public static void run(Bot bot, String channel, String sender, String message) {
		
		double prizeValue = 0.00000000;

		if (!sender.matches("(.*)[bB][oO][tT](.*)|(.*)Satoshi[DV]ICE(.*)") && !message.matches("^[.!](.*)")) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			int now = (int) System.currentTimeMillis();
			int rnd = (int) (Math.random() * 1000) + 1;
			int threshold = now - intTime;
			intTime = now;
			
			/* Regular Prize Win */
			if (rnd > 425 && rnd < 575) {
				
				// Tracks winnings (threshold)
				if (threshold < 15000) {
					System.out.println("BELOW Threshold: " + String.format("%d", threshold));
				} else {
					System.out.println("ABOVE Threshold: " + String.format("%d", threshold));
				}
				
				prizeValue = 0.0000234;
				
				//ADDED!
				try {
					Users user = mapper.readValue(new File(Config.getUserFile(sender)), Users.class);
					Balance balance = user.getBalance();
					String delayed = balance.getDelayed();
					double delayedValue = Double.parseDouble(delayed);
					delayed = String.format("%.8f", delayedValue + prizeValue);
					balance.setDelayed(delayed);
					mapper.writeValue(new File(Config.getUserFile(sender)), user);
				} catch (JsonParseException ex) {
					ex.printStackTrace();
				} catch (JsonMappingException ex) {
					ex.printStackTrace();
				} catch (FileNotFoundException ex) {
					System.out.println("FILE NOT FOUND EXCEPTION! Try newUser()...");
					newUser(sender, prizeValue);
					String[] items = {"rum*","joint*"};
					bot.sendRandomMessage(items, channel, "*passes " + sender + " the");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				Logger.info(Logger.GREEN + sender + " PRIZE WIN!");
				bot.sendMessage(channel, Colors.BOLD + sender + Colors.NORMAL + Colors.GREEN + " (+ 0.0234 mBTC)");

			/* Grand Prize Win */
			} else if (rnd > 900) {
				
				prizeValue = 0.0001;
				
				//ADDED!
				try {
					Users user = mapper.readValue(new File(Config.getUserFile(sender)), Users.class);
					Balance balance = user.getBalance();
					String delayed = balance.getDelayed();
					double delayedValue = Double.parseDouble(delayed);
					delayed = String.format("%.8f", delayedValue + prizeValue);
					balance.setDelayed(delayed);
					mapper.writeValue(new File(Config.getUserFile(sender)), user);
				} catch (JsonParseException ex) {
					ex.printStackTrace();
				} catch (JsonMappingException ex) {
					ex.printStackTrace();
				} catch (FileNotFoundException ex) {
					System.out.println("FILE NOT FOUND! (" + Config.getUserFile(sender) + ")");
					newUser(sender, prizeValue);
					String[] items = {"rum*","joint*"};
					bot.sendRandomMessage(items, channel, "*passes " + sender + " the");
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				Logger.info(Logger.BOLD_GREEN + sender + " GRAND PRIZE!!");
				bot.sendMessage(channel, Colors.BOLD + sender + Colors.BOLD + Colors.GREEN + " (+ 0.0001 BTC" + ")" + Colors.NORMAL);
				
			}
		
		}
		
	}

	/**
	 * Create a new PrizeWin user as sender and an initial prize value.
	 * 
	 * @param sender
	 * @param prizeValue
	 */
	private static void newUser(String sender, double prizeValue) {
		Logger.info("Creating a new user \"" + sender + "\" ...");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Users user = mapper.readValue(new File(Config.getUserTemplate()), Users.class);
			Balance balance = user.getBalance();
			user.setUsername(sender);
			user.setAddress("(change with '!setbitcoin <ADDRESS>')");
			balance.setDelayed(String.format("%.8f", prizeValue));
			balance.setPending("0.00000000");
			balance.setSent("0.00000000");
			mapper.writeValue(new File(Config.getUserFile(sender)), user);
		} catch (JsonParseException ex) {
			ex.printStackTrace();
		} catch (JsonMappingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			Logger.warn("(IOException) - Method: Prize.newUser()");
		}
		
	}

}
