public class SpeedManager {
	private double speedMultiplier;

	// Constructor with speed multiplier parameter
	public SpeedManager(double speedMultiplier) {
		// Check if the provided speed multiplier is valid (between 1 and 5)
		if (speedMultiplier >= 1 && speedMultiplier <= 5) {
			this.speedMultiplier = speedMultiplier;
		} else {
			System.out.println("Invalid speed multiplier! Speed multiplier must be between 1 and 5.");
			// Assign default speed multiplier if the provided one is invalid
			this.speedMultiplier = 1;
		}
	}

	// Method to get the current speed multiplier
	public double getSpeedMultiplier() {
		return speedMultiplier;
	}

	// Method to get the delay for a specific level
	public int getDelayForLevel(int level) {
		int delay;
		// Determine the delay based on the level using a switch statement
		switch (level) {
		case 1:
			delay = 250;
			break;
		case 2:
			delay = 200;
			break;
		case 3:
			delay = 150;
			break;
		case 4:
			delay = 100;
			break;
		case 5:
			delay = 75;
			break;
		case 6:
			delay = Integer.MAX_VALUE;
			break;
		default:
			delay = 250;
			break;
		}
		// Multiply the delay by the speed multiplier
		return (int) (delay * speedMultiplier); // Cast to int after multiplication
	}
}