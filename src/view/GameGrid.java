package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.board.Board;
import model.board.Position;
import model.board.Ship;
import model.enums.ButtonType;

/**
 * @author Mathias, Wouter & Dylan
 */
public class GameGrid extends JPanel {

	private static final long serialVersionUID = 1L;
	private Square[][] buttons = new Square[10][10];
	
	public GameGrid(GameFrame gameFrame, int width) {
		super();
		this.setLayout(new GridLayout(10, 10));
		
		Dimension size = new Dimension(width, width);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				Square button = new Square(x, y);
				this.buttons[x][y] = button;
				this.add(button);
			}
		}
		
		this.setBorder(BorderFactory.createLineBorder(new Color(139, 162, 180), 3));
	}
	
	public void paintBoard(Board board) {
		for(Position p : board.getChangedButtons()) {
			int x = p.getX();
			int y = p.getY();
			ButtonType type = board.getBoardPositions()[x][y].getButtonType();
			this.buttons[x][y].setBackground(type.getColor());
			this.buttons[x][y].setBorderPainted(type.hasBorder());
			this.buttons[x][y].setText(type.getText());
			this.buttons[x][y].setToolTipText(type.getToolTip());
			this.buttons[x][y].repaint();
		}
	}
	
	public void revealBoard(Board board) {
		for(Ship ship : board.getShips()) {
			for(Position p : ship.getPositions()) {
				System.out.println("repainting");
				int x = p.getX();
				int y = p.getY();
				ButtonType type = board.getBoardPositions()[x][y].getButtonType();
				this.buttons[x][y].setBackground(type.getColor());
				this.buttons[x][y].setBorderPainted(type.hasBorder());
				this.buttons[x][y].repaint();
			}
		}
	}
	
	public Square[][] getButtons() {
		return this.buttons;
	}
}
