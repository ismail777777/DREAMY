package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import javax.swing.ImageIcon;

public class Personnage2 {

	public static final double PERSO_SIZE = 60;
	private double x;
	private double y;
	private boolean Walk = false;
	private boolean jump = false;
	private int sprite = 1;
	private int spriteJump = 1;
	private int counter;
	private int jumpCounter;
	private Image image;
	private boolean frontContact;
	private boolean upperContact;
	private boolean lowerContact;
	private final Area persoShape;
	
	public Personnage2() {
		this.setImage(new ImageIcon(getClass().getResource("resources/avatar222.png")).getImage());
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
			str = "resources/" + name + "22.png";
		} else {
			switch (this.sprite) {
			case 1:
				this.sprite++;
				str = "resources/" + name + "1.png";
				break;
			case 2:
				this.sprite++;
				str = "resources/" + name + "2.png";
				break;
			case 3:
				this.sprite++;
				str = "resources/" + name + "3.png";
				break;
			case 4:
				this.sprite++;
				str = "resources/" + name + "4.png";
				break;
			case 5:
				this.sprite++;
				str = "resources/" + name + "5.png";
				break;
			case 6:
				this.sprite++;
				str = "resources/" + name + "6.png";
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
				str ="resources/" + name + "sprite1.png";
				break;
			case 2:
				this.spriteJump++;
				str ="resources/" + name + "sprite2.png";
				break;
			case 3:
				this.spriteJump++;
				str ="resources/" + name + "sprite3.png";
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

	public boolean frontContact(obstacle obstacle) {
			if (this.getX() + 40 < obstacle.getX() || this.getX() + 40 > obstacle.getX() + 5) {
				return false;
			} else {
				return true;
			}
	}

	public boolean lowerContact(obstacle obstacle) {
		if (this.x + 40 < obstacle.getX() + 5 || this.x > obstacle.getX() + 30 - 5 || this.y + 60 < obstacle.getY() || this.y + 60 > obstacle.getY() + 5) {
			return false;
		} else{
			return true;
		}
	}

	public boolean upperContact(obstacle obstacle) {
		if (this.x + 40 < obstacle.getX() + 5 || this.x > obstacle.getX() + 30 - 5 || this.y < obstacle.getY() + 30 || this.y > obstacle.getY() + 30) {
			return false;
		} else{
			return true;
		}
	}

	public boolean near(obstacle obstacle) {
		if ((this.x > obstacle.getX() - 10 && this.x < obstacle.getX() + 178 + 10) || (this.x + 60 > obstacle.getX() - 10 && this.x + 60 < obstacle.getX() + 178 +10)) {
			return true;
		} else {
			return false;
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
}
