import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameEntity extends JPanel implements KeyListener, ActionListener {
	private Snake snake;
	private Food food;
	private Food food2; // Second apple

	// Arrays to store x and y positions for apples
	private int[] applexpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };

	private int[] appleypos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600 };

	private Random random = new Random(); // Random object
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	private Timer timer;// Timer for game updates
	private int delay = 100;// Delay for timer
	private int scores = 0;// Player scores
	private Level levelManager; // Level class instance
	private SpeedManager speedManager; // SpeedManager class instance
	private boolean gameOver = false; // Game over flag
	private boolean paused = false; // Pause flag

	public GameEntity(SpeedManager speedManager) {
		snake = new Snake();
		this.speedManager = speedManager; // Initialize SpeedManager instance
		this.levelManager = new Level(); // Initialize Level instance

		// Initialize Food instance with random positions and types for both apples
		Food[] apples = Food.createRandomApples(applexpos, appleypos);
		food = apples[0];
		food2 = apples[1];

		setPreferredSize(new Dimension(900, 600)); // Set preferred size of the gameplay area
		setFocusable(true);
		addKeyListener(this);

		timer = new Timer(delay, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// Call superclass method
		SnakeGraphics snakeGraphics = new SnakeGraphics();// Create SnakeGraphics object
		if (food != null && food2 != null) {
			food.draw(g); // Draw the first apple
			food2.draw(g); // Draw the second apple
		}

		snakeGraphics.paint(g, snake, food, food2, applexpos, appleypos, scores, levelManager, speedManager, timer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!gameOver && !paused) { // Check if the game is not over and not paused
			snake.move();// Move the snake

			// Check for collision with first apple
			if (food.checkCollision(snake.getHeadX(), snake.getHeadY())) {
				System.out.println("Collision detected with first apple!");
				int points = food.eat();
				scores += points;
				System.out.println("Score: " + scores);// Print current scores

				// Adjust snake's length based on points
				snake.setSnakeLength(snake.getSnakeLength() + points);

				// Generate new position and types for the apples
				int newX = applexpos[random.nextInt(applexpos.length)];
				int newY = appleypos[random.nextInt(appleypos.length)];
				Food[] newApples = Food.createRandomApples(applexpos, appleypos);
				food = newApples[0];// Set first apple
				food2 = newApples[1];// Set second apple

				// Check if score is a multiple of 5 to increment level
				if (scores >= 0 && scores % 5 == 0) {// Check if score is a multiple of 5 to increment level
					levelManager.incrementLevel(); // Increment level
					System.out.println("Level increased to: " + levelManager.getCurrentLevel());
				} else if (scores < (levelManager.getCurrentLevel() - 1) * 5) {
					levelManager.decrementLevel();
					System.out.println("Level decreased to: " + levelManager.getCurrentLevel());
				}
			} else if (food2.checkCollision(snake.getHeadX(), snake.getHeadY())) {
				System.out.println("Collision detected with second choice apple!");
				int points = food2.eat();
				scores += points; // Increment scores
				System.out.println("Score: " + scores);// Print current scores

				// Adjust snake's length based on points
				snake.setSnakeLength(snake.getSnakeLength() + points);

				if (snake.isGameOver(scores)) { // Pass the scores variable to isGameOver method
					gameOver = false; // Set game over flag
					timer.stop(); // Stop the timer
				}

				// Generate new position and types for the apples
				int newX = applexpos[random.nextInt(applexpos.length)];
				int newY = appleypos[random.nextInt(appleypos.length)];
				Food[] newApples = Food.createRandomApples(applexpos, appleypos);
				food = newApples[0];
				food2 = newApples[1];

				// Check if score is a multiple of 5 to increment level
				if (scores >= 0 && scores % 5 == 0) {
					levelManager.incrementLevel();
					System.out.println("Level increased to: " + levelManager.getCurrentLevel());
				} else if (scores < (levelManager.getCurrentLevel() - 1) * 5) {
					levelManager.decrementLevel();
					System.out.println("Level decreased to: " + levelManager.getCurrentLevel());
				}
			}

			repaint();
		} else {
			// Optionally, handle the case when the game is paused or over
			if (paused) {
				System.out.println("Game Paused!");
			} else {
				System.out.println("Game Over!");
			}
		}
	}

	// Method to handle key pressed events
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// Update snake direction based on arrow keys
		if (key == KeyEvent.VK_RIGHT && !snake.left) {
			snake.right = true;
			snake.up = false;
			snake.down = false;
		}
		if (key == KeyEvent.VK_LEFT && !snake.right) {
			snake.left = true;
			snake.up = false;
			snake.down = false;
		}
		if (key == KeyEvent.VK_UP && !snake.down) {
			snake.up = true;
			snake.right = false;
			snake.left = false;
		}
		if (key == KeyEvent.VK_DOWN && !snake.up) {
			snake.down = true;
			snake.right = false;
			snake.left = false;
		}
		if (key == KeyEvent.VK_SHIFT) {
			togglePause(); // Toggle pause state
		}
		if (key == KeyEvent.VK_SPACE) {
			restartGame();
		}
		if (key == KeyEvent.VK_ENTER) {
			System.exit(0);
		}
	}

	private void togglePause() {
		paused = !paused;
		if (paused) {
			timer.stop(); // Pause the timer
		} else {
			timer.start(); // Resume the timer
		}
	}

	private void restartGame() {
		scores = 0;
		snake.resetSnake();
		levelManager.resetLevel(); // Reset level using Level class method
		gameOver = false; // Reset game over flag
		timer.start(); // Restart the timer
		repaint();
	}

	private void isGameOver() {
		gameOver = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Intentionally left blank
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Intentionally left blank
	}
}
