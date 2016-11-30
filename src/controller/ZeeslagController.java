package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import model.ModelFacade;
import model.Player;
import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;
import model.game.state.BattleState;
import model.game.state.FinishedState;
import model.game.state.GameState;
import model.game.state.InitState;
import view.GameFrame;
import view.Square;
import view.View;
import view.ViewFacade;
import view.combobox.ComboItem;

public class ZeeslagController implements Controller {

	private GameFrame view;
	private final ViewFacade viewFacade;
	
	private GameState currentState;
	private GameState initState;
	private GameState battleState;
	private GameState finishedState;
	
	private Orientation orientation;
	private ShipType shipType;
	
	public ZeeslagController(GameFrame view) {
		this.view = view;
		this.viewFacade = new ViewFacade();
		
		Square[][] squares1 = this.viewFacade.getButtonsPanel1(view);
		Square[][] squares2 = this.viewFacade.getButtonsPanel2(view);
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				squares1[x][y].addMouseListener(new ClickListener());
				squares2[x][y].addMouseListener(new ClickListener());
			}
		}
		
		this.viewFacade.getHorizontalButton(view).addActionListener(new RadioListener());
		this.viewFacade.getVerticalButton(view).addActionListener(new RadioListener());
		this.viewFacade.getJComboBox(view).addActionListener(new ComboboxListener());
		
		this.initState = new InitState();
		this.battleState = new BattleState();
		this.finishedState = new FinishedState();
		
		this.currentState = this.initState;
	}
	
	public void buttonClicked(int x, int y) {
		//TODO: change something in the model when a button is clicked etc. etc.
		
		System.out.println("Clicked button with coördinates: x = " + x + ", y = " + y);
		
		this.currentState.addShip();
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	private class ClickListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			Square button = (Square)e.getSource();
			int x = button.getX();
			int y = button.getY();
			buttonClicked(x, y);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			Square button = (Square)e.getSource();
			int x = button.getX();
			int y = button.getY();
			System.out.println("hover over " + x + y + " bitchez");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			Square button = (Square)e.getSource();
			int x = button.getX();
			int y = button.getY();
			System.out.println("exited " + x + y + " bitchez");
		}

	}
	
	private class ComboboxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox box = (JComboBox)e.getSource();
			ShipType shipType = ((ComboItem)box.getSelectedItem()).getShipType();
			System.out.println("You selected: " + shipType);
		}
		
	}
	
	private class RadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton radioButton = (JRadioButton)e.getSource();
			setOrientation(Orientation.valueOf(radioButton.getText().toUpperCase()));
		}
		
	}
	
}
