package core;

public class Logger {
	
	public static final String NORMAL = "\033[0m";
	public static final String BOLD_RED = "\033[01;31m";
	public static final String RED = "\033[31m";
	public static final String BOLD_GREEN = "\033[01;32m";
	public static final String GREEN = "\033[32m";
	public static final String BOLD_YELLOW = "\033[01;33m";
	public static final String YELLOW = "\033[33m";
	public static final String BOLD_BLUE = "\033[01;34m";
	public static final String BLUE = "\033[34m";
	//private static final String BOLD = "\u00033";
	
	private static boolean DEBUG = true;
	
	
	private Logger() {
		
	}
	
	public static void setLogger(boolean onOff) {
		DEBUG = onOff;
	}
	
	public static void info(String message) {
		if (DEBUG == true) {
			System.out.println(BOLD_YELLOW + "[INFO] " + NORMAL + message + NORMAL);
		}
	}
	
	public static void warn(String message) {
		if (DEBUG == true) {
			System.out.println(BOLD_RED + "[WARN] " + NORMAL + message + NORMAL);
		}
	}
	
	public static void error(String message) {
		if (DEBUG == true) {
			System.out.println(BOLD_RED + "[ERROR] " + message + NORMAL);
		}
	}

}
