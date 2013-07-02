package core;

import org.jibble.pircbot.*;

public class Bot extends PircBot {
	
	private String ircAddress;
	private String[] channels;
	private String nickServPass;
	
	public Bot(String name, String login, String version, String ircAddress, String[] channels, String nickServPass) {
		setVerbose(true);
		setName(name);
		setLogin(login);
		setVersion(version);
		this.ircAddress = ircAddress;
		this.channels = channels;
		this.nickServPass = nickServPass;
	}

	//
	// start - connects to server, identifies and joins channels
	public void start() {
		try {
			this.connect(ircAddress);
			if (nickServPass != "") this.identNickServ(nickServPass);
			for (int i=0;i < channels.length;i++) this.joinChannel(channels[i]);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	//
	// identNickServ - Identify to NickServ using a password
	public void identNickServ(String nickServPass) {
		sendMessage("NickServ", "IDENTIFY " + nickServPass);
	}
	
	//
	// identNickServ - Identify to NickServ using a password
	public void sendRandomMessage(String[] items, String channel, String message) {
		int i = (int) (Math.random() * items.length);
		sendMessage(channel, message + " " + items[i]);
	}

}
