package p1;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private static final int X = 0;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 60;
	int y = 5;
	int ya = 0;
	private Game Game;

	public Racquet(Game Game) {
		this.Game = Game;
	}

	public void move() {
		if (y + ya > 5 && y + ya < Game.getHeight() - HEIGHT-5)
			y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.fillRect(X, y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			ya = -Game.speed;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			ya = Game.speed;
	}

	public Rectangle getBounds() {
		return new Rectangle(0, y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return 40;
	}
}