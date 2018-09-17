// Author	: Jordan Fisher
//@Version	: 1.8.1

import java.awt.*;
import javax.swing.ImageIcon;

public class Enemy {
	int a = 1;
	int x, y, z;
	int frameX;
	Image enemy;
	ImageIcon enemyii;
	public Enemy(int x, int y, int z, int inframeX) {
		frameX = inframeX;
		if (z == 1) {
			enemy = Toolkit.getDefaultToolkit().getImage("images\\enemy.png");
		}
		if (z == 2) {
			enemy = Toolkit.getDefaultToolkit().getImage("images\\enemy2.png");
		}
		enemyii = new ImageIcon(enemy);
		this.x = x;
		this.y = y; 
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		return enemy;
	}
	public void moveDown() {
		y += 5;
	}
	public void moveDownMore() {
		y += 10;
	}
	public void moveLeft() {
		x -= 1;
	}
	public void moveRight() {
		x += 1;
	}
	public int getHeight() {
		return enemyii.getIconHeight();
	}
	public int getWidth() {
		return enemyii.getIconWidth();
	}
}