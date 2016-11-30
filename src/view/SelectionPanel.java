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
import view.combobox.ComboBox;
import view.combobox.ComboItem;

public class SelectionPanel extends JPanel {

	JLabel shipsLabel;
	ComboBox<ComboItem> shipsComboBox;
	JRadioButton horizontal;
	JRadioButton vertical;
	
	public SelectionPanel() {
		this.shipsLabel = new JLabel("Beschikbare schepen:");
		this.add(this.shipsLabel);

		ComboItem[] ships = new ComboItem[ShipType.values().length];
		for (int i = 0; i < ShipType.values().length; i++) {
			ShipType st = ShipType.values()[i];
			ships[i] = new ComboItem(st + " (" + st.getLength() + ")", st);
		}

		this.shipsComboBox = new ComboBox<ComboItem>(ships, new ComboItem("Select a ship:"));
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

	public JComboBox<ComboItem> getShipsComboBox() {
		return shipsComboBox;
	}

	public JRadioButton getHorizontal() {
		return horizontal;
	}

	public JRadioButton getVertical() {
		return vertical;
	}
	
}
