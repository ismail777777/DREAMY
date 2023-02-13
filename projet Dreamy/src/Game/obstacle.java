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
 * The obstacles of the game
 * @author ismail El Alout
 *
 */
public class obstacle {

	// Variables
	private final static double OBSTACLE_SIZE = 30;
	private final Image obstacle;
	private double xo;
	private double yo;
	private final Area obstacleShape;
	
	public obstacle() {
		this.obstacle = new ImageIcon(getClass().getResource("resources/ob2.png")).getImage();
		this.xo = 500;
		this.yo = 620;
	    Path2D p = new Path2D.Double();
	    p.moveTo(0, OBSTACLE_SIZE - 20);
	    p.lineTo(0, 10);
	    p.lineTo(OBSTACLE_SIZE-5, 12);
	    p.lineTo(OBSTACLE_SIZE-5, OBSTACLE_SIZE-20);
	    //p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
	    p.lineTo(0, OBSTACLE_SIZE-20);
	    obstacleShape = new Area(p);
	}
	
	public obstacle(double xo, double yo) {
		this.xo = xo;
		this.yo = yo;
		this.obstacle = new ImageIcon(getClass().getResource("resources/ob2.png")).getImage();
		Path2D p = new Path2D.Double();
		p.moveTo(0, OBSTACLE_SIZE-5);
		p.lineTo(0, 50);
		p.lineTo(OBSTACLE_SIZE-5, 20);
		p.lineTo(OBSTACLE_SIZE-5, OBSTACLE_SIZE-5);
		//p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
		p.lineTo(0, OBSTACLE_SIZE-5);
		obstacleShape = new Area(p);
	}
	
	/*public obstacle(double xo, double yo, boolean b) {
		this.xo = xo;
		this.yo = yo;
		Path2D p = new Path2D.Double();
		
		if (b == true) {
			this.obstacle = new ImageIcon(getClass().getResource("resources/aoo1.png")).getImage();
			p.moveTo(0, OBSTACLE_SIZE-5);
			p.lineTo(0, 50);
			p.lineTo(OBSTACLE_SIZE-5, 20);
			p.lineTo(OBSTACLE_SIZE-5, OBSTACLE_SIZE-5);
			//p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
			p.lineTo(0, OBSTACLE_SIZE-5);
			obstacleShape = new Area(p);
		} else {
			this.obstacle = new ImageIcon(getClass().getResource("resources/ob2.png")).getImage();
			p.moveTo(0, OBSTACLE_SIZE-5);
			p.lineTo(0, 50);
			p.lineTo(OBSTACLE_SIZE-5, 20);
			p.lineTo(OBSTACLE_SIZE-5, OBSTACLE_SIZE-5);
			//p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
			p.lineTo(0, OBSTACLE_SIZE-5);
			obstacleShape = new Area(p);
		}
	}*/
	
	public void draw(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(xo,  yo);
		g.drawImage(obstacle, 0, 0, null);
		Shape shape = getShape();
		g.setTransform(oldTransform);
		
		g.setColor(new Color(36, 214, 63));
		g.draw(shape.getBounds2D());
	}

	/**
	 * update the position
	 */
	public void update() {
		this.yo += 10;
	}

	public void refresh() {
		this.xo = 0;
		this.yo = 0;
	}

	/**
	 * 
	 * @return x
	 */
	public double getX() {
		return xo;
	}

	/**
	 * 
	 * @return y
	 */
	public double getY() {
		return yo;
	}

	/**
	 * 
	 * @return the image of the obstacle
	 */
	public Image getObstacleImage() {
		return this.obstacle;
	}

	/**
	 * 
	 * @return the shape of the obstacle
	 */
	public Area getShape() {
		AffineTransform affx = new AffineTransform();
		affx.translate(xo, yo);
		return new Area(affx.createTransformedShape(obstacleShape));
	}	

	/**
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.xo = x;
	}
}
