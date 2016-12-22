package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.board.Position;
import model.enums.ButtonType;

/**
 * @author Mathias, Wouter & Dylan
 */
public class Square extends JButton {

	private static final long serialVersionUID = 1L;
	private final Position position;
	
	public Square(int x, int y) {
		super();
		this.position = new Position(x, y);
		this.setBackground(ButtonType.EMPTY.getColor());
	}

	public Position getPosition() {
		return this.position;
	}

	@Override
	protected void paintComponent(Graphics g) {
		this.setBorder(BorderFactory.createLineBorder(new Color(194, 227, 254)));
		super.paintComponent(g);
	}

}
