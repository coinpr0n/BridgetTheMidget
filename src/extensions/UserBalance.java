//
// URL
//	Get the title of a URL
package extensions;

import core.Bot;
import core.Config;
import core.Logger;

import java.io.File;
import java.io.IOException;

import org.jibble.pircbot.Colors;

import users.Balance;
import users.Users;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class UserBalance {
	
	public static boolean test(String s) {
		return s.matches("^[.!][wW]ithdraw(.*)|^[.!][bB]alance(.*)|^[.!][sS]etbitcoin(.*)");
	}

	public static void run(Bot bot, String channel, String sender, String message) {
		
		// "!balance" command
		if (message.matches("^[.!][bB]alance(.*)")) {
		
			try {
				ObjectMapper mapper = new ObjectMapper();
				
				String delayed = "*delayed*";
				String pending = "*pending*";
				String sent = "*sent*";
				
				try {
					Users user = mapper.readValue(new File(Config.getUserFile(sender)), Users.class);
					Balance balance = user.getBalance();
					delayed = balance.getDelayed();
					pending = balance.getPending();
					sent = balance.getSent();
				} catch (JsonParseException ex) {
					ex.printStackTrace();
				} catch (JsonMappingException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
					Logger.error("(IOException) Reading / Writing JSON");
				}
	
				bot.sendMessage(sender, "Delayed: " + delayed + " - Pending: " + pending + " - Sent: " + sent);
				
			} catch (NullPointerException e) {
				bot.sendMessage(sender, "Delayed: 0.00000000 BTC - Pending: 0.00000000 BTC - Sent: 0.00000000 BTC");
			}
		
		// "!withdraw" command
		} else if (message.matches("^[.!][wW]ithdraw(.*)")) {
		
			bot.sendMessage(sender, "\"I can't do that, " + sender + ".\" - HAL9000");
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				Users user = mapper.readValue(new File(Config.getUserFile(sender)), Users.class);
				Balance balance = user.getBalance();
				String delayed = balance.getDelayed();
				String pending = balance.getPending();
				double pendingNum = Double.parseDouble(pending) + Double.parseDouble(delayed);
				pending = String.format("%.8f", pendingNum);
				balance.setDelayed("0.00000000");
				balance.setPending(pending);
				user.setBalance(balance);
				mapper.writeValue(new File(Config.getUserFile(sender)), user);
				bot.sendMessage(sender, "Withdraw Success! Pending: " + pending + " BTC");
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		// "!setbitcoin" command
		} else if (message.matches("^[.!][sS]etbitcoin(.*)")) {

			String[] words = message.split("\\s");
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				Users user = mapper.readValue(new File(Config.getUserFile(sender)), Users.class);
				if (words.length < 2) {
					bot.sendMessage(sender, "Your address is " + Colors.BOLD + user.getAddress() + Colors.NORMAL + " (change with '!setbitcoin <ADDRESS>')");
				} else { 
					user.setAddress(words[1]);
					mapper.writeValue(new File(Config.getUserFile(sender)), user);
					bot.sendMessage(sender, "Success! " + Colors.BOLD + words[1] + Colors.NORMAL + " (saved)");
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
