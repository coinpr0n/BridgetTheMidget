//
// URL
//	Get the title of a URL
package extensions;

import core.Bot;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.io.IOException;
import java.util.Map;

public class BlockchainInfo {

	public static boolean test(String s) {
		return s.matches("^[.!][tT]icker(.*)|^[.!][bB][tT][cC]2[uU][sS][dD](.*)");
	}

	public static void run(Bot bot, String channel, String sender, String message) {

		if (message.matches("^[.!][tT]icker(.*)")) {
			double last;
			String symbol;

			if (message.split("\\s").length > 1) {

				try {
					ObjectMapper mapper = new ObjectMapper();

					try {
						Map<String,Map> ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Map.class);
						//Ticker ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Ticker.class);
						try {
							Map<String,Double> value = ticker.get(message.split("\\s")[1].toUpperCase());
							last = value.get("last");
							Map<String,String> currency = ticker.get(message.split("\\s")[1].toUpperCase());
							symbol = currency.get("symbol");
						} catch (NullPointerException ex) {
							Map<String,Double> value = ticker.get("USD");
							last = value.get("last");
							Map<String,String> currency = ticker.get("USD");
							symbol = currency.get("symbol");
						}

						bot.sendMessage(channel, "Last: " + "\u000303" + symbol + String.format("%.2f", last) + "\u000f");
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

			} else {

				try {
					ObjectMapper mapper = new ObjectMapper();

					try {
						Map<String,Map> ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Map.class);
						//Ticker ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Ticker.class);
						Map<String,Double> value = ticker.get("USD");
						last = value.get("last");
						Map<String,String> currency = ticker.get("USD");
						symbol = currency.get("symbol");
						bot.sendMessage(channel, "Last: " + "\u000303" + symbol + String.format("%.2f", last) + "\u000f");
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

		} else if (message.matches("^[.!][bB][tT][cC]2[uU][sS][dD](.*)")) {

			if (message.split("\\s").length > 1) {
				try {
					ObjectMapper mapper = new ObjectMapper();

					try {
						Map<String,Map> ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Map.class);
						//Ticker ticker = mapper.readValue(new URL("https://blockchain.info/ticker"), Ticker.class);
						Map<String,Double> value = ticker.get("USD");
						double last = value.get("last");
						double usd = Double.parseDouble(message.split("\\s")[1]) * last;
						bot.sendMessage(channel, message.split("\\s")[1] + " BTC => $" + String.format("%.2f", usd));
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
			} else {
				//TODO: do something
			}

		}

	}

}
