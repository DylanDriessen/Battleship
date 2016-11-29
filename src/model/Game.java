package model;

public class Game {
	
	private Board board1, board2;
	
	public  Game(String playerName){
		this.board1 = new Board(playerName);
		this.board2 = new Board("Computer");
	}

	public Board getBoard1() {
		return board1;
	}

	public Board getBoard2() {
		return board2;
	}

}
