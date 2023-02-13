package Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Boosters
 * @author ismail El Alout
 *
 */
public class Boosters {
	// Variables

	private final static double BOOSTERS_SIZE = 40;
	private Image booster;
	private String boosterName;
	// coordinates
	private double xb;
	private double yb;
	private boolean up, down;
	private final Area boosterShape;
	
	/**
	 * constructor
	 */
	public Boosters() {
		this.booster = new ImageIcon(getClass().getResource("resources/ob2.png")).getImage();
		this.xb = 500;
		this.yb = 620;
		this.up = true;
		this.down = false;
	    Path2D p = new Path2D.Double();
	    p.moveTo(0, BOOSTERS_SIZE - 15);
	    p.lineTo(0, 10);
	    p.lineTo(BOOSTERS_SIZE-5, 12);
	    p.lineTo(BOOSTERS_SIZE-5, BOOSTERS_SIZE-15);
	    //p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
	    p.lineTo(0, BOOSTERS_SIZE-15);
	    boosterShape = new Area(p);
	}
	
	public Boosters(double x, double y) {
		this.xb = x;
		this.yb = y;
		List<String> names = new ArrayList<>();
		names.add("almond");
		names.add("cashew");
		names.add("walnut");
		names.add("pecan");
		this.up = true;
		this.down = false;
		int index = new Random().nextInt(names.size());
	    String random = names.get(index);
	    String str = "resources/" + random + ".png";
		this.booster = new ImageIcon(getClass().getResource(str)).getImage();
		this.boosterName = random;
		Path2D p = new Path2D.Double();
		p.moveTo(0, BOOSTERS_SIZE);
		p.lineTo(0, 8);
		p.lineTo(BOOSTERS_SIZE + 5 , 7);
		p.lineTo(BOOSTERS_SIZE  + 5, BOOSTERS_SIZE);
		//p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
		p.lineTo(0, BOOSTERS_SIZE);
		boosterShape = new Area(p);
	}
	
	public void draw(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(xb,  yb);
		g.drawImage(booster, 0, 0, null);
		Shape shape = getShape();
		g.setTransform(oldTransform);
		
		g.setColor(new Color(36, 214, 63));
		g.draw(shape.getBounds2D());
	}
	
	/**
	 * update the position of the boosters
	 * 
	 */
	public void update() {
		if (this.yb > 0 && up) {
			this.yb -= 5;
			if (this.yb <= 0) {
				up = false;
				down = true;
			}
		} else if (this.yb < 470 && down) {
			this.yb += 5;
			if (this.yb >= 470) {
				up = true;
				down = false;
			}
		}
	}
	
	public void refresh() {
		this.xb = 0;
		this.yb = 0;
	}

	/**
	 * 
	 * @return xb
	 */
	public double getX() {
		return xb;
	}

	/**
	 * 
	 * @return yb
	 */
	public double getY() {
		return yb;
	}

	/**
	 * 
	 * @return the shape of the booster
	 */
	public Area getShape() {
		AffineTransform affx = new AffineTransform();
		affx.translate(xb, yb);
		return new Area(affx.createTransformedShape(boosterShape));
	}

	/**
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.xb = x;
	}

	public void remove() {
		this.booster = null;
	}

	public String getBoosterName() {
		return this.boosterName;
	}
}
