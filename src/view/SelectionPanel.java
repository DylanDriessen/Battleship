package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.reflect.Array;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.enums.Orientation;
import model.enums.ShipType;
import view.combobox.ComboBox;
import view.combobox.ComboItem;

public class SelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel shipsLabel, orientationLabel;
	private ComboBox<ComboItem<ShipType>> shipsComboBox;
	private JRadioButton horizontal;
	private JRadioButton vertical;
	
	public SelectionPanel() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 1;
		gbc.weightx = 1;

		this.shipsLabel = new JLabel("Beschikbare schepen:");
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
		this.add(this.shipsLabel, gbc);

		//Workaround to make array of a generic type
		ComboItem<ShipType>[] ships = (ComboItem<ShipType>[]) Array.newInstance(ComboItem.class, ShipType.values().length);
		for (int i = 0; i < ShipType.values().length; i++) {
			ShipType st = ShipType.values()[i];
			ships[i] = new ComboItem<ShipType>(st + " (" + st.getLength() + ")", st);
		}

		this.shipsComboBox = new ComboBox<ComboItem<ShipType>>(ships, new ComboItem<ShipType>("Select a ship:"));
	    gbc.gridy = 1;
		this.add(this.shipsComboBox, gbc);
		
	    this.orientationLabel = new JLabel("Orientatie:");
	    gbc.gridy = 2;
	    this.add(this.orientationLabel, gbc);
	    
		ButtonGroup buttonGroup = new ButtonGroup();
		this.horizontal = new JRadioButton(Orientation.HORIZONTAL.getName());
		this.vertical = new JRadioButton(Orientation.VERTICAL.getName());
		
		buttonGroup.add(this.horizontal);
		buttonGroup.add(this.vertical);
		
	    gbc.gridy = 3;
	    gbc.gridwidth = 1;
		this.add(this.horizontal, gbc);
		
		gbc.gridx = 1;
		this.add(this.vertical, gbc);
	}

	public JLabel getShipsLabel() {
		return shipsLabel;
	}

	public JComboBox<ComboItem<ShipType>> getShipsComboBox() {
		return shipsComboBox;
	}

	public JRadioButton getHorizontal() {
		return horizontal;
	}

	public JRadioButton getVertical() {
		return vertical;
	}
	
}
