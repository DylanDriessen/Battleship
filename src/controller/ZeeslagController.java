package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

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
		
		Square[][] squares1 = this.view.getPanel1().getGrid().getButtons();
		Square[][] squares2 = this.view.getPanel2().getGrid().getButtons();

		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				squares1[x][y].addMouseListener(new ClickListener());
				squares2[x][y].addMouseListener(new ClickListener());
			}
		}
		
		this.view.getSelectionPanel().getHorizontal().addActionListener(new RadioListener());
		this.view.getSelectionPanel().getVertical().addActionListener(new RadioListener());
		this.view.getSelectionPanel().getShipsComboBox().addActionListener(new ComboboxListener());
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
	
	private class ComboboxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox box = (JComboBox)e.getSource();
			System.out.println("You selected: " + box.getSelectedItem());
		}
		
	}
	
	private class RadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
}
