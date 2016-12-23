package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Array;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.enums.Orientation;
import model.enums.ShipType;
import view.combobox.ComboBox;
import view.combobox.ComboItem;

/**
 * @author Mathias, Wouter & Dylan
 */
public class SelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel shipsLabel, shipsAvailableLabel, orientationLabel;
	private String selectedShipName;
	private int selectedShipAmount;
	private ComboBox<ComboItem<ShipType>> shipsComboBox;
	private JRadioButton horizontal;
	private JRadioButton vertical;
	private JButton startButton;
	private JButton settingsButton;

	public SelectionPanel() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.gridheight = 1;
		gbc.ipady = 7;

		this.shipsLabel = new JLabel("Beschikbare schepen:");
		this.shipsLabel.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 3, 0);
		this.add(this.shipsLabel, gbc);

		// Workaround to make array of a generic type
		ComboItem<ShipType>[] ships = (ComboItem<ShipType>[]) Array.newInstance(ComboItem.class,
				ShipType.values().length);
		for (int i = 0; i < ShipType.values().length; i++) {
			ShipType st = ShipType.values()[i];
			ships[i] = new ComboItem<ShipType>(st + " (" + st.getLength() + ")", st);
		}

		this.shipsComboBox = new ComboBox<ComboItem<ShipType>>(ships, new ComboItem<ShipType>("Selecteer een schip:"));
		this.shipsComboBox.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 25, 0);
		this.add(this.shipsComboBox, gbc);

		this.shipsAvailableLabel = new JLabel(" ");
		this.shipsAvailableLabel.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(-25, 0, 8, 0);
		this.add(this.shipsAvailableLabel, gbc);

		this.orientationLabel = new JLabel("Oriëntatie:");
		this.orientationLabel.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(this.orientationLabel, gbc);

		ButtonGroup buttonGroup = new ButtonGroup();
		this.horizontal = new JRadioButton(Orientation.HORIZONTAL.getName());
		this.horizontal.setFont(GameFrame.DEFAULT_FONT);
		this.vertical = new JRadioButton(Orientation.VERTICAL.getName());
		this.vertical.setFont(GameFrame.DEFAULT_FONT);

		buttonGroup.add(this.horizontal);
		buttonGroup.add(this.vertical);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		this.add(this.horizontal, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		this.add(this.vertical, gbc);

		gbc.insets = new Insets(30, 0, 0, 0);
		gbc.anchor = GridBagConstraints.SOUTHWEST;

		this.settingsButton = new JButton();
		this.settingsButton.setFont(GameFrame.DEFAULT_FONT);
		this.settingsButton.setText("Instellingen");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		this.add(this.settingsButton, gbc);

		this.startButton = new JButton();
		this.startButton.setFont(GameFrame.DEFAULT_FONT);
		this.startButton.setText("Start Spel");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		this.add(this.startButton, gbc);
	}

	public JLabel getShipsLabel() {
		return this.shipsLabel;
	}

	public String getSelectedShipName() {
		return selectedShipName;
	}

	public void setSelectedShipName(String selectedShipName) {
		this.selectedShipName = selectedShipName;
	}

	public int getSelectedShipAmount() {
		return selectedShipAmount;
	}

	public void setSelectedShipAmount(int selectedShipAmount) {
		this.selectedShipAmount = selectedShipAmount;
	}

	public JLabel getShipsAvailableLabel() {
		return this.shipsAvailableLabel;
	}

	public void updateShipsAvailableLabel() {
		this.shipsAvailableLabel.setText("Je kan nog " + this.getSelectedShipAmount() + " "
				+ this.getSelectedShipName().toLowerCase() + " plaatsen");
	}

	public JComboBox<ComboItem<ShipType>> getShipsComboBox() {
		return this.shipsComboBox;
	}

	public JRadioButton getHorizontal() {
		return this.horizontal;
	}

	public JRadioButton getVertical() {
		return this.vertical;
	}

	public JButton getStartButton() {
		return this.startButton;
	}

	public JButton getSettingsButton() {
		return this.settingsButton;
	}

}
