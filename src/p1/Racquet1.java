package p1;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet1 {
	private static final int WIDTH = 10;
	private static final int HEIGHT = 60;
	int y = 5;
	int ya = 0;
	private Game Game;

	public Racquet1(Game Game) {
		this.Game = Game;
	}

	public void move() {
		if (y + ya > 5 && y + ya < Game.getHeight() - HEIGHT-5)
			y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.fillRect(Game.getWidth() - 10, y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			ya = -Game.speed;
		if (e.getKeyCode() == KeyEvent.VK_S)
			ya = Game.speed;
	}

	public Rectangle getBounds() {
		return new Rectangle(Game.getWidth() - 10, y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Game.getWidth() - 10;
	}
}