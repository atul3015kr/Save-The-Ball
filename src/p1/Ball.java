package p1;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	int count = 0;
	private Game Game;

	public Ball(Game Game) {
		this.Game= Game;
	}

	void move() {
		if (x + xa < 0)
			Game.GameOver();
		if (x + xa > Game.getWidth() - DIAMETER)
			Game.GameOver();
		if (y + ya < 0)
			ya = Game.speed;
		if (y + ya > Game.getHeight() - DIAMETER)
			ya = -Game.speed;
		if (collision()){
			Game.playSound("a1.wav");
			count++;
			if(count%2 != 0){
				xa = Game.speed;
				x = Game.racquet.getTopY() - DIAMETER;
			}
			else{
				xa = -Game.speed;
				x = Game.racquet1.getTopY() - DIAMETER;
				Game.speed++;
			}
			
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		if(Game.racquet.getBounds().intersects(getBounds()) || Game.racquet1.getBounds().intersects(getBounds()))
			return true;
		else 
			return false;
		
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}