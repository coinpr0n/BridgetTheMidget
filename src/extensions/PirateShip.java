/**
 * PirateShip
 * 
 * Sends a randomly picked "Cabin Crew" member's picture to the channel
 * 
 */
package extensions;

import core.Bot;

public class PirateShip {

	/**
	 * Array of images to choose from ...
	 */
	private static String[] images = {
		"http://www.uproxx.com/wp-content/uploads/2010/09/Sexy-Pirate.jpg",
		"http://www.malltop1.com/UpLoad/Pro_Images_02/o_sexy-lingerie-Pirate-Costumes-P1520_49_49.jpg",
		"http://federicodelossantos.com/fede_blog/wp-content/uploads/2009/08/sexy-pirate-5.jpg",
		"http://www.gothikas.es/fotos-disfraces-sexy/disfraz-sexy-pirata.jpg",
		"http://cdn.indulgy.com/kA/46/OA/11919P1318469409943.jpg",
		"http://emailsfromgrandpa.com/wp-content/uploads/2012/06/sexy-pirate-girl-with-parrot.jpg",
		"http://blog.celebritymovieblog.com/wp-content/uploads/post_thumbs/keira_knightley_nude_pirate.jpg"
	};


	/**
	 * Tests message for commands '.crew' and '.pr0n'
	 * 
	 * @param message
	 * @return message is a PirateShip command (true or false)
	 */
	public static boolean test(String message) {
		return message.matches("^[.!][cC]rew(.*)|^[.!][pP]r[oO0]n(.*)");
	}
	
	/**
	 * Responds with a random "Cabin Crew" member to the channel
	 * 
	 * @param bot
	 * @param channel
	 * @param sender
	 * @param message
	 */
	public static void run(Bot bot, String channel, String sender, String message) {
		bot.sendMessage(channel, "Cabin Crew " + randomImage());
	}
	
	/**
	 * Grabs a random image from the images[] array
	 * 
	 * @return random image from the (built-in) images[] array
	 */
	public static String randomImage() {
		int i = (int) (Math.random() * images.length);
		return images[i];
	}

}
