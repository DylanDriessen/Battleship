package view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	private String playerName;
	private int score;
	
	public Scoreboard(String playerName, int score) {
		super();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.playerName = playerName;
		this.score = score;
		
		this.label = new JLabel(playerName + "(" + score + "):");
		this.label.setFont(GameFrame.DEFAULT_FONT);
		this.add(this.label);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
}
