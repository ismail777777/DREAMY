package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import Store.Coins;
import Store.StoreSwing;
import Sound.SoundHandler;

public class GameScreen2 extends JComponent {

	private final int FPS = 180;
	private final int TargetTime = 1000000000/FPS;
	
	private int width;
	private int height;
	private double x_background1 = 0;
	private double x_background2 = 1500;
	private double xPos = -1;
	private double ySol = 560;
	private double top = 0;
	private double dx = 0;
	private double diff1 = 0;
	private double diff2 = 2*225985;
	private Thread t;
	private boolean running = true;
	private boolean running2 = false;
	private boolean falling = false;
	private boolean stop = false;
	private List<platform> platforms;
	private List<Spike> spikes;
	private List<obstacle> bombs;
	private List<Boosters> boosters;
	private Energy energy = new Energy();
	private Score score = new Score();
	private SoundHandler soundHandler;
	private Graphics2D g;
	private BufferedImage image;
	private gameControls gameControls;
	
	private Personnage perso;

	public void start() {
		width = getWidth();
		height = getHeight();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					long cTime = System.nanoTime();
					drawBackground(1);
					drawGame();
					render();
					long time = System.nanoTime() - cTime;
					if (time < TargetTime) {
						long sleep = (TargetTime - time) / 1000000;
						sleep(sleep);
					}
				}
				while (running2) {
					long cTime = System.nanoTime();
					drawBackground(2);
					drawGame();
					render();
					long time = System.nanoTime() - cTime;
					if (time < TargetTime) {
						long sleep = (TargetTime - time) / 1000000;
						sleep(sleep);
					}
				}
			}
		});
		init();
		initKeyboard();
		t.start();
	}

	private void init() {
		perso = new Personnage("resources/avatar22.png");
		perso.changePosition(280, 560);
		this.bombs = bombGenerator();
		//this.boosters = boostersGenerator();
		this.platforms = platformsGenerator();
	}

	private void initKeyboard() {
		gameControls = new gameControls();
		requestFocus();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_D) {
					gameControls.setKey_move(true);
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameControls.setKey_jump(true);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_D) {
					gameControls.setKey_move(false);
					perso.setWalk(false);
					setX(0);
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameControls.setKey_jump(false);
					setX(0);
					//perso.setJump(false);
				}
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (running || running2) {
					if (gameControls.isKey_move()) {
						if (xPos == -1) {
							setXpos(0);
							setX1(0);
							setX2(1500);
						}
						if (stop) {
							perso.setWalk(false);
							setX(0);
							/*if (perso.isJumping()) {
								perso.setWalk(true);
								setX(1);
								stop = false;
								setYsol(510);
							}*/
						} else {
							if (StoreSwing.action1.isValid()) {
								perso.setWalk(true);
								setX(1.5);
							} else {
								perso.setWalk(true);
								setX(1);
							}
						}
					} else if (gameControls.isKey_jump()) {
						if (xPos == -1) {
							setXpos(0);
							setX1(0);
							setX2(1500);
						}
						if (stop) {
							stop = false;
							setX(1);
							setYsol(460);
						}
						perso.setJump(true);
						//setX(1);
						//perso.addY(-1.8);
					}
					for (int i = 0; i < bombs.size(); i++) {
						collisionDetector(bombs.get(i));
					}
					for (int i = 0; i < spikes.size(); i++) {
						spikeCollision(spikes.get(i));
					}
					for (int i = 0; i < boosters.size(); i++) {
						Area area = new Area(perso.getShape());
						area.intersect(boosters.get(i).getShape());
						if (!area.isEmpty()) {
							collect(boosters.get(i));
							boosters.remove(boosters.get(i));
						}
					}
					/*for (int i = 0; i < boxes.size(); i++) {
						boxContact(boxes.get(i));
					}
					/*for (int i = 0; i<obstacles.size(); i++) {
						Obstacle obstacle = obstacles.get(i);
						if (obstacle != null) {
							obstacle.update();
						}
					}*/
					sleep(5);
				}
			}
		}).start();
	}
	
	private void deplacementBackground() {
		if (xPos >= 0) {
			this.xPos += dx;
			this.x_background1 -= 10*dx;
			this.x_background2 -= 10*dx;
		}
		if (this.x_background1 == -1500) {
			this.x_background1 = 1500;
		} else if (this.x_background2 == -1500) {
			this.x_background2 = 1500;
		} else if (this.x_background1 == 1500) {
			this.x_background1 = -1500;
		} else if (this.x_background2 == 1500) {
			this.x_background2 = -1500;
		}
	}

	public void Jump(String name, int jumpHeight) {
		String str;
		perso.setJumpCounter(perso.getJumpCounter() + 1);
		if (perso.getJumpCounter() <= 10) {
			if (perso.getY() > this.getTop()) {
				perso.setY(perso.getY() - jumpHeight);
			} else {
				perso.setJumpCounter(11);
			}
			str ="resources/" + name + "Sprite1.png";
		} else if (perso.getY() + this.getTop() < this.getYsol()) {
			perso.setY(perso.getY()+5); 	
			str ="resources/" + name + "Sprite2.png";
		} else {
			str ="resources/" + name + "Sprite3.png";
			perso.setJump(false);
			perso.setJumpCounter(0);
		}
		perso.setImage(new ImageIcon(getClass().getResource(str)).getImage());
	}

	public void Fall(String str) {
		String name = "";
		if (perso.getY() < 560) {
			this.setYsol(this.getYsol() + 20);
			perso.setY(perso.getY() + 5);
			name = "resources/" + str + "Sprite2.png";
		} else if (perso.getY() == 560) {
			name = "resources/" + str + "Sprite3.png";
			perso.setFall(false);
			perso.setFallCounter(0);
		}
		//perso.setImage(new ImageIcon(getClass().getResource(name)).getImage());
		if (perso.getY() >= 560) {
			this.setYsol(560);
			perso.setFall(false);
			//perso.spritesHandler("avatar11");
		}
		perso.setImage(new ImageIcon(getClass().getResource(name)).getImage());
	}

	public void replace(obstacle ob) {
		ob.setX(ob.getX() - 10*dx);
	}
	
	public void replaceBooster(Boosters b) {
		b.setX(b.getX() - 10*dx);
		b.update();
	}

	public void replacePlatform(platform platform) {
		platform.setX(platform.getX() - 10*dx);
		platform.update();
	}

	public void replaceSpike(Spike spike) {
		if (diff1 < 225985) {
			spike.setX(spike.getX() + 2);
			diff1 += 1;
		} else if (diff1 >= 225985) {
			spike.setX(spike.getX() - 2);
			diff2 -= 1;
			if (diff2 == 225985) {
				diff2 = 2*225985;
				diff1 = 0;
			}
		}
		spike.setX(spike.getX() - 10*dx);
		spike.update();
	}

	private List<obstacle> bombGenerator() {
		List bombs = new ArrayList<>();
		int i = 1000;
		for (int j = 0; j < 1000; j++) {
			double rand = i + Math.random()*i;
			obstacle ob = new obstacle(rand, 620);
			i += 750;
			bombs.add(ob);
		}	
	    return bombs;
	}

	/*private List<Boosters> boostersGenerator() {
		List boosters = new ArrayList<>();
		int i = 1354;
		for (int j = 0; j < 1000; j++) {
			double rand = i + Math.random()*i;
			Boosters booster = new Boosters(rand + 450, 200);
			i += 1800;
			boosters.add(booster);
		}	
	    return boosters;
	}*/

	private List<platform> platformsGenerator() {
		List platforms = new ArrayList<>();
		this.spikes = new ArrayList<>();
		this.boosters = new ArrayList<>();
		List<String> names = new ArrayList<>();
		names.add("o1");
		//names.add("o2");
		//names.add("o3");
		//names.add("o4");
		//names.add("o5");
		int index = new Random().nextInt(names.size());
	    String randomString = names.get(index);
		int i = 1300;
		for (int j = 0; j < 1000; j++) {
			double rand = i + Math.random()*i;
			platform platform = new platform(rand, 180, randomString);
			Spike spike = new Spike(rand ,357);
			Boosters booster = new Boosters(rand + 430, 200);
			i += 1800;
			platforms.add(platform);
			spikes.add(spike);
			boosters.add(booster);
		}	
	    return platforms;
	}

	private void collisionDetector(obstacle obstacle) {
		Area area = new Area(perso.getShape());
		area.intersect(obstacle.getShape());
		if (!area.isEmpty()) {
			energy.decrease();
			//System.out.println("COLLISION !!!!");
			bombs.remove(obstacle);
			if (energy.getValue() <= 0) {
				score.show(g);
				running = false;
			}
		}
	}

	private void spikeCollision(Spike spike) {
		Area area = new Area(perso.getShape());
		area.intersect(spike.getShape());
		if (!area.isEmpty()) {
			energy.decrease();
			if (energy.getValue() <= 0) {
				score.show(g);
				running = false;
				running2 = false;
				StoreSwing.coins.setValue(StoreSwing.coins.getValue() + 1000);
				try {
			         FileOutputStream fileOut =
			         new FileOutputStream("tmp/coin.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(StoreSwing.coins);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in /tmp/coins.ser");
			      } catch (IOException i) {
			         i.printStackTrace();
			      }
			}
		}
	}

	private int collision(List<platform> platforms) {
		int s = -1;
		int i = 0;
		while (i < platforms.size()) {
			if (perso.getBoundsBottom().intersects(platforms.get(i).getBounds())) {
				perso.setDown(platforms.get(i).getDown());
				perso.setUp(platforms.get(i).getUp());
				s = i;
				break;
			}
			i++;
		}
		return s;
	}

	private void collect(Boosters booster) {
		if (booster.getBoosterName().equals("almond")) {
			this.energy.increase(1);
			drawScore(1);
		} else if (booster.getBoosterName().equals("pecan")) {
			this.energy.increase(3);
			drawScore(3);
		} else if (booster.getBoosterName().equals("cashew")) {
			this.energy.increase(2);
			drawScore(2);
		} else if (booster.getBoosterName().equals("walnut")) {
			this.energy.increase(4);
			drawScore(4);
		}
	}
	
	private void setX(double x) {
		this.dx = x;
	}
	
	private void setXpos(double x) {
		this.xPos = x;
	}

	private void setX1(double x) {
		this.x_background1 = x;
	}

	private void setX2(double x) {
		this.x_background2 = x;
	}
	
	private void setYsol(double y) {
		this.ySol = y;
	}
	
	public double getYsol() {
		return this.ySol;
	}

	public double getTop() {
		return this.top;
	}	

	private void drawBackground(int i) {
		System.out.println(score.getValue());
		if (score.getValue() < 200) {
			running = true;
			running2 = false;
		} else if (score.getValue() >= 200 && score.getValue() < 450) {
			running = false;
			running2 = true;
		}
		if (i == 1) {
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, width, height);
			deplacementBackground();
			g.drawImage(new ImageIcon(getClass().getResource("resources/bgf.png")).getImage(), (int)this.x_background1, 0, null);
			g.drawImage(new ImageIcon(getClass().getResource("resources/bgf.png")).getImage(), (int)this.x_background2, 0, null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Black", 30, 30));
			if (this.xPos == -1) {
				g.drawString("Score : " + 0, 650, 90);
			} else {
				score.setValue((int) this.xPos);
				g.drawString("Score : " + (int) this.xPos, 650, 90);
			}
			if (StoreSwing.action1.isValid()) {
				Font myFont = new Font("Arial Bold", Font.ITALIC, 25);
				g.setFont(myFont);
				g.drawString("Sprint boost activated", 1150, 70);
			} else if (StoreSwing.action2.isValid()) {
				Font myFont = new Font("Arial Bold", Font.ITALIC, 25);
				g.setFont(myFont);
				g.drawString("High jump boost activated", 1050, 70);
			} else if (StoreSwing.action3.isValid()) {
				
			} else if (StoreSwing.action4.isValid()) {
				
			}
		} else {
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, width, height);
			deplacementBackground();
			g.drawImage(new ImageIcon(getClass().getResource("resources/bg4.png")).getImage(), (int)this.x_background1, 0, null);
			g.drawImage(new ImageIcon(getClass().getResource("resources/bg4.png")).getImage(), (int)this.x_background2, 0, null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Black", 30, 30));
			if (this.xPos == -1) {
				g.drawString("Score : " + 0, 650, 90);
			} else {
				score.setValue((int) this.xPos);
				g.drawString("Score : " + score.getValue(), 650, 90);
			}
			if (StoreSwing.action1.isValid()) {
				Font myFont = new Font("Arial Bold", Font.ITALIC, 25);
				g.setFont(myFont);
				g.drawString("Sprint boost activated", 1150, 70);
			} else if (StoreSwing.action2.isValid()) {
				Font myFont = new Font("Arial Bold", Font.ITALIC, 25);
				g.setFont(myFont);
				g.drawString("High jump boost activated", 1050, 70);
			} else if (StoreSwing.action3.isValid()) {
				
			} else if (StoreSwing.action4.isValid()) {
				
			}
		}
	}

	private void drawScore(int i) {
		if (i == 1) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Black", 30, 30));
			g.drawString(" Energy boost +5", 500, 300);
		} else if (i == 2) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Black", 30, 30));
			g.drawString(" Energy boost +10", 500, 300);
		} else if (i == 3) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Black", 30, 30));
			g.drawString(" Energy boost +15", 500, 300);
		} else if (i == 4) {
		    g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Black", 30, 30));
			g.drawString(" Energy boost +20", 500, 300);
		}
	}
	
	private void drawGame() {
		//perso.Walk("avatar11", 10);
		if (this.xPos >= 0) {
			for (int i = 0; i < this.bombs.size(); i++) {
				replace(this.bombs.get(i));
				this.bombs.get(i).draw(g);
			}
			/*for (int i = 0; i < this.boosters.size(); i++) {
				replaceBooster(this.boosters.get(i));
				this.boosters.get(i).draw(g);
			}*/
			for (int i = 0; i < this.platforms.size(); i++) {
				replacePlatform(this.platforms.get(i));
				replaceSpike(this.spikes.get(i));
				this.spikes.get(i).draw(g);
				this.platforms.get(i).draw(g);
			}
			for (int i = 0; i < this.boosters.size(); i++) {
				replaceBooster(this.boosters.get(i));
				this.boosters.get(i).draw(g);
			}
			/*for (int i = 0; i < this.spikes.size(); i++) {
				replaceSpike(this.spikes.get(i));
				this.spikes.get(i).draw(g);
			}*/
			if (collision(this.platforms) != -1) {
				this.setYsol(this.platforms.get(collision(this.platforms)).getY());
				perso.update();
				//this.setYsol(this.jumpers.get(i).getY());
			} else if (collision(this.platforms)== -1 && this.getYsol() != 560 && !perso.isJumping()) {
				perso.setFall(true);
			} else {
				this.setYsol(560);
			}
		}
		perso.spritesHandler("avatar22");
		if (perso.isJumping() && StoreSwing.action2.isValid()) {
			Jump("avatar222", 20);
		} else if (perso.isJumping() && !StoreSwing.action2.isValid()) {
			Jump("avatar222", 15);
		}
		if (perso.isFalling()) {
			Fall("avatar222");
		}
		perso.draw(g);
		sleep(15);
		this.energy.draw(g);
	}
	
	private void render() {
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}

	private void sleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException ex) {
			System.err.println(ex);
		}
	}

}
