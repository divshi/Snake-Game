import java.awt.Graphics;
import javax.swing.ImageIcon;

class SuperApple extends Food {
	// Private variable to store the ImageIcon for the super apple
	private ImageIcon superAppleIcon;

	// Constructor for SuperApple class, takes x and y coordinates as parameters
	public SuperApple(int x, int y) {
		super(x, y, 1); // Type 1 for super apple
		superAppleIcon = new ImageIcon("superapple.png");
	}

	// Override the draw method from the Food class to draw the super apple
	@Override
	public void draw(Graphics g) {
		superAppleIcon.paintIcon(null, g, x, y);
	}

	// Override the draw method from the Food class with an additional parameter for
	// game over state
	@Override
	public void draw(Graphics g, boolean gameOver) {
		// Check if the game is not over
		if (!gameOver) {
			ImageIcon superappleImage = new ImageIcon("superapple.png");
			superappleImage.paintIcon(null, g, x, y);
		}
	}

	// Override the eat method from the Food class to return 2 points for eating a
	// super apple
	@Override
	public int eat() {
		return 2;
	}
}