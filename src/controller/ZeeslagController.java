package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import exception.ModelException;
import model.board.Board;
import model.board.Position;
import model.enums.AttackStrategies;
import model.enums.Orientation;
import model.enums.PlaceStrategies;
import model.enums.ShipType;
import model.facade.IModelFacade;
import properties.PropertiesFile;
import view.GamePanel;
import view.SettingsFrame;
import view.Square;
import view.combobox.ComboBox;
import view.combobox.ComboItem;
import view.facade.IViewFacade;

public class ZeeslagController {

	private final IViewFacade view;
	private final IModelFacade model;
	private Orientation orientation;
	private ShipType shipType;
	private StartListener startListener;
	private SettingsListener settingsListener;
	private SaveListener saveListener;
	
	public ZeeslagController(IModelFacade model, IViewFacade view) {
		this.model = model;
		this.view = view;
		
		Square[][] squares1 = this.view.getButtonsPanel1();
		Square[][] squares2 = this.view.getButtonsPanel2();
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				squares1[x][y].addMouseListener(new AdvancedClickListener());
				squares2[x][y].addMouseListener(new AdvancedClickListener());
			}
		}
		
		this.view.getHorizontalButton().addActionListener(new RadioListener());
		this.view.getVerticalButton().addActionListener(new RadioListener());
		this.view.getJComboBox().addActionListener(new ComboboxListener());
		this.startListener = new StartListener();
		this.settingsListener = new SettingsListener();
		this.saveListener = new SaveListener();
		this.view.getStartButton().addMouseListener(startListener);
		this.view.getSettingsButton().addMouseListener(settingsListener);
		this.view.getSaveButton().addMouseListener(saveListener);
	
		this.askPlayerName();
		this.view.startView();
	}
	
	public void startGame() {
		try {
			this.model.startGame();
			this.view.getStartButton().setEnabled(false);
			this.view.getSettingsButton().setEnabled(false);
			this.view.closeSettings();
		} catch (ModelException e) {
			this.view.showErrorMessage(e.getMessage());
		}
	}
	
	public void openSettings() {
		this.view.openSettings();
	}
	
	public void askPlayerName() {
		String playerName;
		playerName = this.view.getStringInput("Gelieve uw naam in te vullen:");
		if(playerName == null || playerName.isEmpty()) {
			playerName = "Player";
		}
		this.model.setPlayerName(playerName);
		this.view.updateLabel(playerName);
	}
	
	public void leftButtonClicked(Position position, Board board) {
		try {
			this.model.leftButtonClicked(position, this.shipType, this.orientation, board);
		} catch (ModelException e) {
			this.view.showErrorMessage(e.getMessage());
		}
	}
	
	public void rightButtonClicked(Position position, Board board) {
		try {
			this.model.rightButtonClicked(position, board);
		} catch (ModelException e) {
			this.view.showErrorMessage(e.getMessage());
		}
	}
	
	public void buttonEntered(Position position, Board board) {
		try {
			this.model.buttonEntered(position, this.shipType, this.orientation, board);
		} catch (ModelException e) {
			return;
		}
	}
	
	public void buttonExited(Position position, Board board) {
		try {
			this.model.buttonExited(position, this.shipType, this.orientation, board);
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

	public void saveSettings(PlaceStrategies newPlaceStrategy, AttackStrategies newAttackStrategy) {		
		PropertiesFile properties = this.model.getProperties();
		properties.set("placeStrategyAI", newPlaceStrategy.getName());
		properties.set("attackStrategyAI", newAttackStrategy.getName());
		properties.write();
		
		try {
			this.model.changeAIStrategies(newPlaceStrategy, newAttackStrategy);
		} catch (ModelException e) {
			this.view.showErrorMessage(e.getMessage());
		}
		
		this.view.closeSettings();
	}
	
	private class AdvancedClickListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			Square button = (Square)e.getSource();
			Position position = button.getPosition();
			Board board = ((GamePanel) button.getParent().getParent()).getBoard();
			
			if (SwingUtilities.isLeftMouseButton(e)) {
				leftButtonClicked(position, board);
			} else {
				rightButtonClicked(position, board);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			Square button = (Square)e.getSource();
			Position position = button.getPosition();
			Board board = ((GamePanel) button.getParent().getParent()).getBoard();
			buttonEntered(position, board);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			Square button = (Square)e.getSource();
			Position position = button.getPosition();
			Board board = ((GamePanel) button.getParent().getParent()).getBoard();
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
	
	private class SaveListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JButton source = (JButton)e.getSource();
			SettingsFrame settingsFrame = (SettingsFrame)(source.getParent().getParent().getParent().getParent());
			PlaceStrategies newPlaceStrategy = (PlaceStrategies) settingsFrame.getPlaceComboBox().getSelectedItem();
			AttackStrategies newAttackStrategy = (AttackStrategies) settingsFrame.getAttackComboBox().getSelectedItem();
			saveSettings(newPlaceStrategy, newAttackStrategy);
		}

	}
}
