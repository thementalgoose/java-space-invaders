// @Author	: Jordan Fisher
// @Version	: 1.1

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
	}
}