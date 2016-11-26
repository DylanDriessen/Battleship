package view;

import javax.swing.JButton;

public class Square extends JButton {

	private final int x;
	private final int y;
	
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
