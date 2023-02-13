package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

/**
 * the energy of the player
 * 
 * @author ismail El Alout
 *
 */
public class Energy {

	// Variables
	private double Value;
	private Image image;
	
	public Energy() {
		this.image = new ImageIcon(getClass().getResource("resources/enegry.png")).getImage();
		this.Value = 100;
	}
	
	public void draw(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(5,  5);
		if (Value < 40) {
			g.drawImage(image, 20, 40, null);
		} else if ((Value >= 40) && (Value < 70)) {
			g.drawImage(image, 20, 40, null);
			g.drawImage(image, 75, 40, null);
		} else if ((Value >= 70) && (Value <= 100)) {
			g.drawImage(image, 20, 40, null);
			g.drawImage(image, 75, 40, null);
			g.drawImage(image, 130, 40, null);
		}
		g.setTransform(oldTransform);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", 30, 30));
		g.drawString(" "+ (int) Math.floor(Value)+"%", 190, 90);
	}
	
	public void decrease() {
		if (this.Value - 30 >= 0) {
			this.Value -= 30;
		} else {
			this.Value = 0;
		}
	}
	
	public void increase(int i) {
		if (this.Value + 5 <= 100) {
			this.Value += i*5;
		} else {
			this.Value = 100;
		}
		
	}

	/**
	 * 
	 * @return the value of the energy
	 */
	public double getValue() {
		return this.Value;
	}
}
