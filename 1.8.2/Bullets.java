//@Author	: Jordan Fisher
//@Version	: 1.8.2

import java.awt.*;
import javax.swing.ImageIcon;

public class Bullets {
	int x, y, frameY;
	boolean isVisible = true;
	Image bullet;
	ImageIcon bulletii;
	public Bullets(int x, int y, int frameY) {
		this.frameY = frameY;
		bullet = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bullet.png"));
		bulletii = new ImageIcon(bullet);
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
		return bullet;
	}
	public void move() {
		y += 2;
		if (y >= frameY - 31) {
			isVisible = false;
		}
	}
	public boolean getVisible() {
		return isVisible;
	}
}