package model.game;

import java.util.ArrayList;
import java.util.List;

import exception.ModelException;
import model.Position;
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

public class Game implements GameObservable{
	
	private Player player, winner;
	private AI ai;
	private GameState currentState, newState, startedState, finishedState;
	private List<GameObserver> observers;
	
	private PropertiesFile properties;
	private String playerName;
	
	public  Game(PropertiesFile properties) throws ModelException{
		this.properties = properties;
		this.observers = new ArrayList<GameObserver>();
		
		Board boardPlayer = new Board();
		Board boardAI = new Board();
		
		this.player = new Player(this.playerName, boardPlayer, boardAI);
		this.ai = new AI(this.properties, boardAI, boardPlayer);
				
		this.newState = new NewState(this);
		this.startedState = new StartedState(this);
		this.finishedState = new FinishedState(this);
		
		setState(this.newState);
	}
	
	public void startGame() throws ModelException {
		if (this.player.getMyBoard().getNbOfShips() == 5) {
			this.currentState = startedState;
			this.ai.placeShips(true);
		} else {
			throw new ModelException("Je moet exact 5 schepen op je eigen bord plaatsen.");
		}
	}

	public void buttonClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.currentState.squareClicked(position, shipType, orientation, board);
	}
	
	public void buttonEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.currentState.squareEntered(position, shipType, orientation, board);
	}
	
	public void buttonExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.currentState.squareExited(position, shipType, orientation, board);
	}
	
	//Setters
	
	public void setState(GameState state) {
		this.currentState = state;
	}
	
	public void setWinner(Player player) {
		this.winner = player;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	//Getters
	
	public Player getPlayer() {
		return player;
	}
	
	public AI getAI() {
		return ai;
	}
	
	public Player getWinner() {
		return winner;
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

	@Override
	public void addObserver(GameObserver o) {
		this.observers.add(o);
	}

	@Override
	public void removeObserver(GameObserver o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyGameChanged() {
		for(GameObserver o : this.observers) {
			o.gameChanged(this);;
		}
	}

	public void reset() throws ModelException {
		this.player.resetScore();
		this.ai.resetScore();
		
		this.player.getMyBoard().init();
		this.ai.getMyBoard().init();
		
		this.player.getMyBoard().notifyBoardChanged();
		this.ai.getMyBoard().notifyBoardChanged();
	}

	public void finishGame() throws ModelException {
		this.currentState.finishGame();
	}

}
