package view;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private GameGrid grid;
	private Scoreboard scoreboard;
	
	public GamePanel(View gameFrame, String playerName) {		
		this.grid = new GameGrid(gameFrame, 400);
		this.scoreboard = new Scoreboard(playerName);
		
		this.add(scoreboard);
		this.add(grid);
	}
}
