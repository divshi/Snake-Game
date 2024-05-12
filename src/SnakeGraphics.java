import javax.swing.*;
import java.awt.*;

public class SnakeGraphics {
	private Image youWonImage;
	private Image snakeTitleImage;
	private Image gameOverImage;

	public SnakeGraphics() {
		// Constructor
		try {
			// Load the "You Won" image
			youWonImage = new ImageIcon("youwon.png").getImage();
			// Load the Snake title image
			snakeTitleImage = new ImageIcon("snaketitle.png").getImage();
			// Load the gameover image
			gameOverImage = new ImageIcon("gameover.png").getImage();
		} catch (Exception e) {
			// Print the error message if image loading fails
			System.err.println("Error loading images:");
			e.printStackTrace();
		}
	}

	// Method to paint game elements
	public void paint(Graphics g, Snake snake, Food food, Food food2, int[] applexpos, int[] appleypos, int scores,
			Level levelManager, SpeedManager speedManager, Timer timer) {
		int speedMultiplier = (int) speedManager.getSpeedMultiplier();

		// Draw the game elements
		// Draw the black background for the entire gameplay area
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 900, 650);

		// Draw the Snake title image at the center
		int xSnakeTitle = (900 - snakeTitleImage.getWidth(null)) / 2;
		int ySnakeTitle = (650 - snakeTitleImage.getHeight(null)) / 2;
		g.drawImage(snakeTitleImage, xSnakeTitle, ySnakeTitle, null);

		// Draw the scores and level
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Scores : " + scores, 750, 35);
		g.drawString("Level : " + levelManager.getCurrentLevel(), 750, 60);

		// Delegate drawing the snake to the Snake class
		snake.paintSnake(g);

		// Draw the apples
		if (food != null) {
			food.draw(g); // Draw the first apple
		}
		if (food2 != null) {
			food2.draw(g); // Draw the second apple
		}

		// Draw the "You Won" image if the player won the game
		if (levelManager.getCurrentLevel() == 5) {
			g.drawImage(youWonImage, 0, 0, null);
		}

		// Check if the game is over by passing the scores variable to isGameOver method
		if (snake.isGameOver(scores)) {
			snake.right = false;
			snake.left = false;
			snake.up = false;
			snake.down = false;

			g.drawImage(gameOverImage, 0, 0, null);

		}

		// Calculate delay based on speed multiplier and level
		int levelDelay = speedManager.getDelayForLevel(levelManager.getCurrentLevel());
		int adjustedDelay = levelDelay / speedMultiplier;

		// Set the timer delay
		timer.setDelay(adjustedDelay);
		timer.start(); // Restart the timer with the new delay

	}
}