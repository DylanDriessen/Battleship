package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.board.Board;
import model.board.BoardObserver;
import model.enums.BoardType;

public class GamePanel extends JPanel implements BoardObserver {

	private static final long serialVersionUID = 1L;
	private Scoreboard scoreboard;
	private GameGrid grid;
	
	public GamePanel(View gameFrame, String playerName, int score, BoardType boardType) {
		super();
		
		this.scoreboard = new Scoreboard(playerName, score);
		this.grid = new GameGrid(gameFrame, 300, boardType);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(scoreboard);
		this.add(grid);
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
		this.scoreboard.updateLabel();
	}	
	
}
