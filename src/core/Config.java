package core;

public class Config {
	
	private static final String BOT_DIR = "/home/monk/Bridget/";
	private static final String DATA_DIR = BOT_DIR + "data/";
	private static final String USER_DIR = DATA_DIR + "users/";
	
	private Config() {
		
	}
	
	public static String getUserFile(String user) {
		return USER_DIR + user + ".json";
	}
	
	public static String getUserTemplate() {
		return DATA_DIR + "users.json";
	}

}
