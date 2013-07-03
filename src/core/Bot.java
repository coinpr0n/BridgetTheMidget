package core;

import org.jibble.pircbot.*;

public class Bot extends PircBot {
	
	private String ircAddress;
	private String[] channels;
	private String nickServPass;

	/**
	 * Bot constructor
	 *
	 * @param name
	 * @param login
	 * @param version
	 * @param ircAddress
	 * @param channels
	 * @param nickServPass
	 */
	public Bot(String name, String login, String version, String ircAddress, String[] channels, String nickServPass) {
		setAutoNickChange(true);
		setVerbose(true);
		setName(name);
		setLogin(login);
		setVersion(version);
		this.ircAddress = ircAddress;
		this.channels = channels;
		this.nickServPass = nickServPass;
	}

	/**
	 * Connects bot to IRC server and identifies with NickServ (if necessary).
	 */
	public void start() {
		try {
			this.connect(ircAddress);
			if (nickServPass != null) this.identify(nickServPass);
			for (int i=0;i < channels.length;i++) this.joinChannel(channels[i]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends message followed by a random element selected from an array items.
	 *
	 * @param items
	 * @param channel
	 * @param message
	 */
	public void sendRandomMessage(String[] items, String channel, String message) {
		int i = (int) (Math.random() * items.length);
		sendMessage(channel, message + " " + items[i]);
	}

}
