package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ShipType;

public class SelectionPanel extends JPanel {
	
	public SelectionPanel(){
		
	  JLabel shipsLabel = new JLabel("Beschikbare schepen:");
	  this.add(shipsLabel);
	  String[] ships = new String [ShipType.values().length];
	  for (int i = 0; i < ShipType.values().length; i++) {
	  	ships[i] = ShipType.values()[i].getName();
	  }
	  
	  //JComboBox fucked de UI
	  JComboBox<String> shipsComboBox = new JComboBox<>(ships);
	  
//	  shipsComboBox.setPreferredSize(new Dimension(5,5));
	  this.add(shipsComboBox);
	
	  
	}
}
