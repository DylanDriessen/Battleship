package model.player;

import model.board.Board;

/**
 * @author Mathias, Wouter & Dylan
 */
public class Player {
	
	private String name = "Player";
	private final static int INITIAL_SCORE = 19;
	private int score;
	private Board myBoard, enemyBoard;

	public Player (String name, Board myBoard, Board enemyBoard){
		this.name = name;
		this.myBoard = myBoard;
		this.myBoard.setPlayer(this);
		this.enemyBoard = enemyBoard;
		this.score = Player.INITIAL_SCORE;
	}

	public Player(Board myBoard, Board enemyBoard) {
		this(null, myBoard, enemyBoard);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Board getMyBoard() {
		return myBoard;
	}
	
	public Board getEnemyBoard() {
		return enemyBoard;
	}

	public int getScore() {
		return this.score;
	}
	
	public void decrementScore() {
		this.score--;
	}

	private void resetScore() {
		this.score = Player.INITIAL_SCORE;
	}

	public void reset() {
		this.myBoard.reset();
		resetScore();
	}

}
