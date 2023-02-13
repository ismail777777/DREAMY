package Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import javax.swing.ImageIcon;

public class platform {

	private final static double OBSTACLE_SIZE = 30;
	private final Image platform;
	private double x;
	private double y;
	private final Area platformShape;
	private boolean up, down;
	
	public platform(String str) {
		this.platform = new ImageIcon(getClass().getResource("resources/" + str + ".png")).getImage();
		this.x = 500;
		this.y = 300;
	    Path2D p = new Path2D.Double();
	    p.moveTo(0, OBSTACLE_SIZE - 20);
	    p.lineTo(0, 10);
	    p.lineTo(OBSTACLE_SIZE-5, 12);
	    p.lineTo(OBSTACLE_SIZE-5, OBSTACLE_SIZE-20);
	    p.lineTo(0, OBSTACLE_SIZE-20);
	    platformShape = new Area(p);
	}
	
	public platform(double x, double y, String str) {
		this.x = x;
		this.y = y;
		this.platform = new ImageIcon(getClass().getResource("resources/" +  str + ".png")).getImage();
		up = true;
		down = false;
		Path2D p = new Path2D.Double();
		p.moveTo(0, OBSTACLE_SIZE-5);
		p.lineTo(0, 50);
		p.lineTo(OBSTACLE_SIZE-5, 20);
		p.lineTo(OBSTACLE_SIZE-5, OBSTACLE_SIZE-5);
		//p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
		p.lineTo(0, OBSTACLE_SIZE-5);
		platformShape = new Area(p);
	}

	public void draw(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(x, y);
		g.drawImage(platform, 0, 0, null);
		g.setTransform(oldTransform);
		g.setColor(new Color(36, 214, 63));
	}

	/**
	 * update the position of the platform
	 * 
	 */
	public void update() {
		if (this.y > 0 && up) {
			this.y -= 5;
			if (this.y <= 0) {
				up = false;
				down = true;
			}
		} else if (this.y < 470 && down) {
			this.y += 5;
			if (this.y >= 470) {
				up = true;
				down = false;
			}
		}
	}

	public void refresh() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @return up
	 */
	public boolean getUp() {
		return this.up;
	}

	/**
	 * 
	 * @return down
	 */
	public boolean getDown() {
		return this.down;
	}

	/**
	 * 
	 * @return the shape of the platform
	 */
	public Area getShape() {
		AffineTransform affx = new AffineTransform();
		affx.translate(x, y);
		return new Area(affx.createTransformedShape(platformShape));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)getX(), (int)getY() + 220, 500, 35);
	}

	/**
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
}
