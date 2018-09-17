// @Author	:Jordan Fisher
// @Version	:1.5

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Enemy {
	int x, y, z;
	Image enemy;
	ImageIcon enemyii;
	public Enemy(int x, int y, int z) {
		if (z == 1) {
			enemy = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy.png"));
		}
		if (z == 2) {
			enemy = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy2.png"));
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
	public void move() {
		y += 1;
	}
}