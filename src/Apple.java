import java.awt.Graphics;
import javax.swing.ImageIcon;

//Apple class represents a regular apple in the game
class Apple extends Food {
	private ImageIcon regularAppleIcon;

	// Constructor for Apple class
	public Apple(int x, int y) {
		super(x, y, 0); // Type 0 for regular apple
		regularAppleIcon = new ImageIcon("apple.png");
	}

	// Method to draw the regular apple on the screen
	@Override
	public void draw(Graphics g) {
		regularAppleIcon.paintIcon(null, g, x, y);
	}

	// Method to draw the regular apple on the screen (overloaded for game over
	// scenario)
	@Override
	public void draw(Graphics g, boolean gameOver) {
		if (!gameOver) {
			ImageIcon appleImage = new ImageIcon("apple.png");
			appleImage.paintIcon(null, g, x, y);
		}
	}

	// Method to handle the eating of a regular apple
	@Override
	public int eat() {
		return 1; // Regular apple: +1 point
	}

}