package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Scoreboard scoreboard;
	private GameGrid grid;
	
	public GamePanel(View gameFrame, String playerName) {
		super();
		
		this.scoreboard = new Scoreboard(playerName);
		this.grid = new GameGrid(gameFrame, 300);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(scoreboard);
		this.add(grid);
		
		/*
		 * GRIDBAGLAYOUT 
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;

		gbc.weighty = 0.1;
		this.add(scoreboard, gbc);
		
		gbc.weighty = 0.9;
		gbc.gridy = 1;
		this.add(grid, gbc);
		*/
	}

	public GameGrid getGrid() {
		return grid;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}       
	
}
