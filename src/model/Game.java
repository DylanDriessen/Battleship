package model;

import model.enums.Orientation;
import model.enums.ShipType;
import model.game.state.BattleState;
import model.game.state.FinishedState;
import model.game.state.GameState;
import model.game.state.InitState;

public class Game {
	
	private Board board1, board2;
	private GameState currentState, initState, battleState, finishedState;
	
	public  Game(String playerName){
		this.board1 = new Board(playerName);
		this.board2 = new Board("Computer");
		
		this.initState = new InitState(this);
		this.battleState = new BattleState(this);
		this.finishedState = new FinishedState(this);
		this.currentState = this.initState;
	}

	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation) {
		this.currentState.buttonClicked(x, y, shipType, orientation);
	}
	
	//GETTERS
	public Board getBoard1() {
		return board1;
	}

	public Board getBoard2() {
		return board2;
	}

}
