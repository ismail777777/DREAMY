package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/** 
 * The score of the player
 * @author ismail El Alout
 *
 */
public class Score {

	private int value;

	public Score() {
		this.value = 0;
	}

	/**
	 * set the value of the score
	 * @param n
	 */
	public void setValue(int n) {
		this.value = n;
	}

	/**
	 * 
	 * @return the value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * update the score
	 */
	public void update() {
		this.value += 1;
	}

	/**
	 * draw the score on the game screen
	 * @param g
	 */
	public void draw(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(5,  5);
		//g.drawImage(endImage, 30, 700, null);
		g.setTransform(oldTransform);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial Black", 30, 30));
		g.drawString(" SCORE : "+ this.value, 650, 750);
	}

	/**
	 * show the score at the end 
	 * @param g
	 */
	public void show(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(5,  5);
		g.setTransform(oldTransform);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial Black", 50, 50));
		g.drawString(" GAME OVER ! ", 500, 380);
		g.drawString(" +1000 coins", 500, 450);
	}
}
