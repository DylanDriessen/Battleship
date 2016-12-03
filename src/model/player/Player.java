package model.player;

import model.board.Board;

public class Player {
	
	private String name;
	private Board myBoard, enemyBoard;

	public Player (String name, Board myBoard, Board enemyBoard){
		this.name = name;
		this.myBoard = myBoard;
		this.enemyBoard = enemyBoard;
	}

	public String getName(){
		return name;
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

}
