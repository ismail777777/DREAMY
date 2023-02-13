package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import javax.swing.ImageIcon;

public class Personnage {

	public static final double PERSO_SIZE = 60;
	private double x;
	private double y;
	private boolean up, down;
	private boolean Walk = false;
	private boolean jump = false;
	private boolean fall = false;
	private int sprite = 1;
	private int spriteJump = 1;
	private int counter;
	private int jumpCounter;
	private int fallCounter;

	private Image image;
	private final Area persoShape;
	
	public Personnage(String str) {
		this.setImage(new ImageIcon(getClass().getResource(str)).getImage());
		this.up = true;
		this.down = false;
		Path2D p = new Path2D.Double();
		p.moveTo(0, PERSO_SIZE);
		p.lineTo(0, 110);
		p.lineTo(PERSO_SIZE , 5);
		p.lineTo(PERSO_SIZE , PERSO_SIZE);
		//p.lineTo(OBSTACLE_SIZE - 5, OBSTACLE_SIZE - 13);
		p.lineTo(0, PERSO_SIZE);
		persoShape = new Area(p);
	}

	public void changePosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g) {
		AffineTransform oldTransform = g.getTransform();
		g.translate(x,  y);
		g.drawImage(getImage(), 0, 0, null);
		Shape shape = getShape();

		g.setTransform(oldTransform);
		
		g.setColor(new Color(36, 214, 63));
		g.draw(shape.getBounds2D());
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public boolean isWalking() {
		return this.Walk;
	}
	
	public boolean isJumping() {
		return this.jump;
	}

	public boolean isFalling() {
		return this.fall;
	}
	
	public int getSpriteJump() {
		return this.spriteJump;
	}

	public void setX(double nx) {
		this.x = nx;
	}

	public void setY(double ny) {
		this.y = ny;
	}

	public void setWalk(boolean walk) {
		this.Walk = walk;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void addY(double ny) {
		this.y += ny;
	}

	public void addX(double nx) {
		this.x += nx;
	}

	public void Walk(String name, int frequency) {
		String str;
		
		if(this.Walk == false || this.getX() <= 0){
			str = "resources/" + name + ".png";
		} else {
			this.counter++;
			if(this.counter / frequency == 0){
				str = "resources/" + name + ".png";
			} else{
				str = "resources/" + name + "Walk.png";
			}
			if(this.counter == 2 * frequency){this.counter = 0;}
		}
		//Affichage de l'image du personnage
		this.setImage(new ImageIcon(getClass().getResource(str)).getImage());
	}

	public void spritesHandler(String name) {
		String str = "";
		if (this.Walk == false || this.getX() <= 0) {
			str = "resources/" + name + ".png";
		} else {
			switch (this.sprite) {
			case 1:
				this.sprite++;
				str = "resources/" + name + "Sprite1.png";
				break;
			case 2:
				this.sprite++;
				str = "resources/" + name + "Sprite2.png";
				break;
			case 3:
				this.sprite++;
				str = "resources/" + name + "Sprite3.png";
				break;
			case 4:
				this.sprite++;
				str = "resources/" + name + "Sprite4.png";
				break;
			case 5:
				this.sprite++;
				str = "resources/" + name + "Sprite5.png";
				break;
			case 6:
				this.sprite++;
				str = "resources/" + name + "Sprite6.png";
				break;
			}
		}
		//Affichage de l'image du personnage
		if (this.sprite == 7) {
			this.sprite = 1;
		}
		this.setImage(new ImageIcon(getClass().getResource(str)).getImage());	
	}

	public void jump(String name) {
		String str = "";
		if (this.jump == true && this.getX() > 0) {
			switch (this.spriteJump) {
			case 1:
				this.spriteJump++;
				str ="resources/" + name + "Sprite1.png";
				break;
			case 2:
				this.spriteJump++;
				str ="resources/" + name + "Sprite2.png";
				break;
			case 3:
				this.spriteJump++;
				str ="resources/" + name + "Sprite3.png";
				break;
			}
			if (this.spriteJump == 4) {
				this.addY(1.8);
			} else {
				this.setImage(new ImageIcon(getClass().getResource(str)).getImage());
			}	
		}
		//Affichage de l'image du personnage
		if (this.spriteJump == 4) {
			this.jump = false;
			this.spriteJump = 1;
		}
	}
	
	public Area getShape() {
		AffineTransform afx = new AffineTransform();
		afx.translate(x, y);
		return new Area(afx.createTransformedShape(persoShape));
	}

	public int getJumpCounter() {
		return jumpCounter;
	}

	public void setJumpCounter(int jumpCounter) {
		this.jumpCounter = jumpCounter;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getFallCounter() {
		return fallCounter;
	}

	public void setFallCounter(int fallCounter) {
		this.fallCounter = fallCounter;
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int)getX() + 60 - 5, (int)getY() + 10, 5, 112 - 20);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int)getX() + 60, (int)getY() + 112 - 25, 60, 30);
	}

	public void update() {
		if (this.y > 0 && up) {
			this.y -= 5;
			if (this.y <= 0) {
				up = false;
				down = true;
			}
		} else if (this.y < 500 && down) {
			this.y += 5;
			if (this.y >= 500) {
				up = true;
				down = false;
			}
		}
	}
	/*public boolean frontContact(Box box) {
		if (this.x + 60 < box.getX() || this.x + 60 > box.getX() + 5 || this.y + 112 <= box.getY() || this.y >= box.getY() + 100) {
			return false;
		} else {
			return true;
		}
	}

	public boolean backContact(Box box) {
		if (this.x > box.getX() + 56 || this.x + 60 < box.getX() + 56 - 5 || this.y + 112 <= box.getY() || this.y >= box.getY() + 100) {
			return false;
		} else {
			return true;
		}
	}

	public boolean upperContact(Box box) {
		if (this.x + 60 < box.getX() + 5 || this.x > box.getX() + 56 - 5 || this.y < box.getY() + 100 || this.y > box.getY() + 100 + 5) {
			return false;
		} else {
			return true;
		}
	}

	public boolean lowerContact(Box box) {
		if (this.x + 60 < box.getX() + 5 || this.x > box.getX() + 56 - 5 || this.y + 112 < box.getY() || this.y + 112 > box.getY() + 5) {
			return false;
		} else {
			return true;
		}
	}

	public boolean near(Box box) {
		if ((this.x > box.getX() - 10 && this.x < box.getX() + 56 + 10) || (this.x + 60 > box.getX() - 10 && this.x + 60 < box.getX() + 56 + 10)) {
			return true;
		} else {
			return false;
		}
	}*/
}
