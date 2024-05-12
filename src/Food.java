import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.util.Random;

public abstract class Food {
	protected int x;
	protected int y;
	protected int type;

	public Food(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public abstract void draw(Graphics g);

	public abstract int eat();

	public abstract void draw(Graphics g, boolean gameOver);

	// Method to check collision between food item and snake head
	public boolean checkCollision(int snakeHeadX, int snakeHeadY) {
		return this.x == snakeHeadX && this.y == snakeHeadY;
	}

	// Method to get the type of food item
	public int getType() {
		return type;
	}

	// Method to create random apples with specified positions
	public static Food[] createRandomApples(int[] applexpos, int[] appleypos) {
		Random random = new Random();
		Food[] apples = new Food[2];

		// Generate random positions for each apple
		int x1 = applexpos[random.nextInt(applexpos.length)];
		int y1 = appleypos[random.nextInt(appleypos.length)];
		int x2 = applexpos[random.nextInt(applexpos.length)];
		int y2 = appleypos[random.nextInt(appleypos.length)];

		// Create the first apple
		apples[0] = new Apple(x1, y1);

		// Randomly choose the type of the second apple
		int secondAppleType = random.nextInt(3); // 0, 1, or 2
		switch (secondAppleType) {
		case 0:
			apples[1] = new Apple(x2, y2); // Regular apple
			break;
		case 1:
			apples[1] = new SuperApple(x2, y2); // Super apple
			break;
		case 2:
			apples[1] = new PoisonApple(x2, y2); // Poison apple
			break;
		default:
			apples[1] = new Apple(x2, y2); // Default to regular apple
			break;
		}
		return apples;
	}

}
