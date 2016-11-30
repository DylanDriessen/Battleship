package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.enums.Orientation;
import model.enums.ShipType;

public class SelectionPanel extends JPanel {

	JLabel shipsLabel;
	JComboBox<String> shipsComboBox;
	JRadioButton horizontal;
	JRadioButton vertical;
	
	public SelectionPanel() {
		this.shipsLabel = new JLabel("Beschikbare schepen:");
		this.add(this.shipsLabel);

		String[] ships = new String[ShipType.values().length];
		for (int i = 0; i < ShipType.values().length; i++) {
			ships[i] = ShipType.values()[i].getName() + " (" + ShipType.values()[i].getLength() + ")";
		}

		// JComboBox fucked de UI
		this.shipsComboBox = new JComboBox<>(ships);

		// shipsComboBox.setPreferredSize(new Dimension(5,5));
		this.add(this.shipsComboBox);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		this.horizontal = new JRadioButton(Orientation.HORIZONTAL.getName());
		this.vertical = new JRadioButton(Orientation.VERTICAL.getName());

		buttonGroup.add(this.horizontal);
		buttonGroup.add(this.vertical);
		this.add(this.horizontal);
		this.add(this.vertical);
	}

	public JLabel getShipsLabel() {
		return shipsLabel;
	}

	public JComboBox<String> getShipsComboBox() {
		return shipsComboBox;
	}

	public JRadioButton getHorizontal() {
		return horizontal;
	}

	public JRadioButton getVertical() {
		return vertical;
	}
	
}
