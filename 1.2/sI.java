// @Author	: Jordan Fisher
// @Version	: 1.2

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class sI extends JFrame {
	int frameX = 400;
	int frameY = 400;
	
	Image craft, enemy;
	ImageIcon craftii, enemyii;
	int screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screenY = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	int posX;
	int posY;
	int increment = 10;
	
	int countMissiles =0;
	
	ArrayList missiles = new ArrayList();
	
	
	
	public sI() {
		try {
			craft = Toolkit.getDefaultToolkit().getImage("craft.png");
			craftii = new ImageIcon(craft);
			enemy = Toolkit.getDefaultToolkit().getImage("enemy.png");
			enemyii = new ImageIcon(enemy);
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
		updatePanel t = new updatePanel();
		t.start();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					if (posX >= frameX - 30 - craftii.getIconWidth()) {} else {
						posX += increment;
						repaint();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (posX <= 30) {} else {
						posX -= increment;
						repaint();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					missiles.add(new Missile(posX + (craftii.getIconWidth() / 2), posY + (craftii.getIconHeight() / 2)));
					countMissiles++;
				}
				if (e.getKeyCode() == KeyEvent.VK_R) {
					System.out.println("Craft : (" + posX + ", " + posY + ")");
					System.out.println(missiles.size() + " missiles active.");
					System.out.println(countMissiles + " missiles fired.");
				}
			}
		});
	}
	public static void main(String[] args) {
		sI frame = new sI();
		frame.setVisible(true);
	}
	public void paint(Graphics g) {
		g.clearRect(0, 0, frameX, frameY);
		g.drawImage(craft, posX, posY, this);
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = (Missile) missiles.get(i);
			g.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}
	}
	public class updatePanel extends Thread implements Runnable {
		public void run() {
			for (int i = 1; i > 0; i++) {
				try {
					Thread.sleep(30);
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
				repaint();
			}
		}
	}
}