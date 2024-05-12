import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		// Create JFrame
		JFrame f = new JFrame();
		f.setTitle("Snake Game");
		f.setBounds(10, 10, 905, 670);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Show instructions dialog
		showInstructionsDialog();

		// Ask for difficulty level
		int difficultyLevel = askForDifficultyLevel();
		if (difficultyLevel >= 1 && difficultyLevel <= 5) {
			// Create SpeedManager instance with chosen difficulty level
			SpeedManager speedManager = new SpeedManager(difficultyLevel);

			// Show confirmation dialog to start the game
			int startGameChoice = showStartGameConfirmationDialog();
			if (startGameChoice == JOptionPane.YES_OPTION) {
				// Create Gameplay instance and pass SpeedManager to it
				GameEntity gameplay = new GameEntity(speedManager);
				f.add(gameplay);

				// Make the frame visible after adding the gameplay panel
				f.setVisible(true);
			} else {
				// If user chooses not to start, close the application
				System.exit(0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid difficulty level! Difficulty level must be between 1 and 5.",
					"Error", JOptionPane.ERROR_MESSAGE);
			// If difficulty level is invalid, close the application
			System.exit(0);
		}
	}

	// Method to show instructions dialog
	private static void showInstructionsDialog() {
		// Define custom font style and size
		Font customFont = new Font("Arial", Font.PLAIN, 10);

		// Create HTML formatted message for better styling
		String message = "<html><body style='font-family: Arial; font-size: 10px;'>"
				+ "<center><b>🐍 Welcome to the Snake Game! 🍎</b></center><br/>"
				+ "<p>In this thrilling adventure, you'll take on the role of a cunning snake navigating its way through a perilous maze. Here are some essential instructions to guide you through the game:</p>"
				+ "<ul>"
				+ "<li><b>🕹️ Controls:</b> Space Bar-> Restart | Enter-> End game Shift-> Pause Game 🕹️ </li>"
				+ "<li><b>🕹️ Controls:</b> Use the arrow keys on your keyboard to control the movement of the snake. 🕹️ </li>"
				+ "<li><b>🍎 Objective:</b> Your objective is to guide the snake to consume as many apples as possible. 🍎</li>"
				+ "<li><b>🍎 Objective:</b> While there are 3 apples, each has different points for it which you can see at the screen top. 🍎</li>"
				+ "<li><b>   </li>"
				+ "<li><b> Each apple eaten will make the snake grow longer and the poison apple will decrease the length too.</li>"
				+ "<li><b>⚠️ Avoid Collisions:</b> Be wary of the snake's own body! A collision with its own tail will result in a game over. ⚠️</li>"
				+ "<li><b>🌟 Level Progression:</b> The game consists of five challenging levels. To advance to the next level, you must accumulate five points in each level. The difficulty increases with each level. 🌟</li>"
				+ "<li><b>🕒 Be aware, Poison Apple = -2 ; Red Apple = +1; Super Apple = +2. Eat the Super Apple to Win fast. 🕒</li>"
				+ "<li><b>⚠️ Also, If the point goes to -1, the game will be OVER. So STAY ALERT WHILE PLAYING. ⚠️ </li>"
				+ "<li><b>🏆 Become the Ultimate Champion:</b> Only the most skilled players can conquer the final level and emerge victorious. 🏆</li>"
				+ "</body></html>";

		// Show dialog with styled message and increased size
		JOptionPane.showMessageDialog(null, message, "Instructions", JOptionPane.INFORMATION_MESSAGE, null);
	}

	// Method to ask for difficulty level
	private static int askForDifficultyLevel() {
		String input = JOptionPane.showInputDialog(null, "Choose the difficulty level for the game (1 to 5):",
				"Difficulty Level", JOptionPane.QUESTION_MESSAGE);
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return -1; // Invalid input
		}
	}

	// Method to show confirmation dialog to start the game
	private static int showStartGameConfirmationDialog() {
		String[] options = { "Start", "Exit" };
		return JOptionPane.showOptionDialog(null, "Are you ready to begin?", "Start Game", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
}
