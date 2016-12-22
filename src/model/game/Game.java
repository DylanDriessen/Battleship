package model.game;

import java.util.ArrayList;
import java.util.List;

import exception.ModelException;
import model.board.Board;
import model.board.Position;
import model.enums.AttackStrategies;
import model.enums.Orientation;
import model.enums.PlaceStrategies;
import model.enums.ShipType;
import model.game.state.StartedState;
import model.player.AI;
import model.player.Player;
import properties.PropertiesFile;
import model.game.state.FinishedState;
import model.game.state.GameState;
import model.game.state.NewState;

public class Game implements GameObservable {

	private Player player, winner;
	private AI ai;
	private GameState currentState, newState, startedState, finishedState;
	private List<GameObserver> observers;
	private PropertiesFile properties;
	private static final int MAX_NUMBER_OF_SHIPS = 5;

	public Game(PropertiesFile properties) throws ModelException {
		this.properties = properties;
		this.observers = new ArrayList<GameObserver>();

		Board boardPlayer = new Board(MAX_NUMBER_OF_SHIPS);
		Board boardAI = new Board(MAX_NUMBER_OF_SHIPS);

		this.player = new Player(boardPlayer, boardAI);
		this.ai = new AI(this.properties, boardAI, boardPlayer);

		this.newState = new NewState(this);
		this.startedState = new StartedState(this);
		this.finishedState = new FinishedState(this);

		setState(this.newState);
	}

	public void startGame() throws ModelException {
		int nbOfShips = this.player.getMyBoard().getNbOfShips();
		if (nbOfShips == MAX_NUMBER_OF_SHIPS) {
			this.currentState = startedState;
			this.ai.placeShips(false);
		} else {
			throw new ModelException("Je moet nog " + (MAX_NUMBER_OF_SHIPS - nbOfShips) + " sch"
					+ (MAX_NUMBER_OF_SHIPS - nbOfShips == 1 ? "ip" : "epen") + " op je eigen bord plaatsen.");
		}
	}
	
	public void resetGame() throws ModelException {
		this.player.reset();
		this.ai.reset();

		this.player.getMyBoard().notifyObservers();
		this.ai.getMyBoard().notifyObservers();
	}
	
	public void finishGame() throws ModelException {
		this.currentState.finishGame();
	}

	public void changeAIStrategies(PlaceStrategies newPlaceStrategy, AttackStrategies newAttackStrategy)
			throws ModelException {
		this.currentState.changeStrategies(newPlaceStrategy, newAttackStrategy);
	}

	public void leftButtonClicked(Position position, ShipType shipType, Orientation orientation, Board board)
			throws ModelException {
		this.currentState.squareLeftClicked(position, shipType, orientation, board);
	}

	public void rightButtonClicked(Position position, Board board) throws ModelException {
		this.currentState.squareRightClicked(position, board);
	}

	public void buttonEntered(Position position, ShipType shipType, Orientation orientation, Board board)
			throws ModelException {
		this.currentState.squareEntered(position, shipType, orientation, board);
	}

	public void buttonExited(Position position, ShipType shipType, Orientation orientation, Board board)
			throws ModelException {
		this.currentState.squareExited(position, shipType, orientation, board);
	}

	// Setters

	public void setState(GameState state) {
		this.currentState = state;
	}

	public void setWinner(Player player) {
		this.winner = player;
	}

	// Getters

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
	public void notifyObservers() {
		for (GameObserver o : this.observers) {
			o.update(this);
		}
	}
}
