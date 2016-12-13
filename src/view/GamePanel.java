package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Position;
import model.board.Board;
import model.board.BoardObserver;
import model.enums.ButtonType;

public class GamePanel extends JPanel implements BoardObserver {

	private static final long serialVersionUID = 1L;
	private Scoreboard scoreboard;
	private GameGrid grid;
	
	public GamePanel(View gameFrame, String playerName, int score, Board board) {
		super();
		
		this.scoreboard = new Scoreboard(playerName, score);
		this.grid = new GameGrid(gameFrame, 300, board);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(scoreboard);
		this.add(grid);
		
		board.addObserver(this);
		
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
	
	@Override
	public void boardChanged(Board board) {
		this.grid.setBoard(board);
		this.grid.paintBoard();	
		this.scoreboard.setScore(board.getPlayer().getScore());
	}	
	
}
