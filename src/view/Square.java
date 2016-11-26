package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class Square extends JButton {

	private final int x;
	private final int y;
	
	public Square(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(new Color(240, 240, 240));
	}

}
