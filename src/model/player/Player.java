package model.player;

import model.board.Board;

public class Player {
	
	private String name = "Player";
	private final int initialScore = 19;
	private int score;
	private Board myBoard, enemyBoard;

	public Player (String name, Board myBoard, Board enemyBoard){
		this.name = name;
		this.myBoard = myBoard;
		this.myBoard.setPlayer(this);
		this.enemyBoard = enemyBoard;
		this.score = this.initialScore;
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

	public void setBoard(Board board) {
		this.myBoard = board;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void decrementScore() {
		this.score--;
	}

	public void resetScore() {
		this.score = this.initialScore;
	}

}
