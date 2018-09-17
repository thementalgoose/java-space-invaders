//@Author	: Jordan Fisher
//@Version	: 1.8.2

import java.awt.*;
import javax.swing.ImageIcon;

public class Barriers {
	int x, y;
	int condition;
	Image barrier;
	ImageIcon barrierii;
	public Barriers(int x, int y, int condition) {
		this.condition = condition;
		if (condition == 1) {
			barrier = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/barrier100.png"));
		}
		if (condition == 2) {
			barrier = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/barrier80.png"));
		}
		if (condition == 3) {
			barrier = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/barrier60.png"));
		}
		if (condition == 4) {
			barrier = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/barrier40.png"));
		}
		if (condition == 5) {
			barrier = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/barrier20.png"));
		}		
		barrierii = new ImageIcon(barrier);
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
		return barrier;
	}
	public int getImageWidth() {
		return barrierii.getIconWidth();
	}
	public int getCondition() {
		return condition;
	}
}