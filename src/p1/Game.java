package p1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	Racquet1 racquet1 = new Racquet1(this);
	int speed = 1;

	public void playSound(String soundName)
	 {
	   try 
	   {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
	    Clip clip = AudioSystem.getClip( );
	    clip.open(audioInputStream);
	    clip.start( );
	   }
	   catch(Exception ex)
	   {
	     System.out.println("Error with playing sound.");
	     ex.printStackTrace( );
	   }
	 }


	public Game() {
		setBackground(Color.BLACK);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				racquet1.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
				racquet1.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		ball.move();
		racquet.move();
		racquet1.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green);
		g2d.fillRect(0, 0, getWidth(), 5);
		g2d.fillRect(0, getHeight() - 5, getWidth(), 5);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);     //smoothening of edges.
		g2d.setColor(Color.blue);
		ball.paint(g2d);
		g2d.setColor(Color.red);
		racquet.paint(g2d);
		g2d.setColor(Color.yellow);
		racquet1.paint(g2d);
		
		
	}
	
	public void GameOver() {
		playSound("a3.wav");
		if(ball.count % 2 == 0)
			JOptionPane.showMessageDialog(this, "Yellow WINS!" ,
					"Game Over", JOptionPane.YES_NO_OPTION);
		else
			JOptionPane.showMessageDialog(this, "Red WINS!" ,
					"Game Over", JOptionPane.YES_NO_OPTION);
		
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Save The Ball");
		String str= "<html><big><font color='green'>Save The Ball</font></big><hr>"
								
				+"<p align=center><font size='5' color='blue'>Players</font>"
								
				+"<br><br><p align=left>Player1 - Red Racquet"
								
				+"<p align=left>Player2 - Yellow Racquet"
								
				+"<hr><p align=center><font size='5' color='blue'>Controls</font>"
								
				+"<br><br><p align=left> Red- Keys UP and DOWN aroow keys"
								
				+"<p align=left>Yellow - Keys- W & S<hr>"
								
				+"<br><p align=center>Click 'OK' to start the game.<br>";
		int value =JOptionPane.showConfirmDialog(frame, str, "Game instructions", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if(value== JOptionPane.CLOSED_OPTION)
			System.exit(0);
		

		Game Game = new Game();
		frame.add(Game);
		frame.setBounds(700, 400, 500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			Game.move();
			Game.repaint();
			Thread.sleep(10);
		}
	}
}
