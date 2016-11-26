package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javafx.geometry.HorizontalDirection;

public class Scoreboard extends JPanel {

	private JLabel playerName;
	
	public Scoreboard(String playerName) {
		super();		
		this.playerName = new JLabel(playerName + ":");
		this.add(this.playerName);
	}
	
}
