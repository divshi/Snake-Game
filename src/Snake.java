import java.awt.Graphics;

public class Snake {
	private SnakeHead head;
	private SnakeBody body;
	public boolean right = true, left = false, up = false, down = false;

	public Snake() {
		head = new SnakeHead();
		body = new SnakeBody(750); // Assumes maximum snake length
		resetSnake();
	}

	public void resetSnake() {
		body.setLength(3); // Initial length of the snake
		body.setBodySegment(0, 100, 100); // Head position
		body.setBodySegment(1, 75, 100); // Second segment
		body.setBodySegment(2, 50, 100); // Third segment
		head.setPosition(body.getXLength()[0], body.getYLength()[0]);
		right = true;
		left = false;
		up = false;
		down = false;
	}

	public void move() {
		// Move the body
		for (int i = body.getLength() - 1; i > 0; i--) {
			body.setBodySegment(i, body.getXLength()[i - 1], body.getYLength()[i - 1]);
		}

		// Move the head
		if (right) {
			head.setPosition((head.getX() + 25) % 875, head.getY()); // 875 should be the width boundary
		} else if (left) {
			head.setPosition((head.getX() - 25 + 875) % 875, head.getY());
		} else if (up) {
			head.setPosition(head.getX(), (head.getY() - 25 + 650) % 650); // 650 should be the height boundary
		} else if (down) {
			head.setPosition(head.getX(), (head.getY() + 25) % 650);
		}

		// Update the first segment to the new head position
		body.setBodySegment(0, head.getX(), head.getY());
	}

	public int getHeadX() {
		return head.getX();
	}

	public int getHeadY() {
		return head.getY();
	}

	public int getSnakeLength() {
		return body.getLength();
	}

	public void setSnakeLength(int length) {
		body.setLength(length);
	}

	public boolean isGameOver(int score) {
		// Check if the snake has collided with itself
		int[] xLength = body.getXLength();
		int[] yLength = body.getYLength();
		for (int i = 1; i < body.getLength(); i++) {
			if (xLength[i] == head.getX() && yLength[i] == head.getY()) {
				return true;
			}
		}
		// Check if the score is less than or equal to -1
		return score <= -1;
	}

	public void paintSnake(Graphics g) {
		// Draw the snake on the screen
		head.draw(g, right, left, up, down);
		body.draw(g);
	}
}