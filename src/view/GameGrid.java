package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Board;
import model.BoardObservable;
import model.BoardObserver;
import model.Position;
import model.enums.ButtonType;

public class GameGrid extends JPanel implements BoardObserver {

	private static final long serialVersionUID = 1L;
	private Square[][] buttons = new Square[10][10];
	private Board board;
	
	public GameGrid(View gameFrame, int width, Board board) {
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

		this.board = board;
		this.board.addObserver(this);
	}

	public Board getBoard() {
		return board;
	}

	@Override
	public void boardChanged(Board board) {
		this.board = board;
		this.paintBoard();	
	}
	
	public void paintBoard() {
		for(Position p : this.board.getChangedButtons()) {
			int x = p.getX();
			int y = p.getY();
			Color color = this.board.getBoardPositions()[x][y].getColor();
			this.buttons[x][y].setBackground(color);
			if (color == Color.RED) {
				this.buttons[x][y].setBorderPainted(false);
			}
			this.buttons[x][y].repaint();
		}
	}	
	
	public Square[][] getButtons() {
		return this.buttons;
	}

}
