package model;

import exception.ModelException;
import model.enums.Orientation;
import model.enums.ShipType;
import model.game.state.StartedState;
import model.player.AI;
import model.player.Player;
import properties.PropertiesFile;
import model.game.state.FinishedState;
import model.game.state.GameState;
import model.game.state.NewState;

public class Game {
	
	private Player player, ai;
	private Board boardPlayer, boardAI;
	private GameState currentState, newState, startedState, finishedState;
	
	public  Game(PropertiesFile properties, String playerName) throws ModelException{
		this.player = new Player(playerName);
		this.ai = new AI(properties);
		
		this.boardPlayer = new Board(this.player);
		this.boardAI = new Board(this.ai);
		
		this.newState = new NewState(this);
		this.startedState = new StartedState(this);
		this.finishedState = new FinishedState(this);
		this.currentState = this.newState;
	}
	
	public void startGame() throws ModelException {
		if (boardPlayer.getNbOfShips() == 5) {
			this.currentState = startedState;
		} else {
			throw new ModelException("Je moet 5 schepen op je eigen bord plaatsen");
		}
	}

	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.currentState.squareClicked(x, y, shipType, orientation, board);
	}
	
	public void buttonEntered(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.currentState.squareEntered(x, y, shipType, orientation, board);
	}
	
	public void buttonExited(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.currentState.squareExited(x, y, shipType, orientation, board);
	}
	
	//GETTERS
	public Board getBoardPlayer() {
		return boardPlayer;
	}

	public Board getBoardAI() {
		return boardAI;
	}

}
