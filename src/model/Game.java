package model;

import exception.ModelException;
import model.enums.Orientation;
import model.enums.ShipType;
import model.game.state.StartedState;
import model.game.state.FinishedState;
import model.game.state.GameState;
import model.game.state.NewState;

public class Game {
	
	private Board board1, board2;
	private GameState currentState, newState, startedState, finishedState;
	
	public  Game(String playerName){
		this.board1 = new Board(playerName);
		this.board2 = new Board("Computer");
		
		this.newState = new NewState(this);
		this.startedState = new StartedState(this);
		this.finishedState = new FinishedState(this);
		this.currentState = this.newState;
	}

	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation) throws ModelException {
		this.currentState.squareClicked(x, y, shipType, orientation);
	}
	
	//GETTERS
	public Board getBoard1() {
		return board1;
	}

	public Board getBoard2() {
		return board2;
	}

}
