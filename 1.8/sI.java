// @Author	: Jordan Fisher
// @Version	: 1.7

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class sI extends JFrame {
	int frameX = 400;
	int frameY = 400;
	
	Image craft;
	ImageIcon craftii;
	int screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screenY = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	int posX;
	int posY;
	int increment = 5;
	
	int countMissiles =0;
	int lives = 3;
	int timer = 25;
	int enemyMovementTimer = 100;
	int inGame = 1;
	int rows = 10;
	int numberPerRow = 10;
	int NPRAR = numberPerRow * rows;
	int winlose = 0;
	int offset = 7;
	int direction = 1;
	int numberOfBarriers = 5;
	int barrierY;
	int shotFiredByEnemies = 0;
	int frequencyOfBullets = 60;
	int showPercentages = 0;
	
	ArrayList missiles = new ArrayList();
	ArrayList enemies = new ArrayList();
	ArrayList barry = new ArrayList();
	ArrayList bullet = new ArrayList();
	
	public sI() {
		try {
			craft = Toolkit.getDefaultToolkit().getImage("images\\craft.png");
			craftii = new ImageIcon(craft);
		} catch (Exception e) {
			System.out.println("Error finding files. --------------");
			System.out.println(e);
		}
		posX = (frameX / 2) - (craftii.getIconWidth() / 2);
		posY = frameY - 40;
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameX, frameY);
		setLocation((screenX / 2) - (frameX / 2), (screenY / 2) - (frameY / 2));
		setResizable(false);
		setBackground(Color.BLACK);
		drawEnemies();
		updatePanel t = new updatePanel();
		t.start();
		updatePanelTwo t2 = new updatePanelTwo();
		t2.start();
		buildBarriers();
		randomBulletThread t3 = new randomBulletThread();
		t3.start();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					if (posX >= frameX - 10 - craftii.getIconWidth()) {} else {
						posX += increment;
						repaint();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (posX <= 10) {} else {
						posX -= increment;
						repaint();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					missiles.add(new Missile(posX + (craftii.getIconWidth() / 2), posY + (craftii.getIconHeight() / 2)));
					countMissiles++;
				}
				if (e.getKeyCode() == KeyEvent.VK_R) {
					System.out.println("-------------------");
					System.out.println("Craft : (" + posX + ", " + posY + ")");
					System.out.println(missiles.size() + " missiles active.");
					System.out.println(countMissiles + " missiles fired.");
					System.out.println(lives + " lives.");
					System.out.println(enemies.size() + " enemies left.");
					System.out.println(barry.size() + " barriers left.");
					System.out.println(shotFiredByEnemies + " shots fired by enemies.");
					System.out.println(bullet.size() + " active shots fired.");			
				}
			}
		});
		setVisible(true);
	}
	public static void main(String[] args) {
		sI frame = new sI();
		System.out.println("Jordan Fisher - Space Invaders!");
	}
	public void buildBarriers() {
		double a = frameX / numberOfBarriers;
		int h = (int) a;
		int stat = h;
		barrierY = posY - 40;
		for (int i = 0; i < numberOfBarriers; i++) {
			barry.add(new Barriers(stat - 60 , posY - 40, 1));
			stat = stat + h;
		}
	}
	public int[] coordinatesX() {
		int[] XC = new int[NPRAR];
		double a = frameX / numberPerRow;
		int TX = (int) a;
		int ST = (TX / 2) / 2 - offset;
		for (int i = 0; i < NPRAR; i++) {
			if (ST > frameX - (offset * numberPerRow)) {
				ST = (TX / 2) / 2 - offset;
			}
			XC[i] = ST + offset;
			ST += TX - offset;
		}
		return XC; 
	}
	public void drawEnemies() {
		int tempX = frameX / 10;
		int startX = 10;
		int[] xVals = coordinatesX();
		Random r = new Random();
		int yAxis = 50;
		int u;
		int z = r.nextInt(2) + 1;
		for (int i = 0; i < NPRAR; i++) {
			u = i % numberPerRow;
			if (u == 0 && i != 0) {
				yAxis += 20;
				z = r.nextInt(2) + 1;
			}
			enemies.add(new Enemy(xVals[i], yAxis, z, frameX));
		}
	}
	public void paint(Graphics g) {
		g.clearRect(0, 0, frameX, frameY);
		g.setColor(Color.GREEN);
		if (inGame == 1) {
			g.drawImage(craft, posX, posY, this);
			g.drawLine(0, frameY - 25, frameX, frameY - 25);
			g.drawString("Aliens: " + enemies.size(), frameX - 70, frameY - 10);
			g.drawString("Lives: " + lives, 10, frameY - 10);
			g.drawString("Jordan Fisher", 10, 40);
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = (Enemy) enemies.get(i);
				g.drawImage(e.getImage(), e.getX(), e.getY(), this);
			}
			for (int i = 0; i < missiles.size(); i++) {
				Missile m = (Missile) missiles.get(i);
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
			for (int i = 0; i < barry.size(); i++) {
				Barriers b = (Barriers) barry.get(i);
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
			for (int i = 0; i < bullet.size(); i++) {
				Bullets b = (Bullets) bullet.get(i);
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		} else {
			Image gameover = Toolkit.getDefaultToolkit().getImage("images\\gameover.png");
			ImageIcon gameoverii = new ImageIcon(gameover);
			g.drawImage(gameover, (frameX / 2) - (gameoverii.getIconWidth() / 2), 100, this);
			String message = "";
			if (winlose == 1) {
				message = "You win!";
			}
			if (winlose == 2) {
				message = "You lose.";
			}
			g.drawString(message, frameX / 2 - 30, frameY / 2 - 30);
		}
	}
	public class updatePanel extends Thread implements Runnable {
		public void run() {
			for (int i = 1; i > 0; i++) {
				try {
					Thread.sleep(timer);
				} catch (Exception e) {
					System.out.println(e);
				}
				for (int a = 0; a < missiles.size(); a++) {
					Missile m = (Missile) missiles.get(a);
					m.move();
					boolean f = m.isVisible();
					if (f == false) {
						missiles.remove(a);
					}
				}
				for (int a = 0; a < bullet.size(); a++) {
					Bullets b = (Bullets) bullet.get(a);
					boolean f = b.getVisible();
					if (f == false) {
						bullet.remove(a);
					}
				}
				for (int k = 0; k < enemies.size(); k++) {
					Enemy e = (Enemy) enemies.get(k);
					Rectangle rect1 = new Rectangle(e.getX(), e.getY(), 20, 10);
					for (int n = 0; n < missiles.size(); n++) {
						Missile m = (Missile) missiles.get(n);
						Rectangle rect2 = new Rectangle(m.getX(), m.getY(), 2, 6);
						if (rect1.intersects(rect2)) {
							enemies.remove(k);
							missiles.remove(n);
						}
					}
				}
				for (int l = 0; l < barry.size(); l++) {
					Barriers b = (Barriers) barry.get(l);
					Rectangle rect1 = new Rectangle(b.getX(), b.getY(), 40, 20);
					for (int n = 0; n < missiles.size(); n++) {
						Missile m = (Missile) missiles.get(n);
						Rectangle rect2 = new Rectangle(m.getX(), m.getY(), 2, 6);
						if (rect1.intersects(rect2)) {
							int u = b.getCondition();
							if (u == 5) {
								barry.remove(l);
							} else {
								barry.set(l, new Barriers(b.getX(), b.getY(), (b.getCondition() + 1)));
							}
							missiles.remove(n);
						}
					}
				}
				for (int p = 0; p < bullet.size(); p++) {
					Bullets b = (Bullets) bullet.get(p);
					Rectangle rect1 = new Rectangle(b.getX(), b.getY(), 2, 6);
					Rectangle rect3 = new Rectangle(posX, posY, craftii.getIconWidth(), craftii.getIconHeight());
					if (rect1.intersects(rect3)) {
						lives -= 1;
						bullet.remove(p);
						if (lives == 0) {
							inGame = 0;
							winlose = 2;
						}
					}
					for (int n = 0; n < barry.size(); n++) {
						Barriers f = (Barriers) barry.get(n);
						Rectangle rect2 = new Rectangle(f.getX(), f.getY(), 40, 20);
						if (rect1.intersects(rect2)) {
							int u = f.getCondition();
							if (u == 5) {
								barry.remove(n);
							} else {
								barry.set(n, new Barriers(f.getX(), f.getY(), (f.getCondition() + 1)));
							}
							bullet.remove(p);
						}
					}
				}
				for (int t = 0; t < bullet.size(); t++) {
					Bullets b = (Bullets) bullet.get(t);
					b.move();
				}
				if (enemies.size() == 0) {
					inGame = 0;
					winlose = 1;
				}
				repaint();
			}
		}
	}
	public void moveAllEnemiesDown() {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = (Enemy) enemies.get(i);
			e.moveDown();
		}
		if (direction == 1) {
			direction = 2; 
		} else {
			direction = 1;
		}
	}
	public class updatePanelTwo extends Thread implements Runnable {
		public void run() {
			int p = 0;
			for (int i = 1; i > 0; i++) {
				try {
					Thread.sleep(enemyMovementTimer);
				} catch (Exception e) {
					System.out.println(e);
				}
				for (int a = 0; a < enemies.size(); a++) {
					Enemy e = (Enemy) enemies.get(a);
					if (direction == 1) {
						e.moveRight();
						if (e.getX() >= frameX - e.getWidth() - offset) {
							p = 1;
						}
					}
					if (direction == 2) {
						e.moveLeft();
						if (e.getX() <= offset) {
							p = 1;
						}
					}
					if (e.getY() + 10 >= barrierY) {
						inGame = 0;
						winlose = 2;
					}
				}
				if (p == 1) {
					moveAllEnemiesDown();
					p = 0;
				}
			}
		}
	}
	public class randomBulletThread extends Thread implements Runnable {
		public void run() {
			Random r = new Random();
			int ra, rb;
			for (int i = 1; i > 0; i++) {
				ra = r.nextInt(frequencyOfBullets) + 1;
				ra = ra * 100;
				try {
					Thread.sleep(ra);
				} catch (Exception e) {
					System.out.println(e);
				}
				rb = r.nextInt(enemies.size());
				Enemy e = (Enemy) enemies.get(rb);
				int tempX, tempY;
				tempX = e.getX() + 10;
				tempY = e.getY() + 5;
				bullet.add(new Bullets(tempX, tempY, frameY));
				shotFiredByEnemies++;
			}			
		}
	}
}