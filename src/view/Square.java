package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import model.Position;

public class Square extends JButton {

	private final Position position;
	
	public Square(int x, int y) {
		super();
		this.position = new Position(x, y);
	}

	public Position getPosition() {
		return this.position;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(new Color(240, 240, 240));
	}
	
	@Override
	public int getY(){
		return this.getPosition().getY();
	}
	
	@Override
	public int getX(){
		return this.getPosition().getX();
	}

}
