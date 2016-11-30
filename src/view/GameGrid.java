package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Board;
import model.BoardObserver;

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
		
		this.board = board;
		this.board.addObserver(this);
	}

	@Override
	public void boardChanged(Board board) {
		this.board = board;
		this.place();	
	}
	
	public Square[][] getButtons() {
		return this.buttons;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void place() {
		System.out.println("im here bryh");
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				if(this.board.getContainsShip()[x][y]) {
					this.buttons[x][y].setBackground(Color.RED);
				}
			}
		}
	}
}
