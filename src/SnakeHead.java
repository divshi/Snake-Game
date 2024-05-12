import javax.swing.ImageIcon;
import java.awt.Graphics;

public class SnakeHead {
	private int x;
	private int y;
	private ImageIcon rightMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;

	// Constructor to initialize the ImageIcon objects for different directions
	public SnakeHead() {
		// Load the image files for different directions
		rightMouth = new ImageIcon("rightmouth.png");
		leftMouth = new ImageIcon("leftmouth.png");
		upMouth = new ImageIcon("upmouth.png");
		downMouth = new ImageIcon("downmouth.png");
	}

	// Getter method for the X-coordinate of the snake's head
	public int getX() {
		return x;
	}

	// Getter method for the Y-coordinate of the snake's head
	public int getY() {
		return y;
	}

	// Method to set the position of the snake's head
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g, boolean right, boolean left, boolean up, boolean down) {
		// Check each direction flag and draw the corresponding ImageIcon
		if (right)
			rightMouth.paintIcon(null, g, x, y);
		if (left)
			leftMouth.paintIcon(null, g, x, y);
		if (up)
			upMouth.paintIcon(null, g, x, y);
		if (down)
			downMouth.paintIcon(null, g, x, y);
	}
}