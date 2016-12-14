package model;

import javax.swing.JOptionPane;

import exception.ModelException;
import model.board.Board;
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
	
	private Player player;
	//TODO: Statisch type AI is geen player meer omdat we anders niet aan placeShip() kunnen. Kan dit beter? 
	private AI ai;
	private GameState currentState, newState, startedState, finishedState;
	
	public  Game(PropertiesFile properties, String playerName) throws ModelException{
		Board boardPlayer = new Board();
		Board boardAI = new Board();
		
		this.player = new Player(playerName, boardPlayer, boardAI);
		this.ai = new AI(properties, boardAI, boardPlayer);
				
		this.newState = new NewState(this);
		this.startedState = new StartedState(this);
		this.finishedState = new FinishedState(this);
		setState(this.newState);
	}
	
	public void startGame() throws ModelException {
		//RandomPlaceStrategy oproepen
		if (this.player.getMyBoard().getNbOfShips() == 5) {
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
	
	public boolean finishedGame()throws ModelException{
		if(getPlayer().getMyBoard().getShipCounter() == 0 || getPlayer().getEnemyBoard().getShipCounter() == 0){
			return true;
		} else {
			throw new ModelException("Je moet 5 schepen neerschieten");
		}
}
	
	//Setters
	
	public void setState(GameState state) {
		this.currentState = state;
	}
	
	//Getters
	
	public Player getPlayer() {
		return player;
	}
	
	public AI getAI() {
		return ai;
	}

	public GameState getNewState() {
		return newState;
	}

	public GameState getStartedState() {
		return startedState;
	}

	public GameState getFinishedState() {
		return finishedState;
	}

}
