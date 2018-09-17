// @Author	: Jordan Fisher
// @Version	: 1.5

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpaceInvaders extends JFrame implements ActionListener {
	JTextField rows = new JTextField("", 3);
	JTextField npr = new JTextField("", 3);
	JLabel text = new JLabel("Jordan Fisher.          Rows");
	JLabel text2 = new JLabel("        N.o. Per row");
	JLabel title;
	JButton start = new JButton("Start!");
	JPanel p1 = new JPanel();
	public SpaceInvaders() {
		Image i = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/spaceinvaders.png"));
		ImageIcon ii = new ImageIcon(i);
		title = new JLabel(ii); 
		p1.add(text);
		p1.add(rows);
		p1.add(text2);
		p1.add(npr);
		p1.add(start);
			
		Font font = new Font("Dotum", Font.PLAIN, 80);
		title.setFont(font);
		title.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(title, BorderLayout.CENTER);
		getContentPane().add(p1, BorderLayout.SOUTH);
		
		setTitle("Space Invaders!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		int screenX = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
		int screenY = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		int frameX = this.getWidth() / 2;
		int frameY = this.getHeight() / 2;
		setLocation(screenX - frameX, screenY - frameY);
		start.addActionListener(this);
	}
	public static void main(String[] args) {
		SpaceInvaders frame = new SpaceInvaders();
		frame.setVisible(true);
		System.out.println("Frame loaded.");
	}
	public void actionPerformed(ActionEvent e) {
		if (rows.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "One or more of the fields is empty");
		} else {
			String temp = rows.getText();
			temp = temp.trim();
			int row = Integer.parseInt(temp);
			String temp2 = npr.getText();
			temp2 = temp2.trim();
			int nprv = Integer.parseInt(temp2);
			if (row >= 1 && row <= 7 && nprv >= 1 && nprv <= 12) {
				final sI mainGame = new sI(row, nprv);
				setEnabled(false);
				System.out.println("Game started");
				System.out.println("Rows       : " + row);
				System.out.println("NO. Per Row: " + nprv);
				System.out.println("------------------------");
				mainGame.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						setEnabled(true);
					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "Your combination is invalid.\nYour variables must follow these rules.\nRows is inbetween 1 and 7\nNumber per row is inbetween 1 and 12.");
			}
		}
	}
}