package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Position;
import model.board.Board;
import model.enums.BoardType;
import model.enums.ButtonType;

public class GameGrid extends JPanel {

	private static final long serialVersionUID = 1L;
	private Square[][] buttons = new Square[10][10];
	private BoardType boardType;
	
	public GameGrid(View gameFrame, int width, BoardType boardType) {
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
		
		this.boardType = boardType;
		
		this.setBorder(BorderFactory.createLineBorder(new Color(139, 162, 180), 3));
	}

	public BoardType getBoardType() {
		return boardType;
	}

	public void paintBoard(Board board) {
		for(Position p : board.getChangedButtons()) {
			int x = p.getX();
			int y = p.getY();
			ButtonType type = board.getBoardPositions()[x][y].getButtonType();
			this.buttons[x][y].setBackground(type.getColor());
			this.buttons[x][y].setBorderPainted(type.hasBorder());
			this.buttons[x][y].setText(type.getText());
			this.buttons[x][y].repaint();
		}
	}	
	
	public Square[][] getButtons() {
		return this.buttons;
	}

}
