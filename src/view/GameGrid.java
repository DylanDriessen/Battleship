package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Board;
import model.BoardObserver;

public class GameGrid extends JPanel implements BoardObserver {

	private Square[][] buttons = new Square[10][10];
	
	public GameGrid(View gameFrame, int width) {
		super();
		this.setLayout(new GridLayout(10, 10));
		this.setPreferredSize(new Dimension(width, width));
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				Square button = new Square(x, y);
				this.buttons[x][y] = button;
				this.add(button);
			}
		}
	}

	@Override
	public void boardChanged(Board board) {
		// TODO Auto-generated method stub
		
	}
	
	public Square[][] getButtons() {
		return this.buttons;
	}
	
}
