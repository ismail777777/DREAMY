package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import javax.swing.ImageIcon;

/**
 * 
 * @author ismail El Alout
 *
 */
public class Spike {

	private double x, y;
	private Image image;
	private final Area spikeShape;
	private boolean up, down;

	public Spike(double x, double y) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(getClass().getResource("resources/spikes.png")).getImage();
		this.up = true;
		this.down = false;
		Path2D p = new Path2D.Double();
		p.moveTo(0, 50);
	    p.lineTo(80, 50);
	    p.lineTo(80, 10);
	    p.lineTo(50, 10);
	    p.lineTo(0, 10);
	    this.spikeShape = new Area(p);
	}

	/**
	 * 
	 * @return x
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * 
	 * @return y
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * 
	 * @return the shape of the spike
	 */
	public Area getShape() {
		AffineTransform affx = new AffineTransform();
		affx.translate(x, y);
		return new Area(affx.createTransformedShape(spikeShape));
	}

	/**
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * update the position of the spike
	 */
	public void update() {
		if (this.y > 177 && up) {
			this.y -= 5;
			if (this.y <= 177) {
				up = false;
				down = true;
			}
		} else if (this.y < 647 && down) {
			this.y += 5;
			if (this.y >= 647) {
				up = true;
				down = false;
			}
		}
	}

	public void draw(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(x,  y);
		g.drawImage(image, 0, 0, null);
		Shape shape = getShape();
		g.setTransform(oldTransform);
		g.setColor(new Color(36, 214, 63));
		g.draw(shape.getBounds2D());
	}
}
