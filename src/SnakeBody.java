import javax.swing.ImageIcon;
import java.awt.Graphics;

public class SnakeBody {
	private int[] xLength;
	private int[] yLength;
	private int length;
	private ImageIcon bodyImage;

	// Constructor to initialize the snake's body
	public SnakeBody(int maxLength) {
		xLength = new int[maxLength];
		yLength = new int[maxLength];
		bodyImage = new ImageIcon("snakeimage.png");// Load the snake body image
	}

	// Getter method for the X-coordinates of body segments
	public int[] getXLength() {
		return xLength;
	}

	// Getter method for the Y-coordinates of body segments
	public int[] getYLength() {
		return yLength;
	}

	// Method to set the position of a body segment at a specific index
	public void setBodySegment(int index, int x, int y) {
		xLength[index] = x;
		yLength[index] = y;
	}

	// Method to increment the snake's body length
	public void incrementLength() {
		length++;
	}

	// Method to draw the snake's body
	public void draw(Graphics g) {
		for (int i = 1; i < length; i++) {
			bodyImage.paintIcon(null, g, xLength[i], yLength[i]);
		}
	}

	// Getter method for the snake's body length
	public int getLength() {
		return length;
	}

	// Setter method for the snake's body length
	public void setLength(int length) {
		this.length = length;
	}
}