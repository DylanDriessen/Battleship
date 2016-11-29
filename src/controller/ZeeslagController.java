package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import model.ModelFacade;
import model.Player;
import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;
import view.GameFrame;
import view.Square;
import view.View;

public class ZeeslagController implements Controller {

	private GameFrame view;
	
	public ZeeslagController(GameFrame view) {
		this.view = view;
		
		for(Square[] square : this.view.getPanel1().getGrid().getButtons()) {
			for(Square sq : square) {
				sq.addMouseListener(new ClickListener());
			}
		}
		
		for(Square[] square : this.view.getPanel2().getGrid().getButtons()) {
			for(Square sq : square) {
				sq.addMouseListener(new ClickListener());
			}
		}
	}
	
	public void buttonClicked(int x, int y) {
		//TODO: change something in the model when a button is clicked etc. etc.
		System.out.println("Clicked button with coördinates: x = " + x + ", y = " + y);
	}


	private class ClickListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			Square button = ((Square)e.getSource());
			int x = button.getX();
			int y = button.getY();
			buttonClicked(x, y);
		}

	}
	
}
