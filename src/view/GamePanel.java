package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.board.Board;
import model.board.BoardObserver;

/**
 * @author Mathias, Wouter & Dylan
 */
public class GamePanel extends JPanel implements BoardObserver {

	private static final long serialVersionUID = 1L;
	private Scoreboard scoreboard;
	private GameGrid grid;
	private Board board;
	
	public GamePanel(GameFrame gameFrame, String playerName, int score, Board board) {
		super();
		
		this.scoreboard = new Scoreboard(playerName, score);
		this.grid = new GameGrid(gameFrame, 300);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(scoreboard);
		this.add(grid);
		
		this.board = board;
		board.addObserver(this);
	}
	
	@Override
	public void update(Board board) {
		this.setBoard(board);
		this.grid.paintBoard(this.board);	
		this.scoreboard.setScore(this.board.getPlayer().getScore());
		this.scoreboard.updateLabel();
	}
	
	public void revealBoard() {
		this.grid.revealBoard(this.board);		
	}
	
	//Getters & setters

	public GameGrid getGrid() {
		return grid;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	private void setBoard(Board board) {
		this.board = board;
	}	
}
