import java.awt.Graphics;
import javax.swing.ImageIcon;

//Class representing the PoisonApple food item
class PoisonApple extends Food {
	private ImageIcon poisonAppleIcon;

	// Constructor to initialize PoisonApple object with specified position
	public PoisonApple(int x, int y) {
		super(x, y, -1); // Type -1 for poison apple
		poisonAppleIcon = new ImageIcon("poisonapple.png");
	}

	// Method to draw the poison apple on the graphics context
	@Override
	public void draw(Graphics g) {
		poisonAppleIcon.paintIcon(null, g, x, y);
	}

	// Method to draw the poison apple with game over condition
	@Override
	public void draw(Graphics g, boolean gameOver) {
		if (!gameOver) {
			ImageIcon PoisonappleImage = new ImageIcon("poisonapple.png");
			PoisonappleImage.paintIcon(null, g, x, y);
		}
	}

	@Override
	public int eat() {
		return -3; // Return score for eating poison apple
	}
}
