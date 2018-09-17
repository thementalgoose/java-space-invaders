// Author	:Jordan Fisher
//@Version	:1.3

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Missile {
	int x, y;
	Image missile;
	ImageIcon missileii;
	boolean visible = true;
	public Missile(int x, int y) {
		missile = Toolkit.getDefaultToolkit().getImage("missile.png");
		missileii = new ImageIcon(missile);
		this.x = x - (missileii.getIconWidth() / 2);
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		return missile;
	}
	public void move() {
		y -= 2;
		if (y < 0) {
			visible = false;
		}
	}
	public boolean isVisible() {
		return visible;
	}
}