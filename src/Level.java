class Level {
	private int level;

	// Constructor
	public Level() {
		level = 1;
	}

	// Method to get the current level
	public int getCurrentLevel() {
		return level;
	}

	// Method to increment the level
	public void incrementLevel() {
		level++;
	}

	public void decrementLevel() {
		level--;
	}

	// Method to reset the level
	public void resetLevel() {
		level = 1;
	}
}
