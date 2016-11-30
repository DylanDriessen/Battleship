package view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel playerName;
	
	public Scoreboard(String playerName) {
		super();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.playerName = new JLabel(playerName + ":");
		this.add(this.playerName);
	}
	
}
