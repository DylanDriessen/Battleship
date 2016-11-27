package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {

	private GameGrid grid;
	private Scoreboard scoreboard;
	
	public GamePanel(View gameFrame, String playerName) {
		super();
		
		this.grid = new GameGrid(gameFrame, 350);
		this.scoreboard = new Scoreboard(playerName);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.LINE_START;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weighty = 0.1;
	    
		this.add(scoreboard, gbc);
		
		gbc.weighty = 0.90;
	    gbc.gridy = 1;
		this.add(grid, gbc);
	}
}
