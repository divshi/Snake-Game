public class GameOver extends SpeedManager {
	public GameOver() {
		super(1); // Call the constructor of the superclass
	}

	@Override
	public int getDelayForLevel(int level) {
		// For level 5, return a very large delay to effectively halt the game
		if (level == 6) {
			return Integer.MAX_VALUE; // Very large delay
		} else {
			// For other levels, use the superclass method to get the delay
			return super.getDelayForLevel(level);
		}
	}

	// Additional method to display game over message
	public static void displayGameOver() {
		System.out.println("GAME OVER");
		System.out.println("Press SPACE to Restart");
		System.out.println("Press ENTER to End");
	}
}