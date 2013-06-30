package core;

import extensions.*;

public class Bridget extends Bot {
	
	/**
	 * Bot's nickname on IRC
	 */
	private static String botName = "BridgetTheMidget";
	
	/**
	 * Bot's "real name" (for WHOIS)
	 */
	private static String realName = "ChatBot";
	
	/**
	 * Version Information (for WHOIS)
	 */
	private static String versionInfo = "ChatBot 0.0.1 IRC Bot";
	
	/**
	 * IRC Server Address (eg. irc.geekshed.net)
	 */
	private static String ircAddress = "irc.geekshed.net";
	
	/**
	 * List of all the channels the bot should connect to
	 */
	private static String[] channels = {"#cvchat","#cvchat-dev"};
	
	/**
	 * NickServ password (only needed for registered nicks)
	 */
	private static String nickServPass = "-info@coinvisitor.com-01";

	
	/**
	 * Bridget constructor
	 * 
	 * Calls parent object's constructor with local instance variables.
	 */
	public Bridget() {
		super(botName, realName, versionInfo, ircAddress, channels, nickServPass);
	}

	/**
	 * onMessage
	 * 
	 * Load all the extensions we want our bot to test(), and run() if needed.
	 */
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		
		//...
		if (AntiFlood.test(message))
			AntiFlood.run(this, channel, sender, message);
		
		//...
		if (ParseUrl.test(message))
  		ParseUrl.run(this, channel, sender, message);
  	
		//...
		if (PirateShip.test(message))
  		PirateShip.run(this, channel, sender, message);
		
		//...
		if (HelloWorld.test(message))
			HelloWorld.run(this, channel, sender, message);

		//...
		if (RandomPrize.test(message))
  		RandomPrize.run(this, channel, sender, message);
		
		//...
		if (UserBalance.test(message))
  		UserBalance.run(this, channel, sender, message);
		
	}

}
