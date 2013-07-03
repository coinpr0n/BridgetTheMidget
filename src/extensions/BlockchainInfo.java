//
// URL
//	Get the title of a URL
package extensions;

import core.Bot;

import extensions.ticker.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.io.IOException;
import java.util.Map;

public class BlockchainInfo {

	public static boolean test(String s) {
		return s.matches("^[.!][tT]icker(.*)");
	}

	public static void run(Bot bot, String channel, String sender, String message) {

			try {
				ObjectMapper mapper = new ObjectMapper();

				try {
					Map<String,Map> ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Map.class);
					//Ticker ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Ticker.class);
					Map<String,Double> usd = ticker.get("USD");
					double last = usd.get("last");
					bot.sendMessage(channel, "$" + String.format("%.2f", last));
					//USD usd = ticker.getUSD();
					//String symbol = usd.getSymbol();
					//Number last = usd.getLast();
					//String price = "$" + String.format("%.2f", last);
					//bot.sendMessage(channel, "Last: " + price);
				} catch (JsonParseException ex) {
					ex.printStackTrace();
				} catch (JsonMappingException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
			}

	}

}
