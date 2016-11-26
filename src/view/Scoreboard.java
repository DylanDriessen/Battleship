package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JPanel {

	private JLabel playerName;
	
	public Scoreboard(String playerName) {
		this.playerName = new JLabel(playerName + ":");
		this.add(this.playerName);
	}
	
}
