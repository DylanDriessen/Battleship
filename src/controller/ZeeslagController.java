package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import exception.ModelException;
import model.Position;
import model.board.Board;
import model.enums.BoardType;
import model.enums.Orientation;
import model.enums.ShipType;
import model.facade.ModelFacade;
import view.GameGrid;
import view.Square;
import view.combobox.ComboBox;
import view.combobox.ComboItem;
import view.facade.ViewFacade;

public class ZeeslagController {

	private final ViewFacade viewFacade;
	private final ModelFacade modelFacade;
	private Orientation orientation;
	private ShipType shipType;
	private StartListener startListener;
	private SettingsListener settingsListener;
	
	public ZeeslagController(ModelFacade modelFacade, ViewFacade viewFacade) {
		this.modelFacade = modelFacade;
		this.viewFacade = viewFacade;
		
		Square[][] squares1 = this.viewFacade.getButtonsPanel1();
		Square[][] squares2 = this.viewFacade.getButtonsPanel2();
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				squares1[x][y].addMouseListener(new AdvancedClickListener());
				squares2[x][y].addMouseListener(new AdvancedClickListener());
			}
		}
		
		this.viewFacade.getHorizontalButton().addActionListener(new RadioListener());
		this.viewFacade.getVerticalButton().addActionListener(new RadioListener());
		this.viewFacade.getJComboBox().addActionListener(new ComboboxListener());
		this.startListener = new StartListener();
		this.settingsListener = new SettingsListener();
		this.viewFacade.getStartButton().addMouseListener(startListener);
		this.viewFacade.getSettingsButton().addMouseListener(settingsListener);
	
		this.modelFacade.placeAIShips(false);
		this.viewFacade.startView();
	}
	
	public void startGame() {
		try {
			this.modelFacade.startGame();
			this.viewFacade.getStartButton().removeMouseListener(startListener);
			this.viewFacade.getStartButton().setEnabled(false);
		} catch (ModelException e) {
			this.viewFacade.showErrorMessage(e.getMessage());
		}
	}
	
	public void openSettings() {
		this.viewFacade.openSettings();
	}
	
	public void buttonClicked(Position position, Board board) {
		try {
			this.modelFacade.buttonClicked(position, this.shipType, this.orientation, board);
		} catch (ModelException e) {
			this.viewFacade.showErrorMessage(e.getMessage());
		}
	}
	
	public void buttonEntered(Position position, Board board) {
		try {
			this.modelFacade.buttonEntered(position, this.shipType, this.orientation, board);
		} catch (ModelException e) {
			return;
		}
	}
	
	public void buttonExited(Position position, Board board) {
		try {
			this.modelFacade.buttonExited(position, this.shipType, this.orientation, board);
		} catch (ModelException e) {
			return;
		}
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}
	
	public Board getBoardFromType(BoardType boardType) {
		if(boardType == BoardType.PLAYER) {
			return this.modelFacade.getBoardPlayer();
		} else {
			return this.modelFacade.getBoardAI();
		}
	}
	
	private class ClickListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			Square button = (Square)e.getSource();
			Position position = button.getPosition();
			Board board = getBoardFromType(((GameGrid) button.getParent()).getBoardType());
			buttonClicked(position, board);
		}

	}

	private class AdvancedClickListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			Square button = (Square)e.getSource();
			Position position = button.getPosition();
			Board board = getBoardFromType(((GameGrid) button.getParent()).getBoardType());
			buttonClicked(position, board);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			Square button = (Square)e.getSource();
			Position position = button.getPosition();
			Board board = getBoardFromType(((GameGrid) button.getParent()).getBoardType());
			buttonEntered(position, board);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			Square button = (Square)e.getSource();
			Position position = button.getPosition();
			Board board = getBoardFromType(((GameGrid) button.getParent()).getBoardType());
			buttonExited(position, board);
		}

	}
	
	private class ComboboxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ComboBox<ComboItem<ShipType>> box = (ComboBox<ComboItem<ShipType>>)e.getSource();
			ShipType shipType = ((ComboItem<ShipType>)box.getSelectedItem()).getValue();
			setShipType(shipType);
		}
		
	}
	
	private class RadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton radioButton = (JRadioButton)e.getSource();
			setOrientation(Orientation.checkOrientation(radioButton.getText().toUpperCase()));
		}
		
	}
	
	private class StartListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			startGame();
		}
		
	}
	
	private class SettingsListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			openSettings();
		}
		
	}
}
