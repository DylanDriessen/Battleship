package model.facade;

import exception.ModelException;
import model.game.Game;
import model.game.GameObserver;
import properties.PropertiesFile;
import model.board.Board;
import model.board.Position;
import model.enums.AttackStrategies;
import model.enums.Orientation;
import model.enums.PlaceStrategies;
import model.enums.ShipType;

public class ModelFacade implements IModelFacade {
	
	private Game game;
	private PropertiesFile properties;

	public ModelFacade(PropertiesFile properties) throws ModelException {
		this.game = new Game(properties);
		this.properties = properties;
	}
	
	@Override
	public PropertiesFile getProperties() {
		return properties;
	}
	
	@Override
	public void startGame() throws ModelException {
		this.game.startGame();
	}
	
	@Override
	public void leftButtonClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.leftButtonClicked(position, shipType, orientation, board);
	}
	
	@Override
	public void rightButtonClicked(Position position, Board board) throws ModelException {
		this.game.rightButtonClicked(position, board);
	}
	
	@Override
	public void buttonEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonEntered(position, shipType, orientation, board);
	}
	
	@Override
	public void buttonExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonExited(position, shipType, orientation, board);
	}
	
	@Override
	public String getPlayerName() {
		return this.game.getPlayer().getName();
	}
	
	@Override
	public void setPlayerName(String playerName) {
		this.game.getPlayer().setName(playerName);
	}
	
	@Override
	public int getPlayerScore() {
		return this.game.getPlayer().getScore();
	}
	
	@Override
	public Board getBoardPlayer() {
		return this.game.getPlayer().getMyBoard();
	}
	
	@Override
	public String getAIName() {
		return this.game.getAI().getName();
	}
	
	@Override
	public int getAIScore() {
		return this.game.getAI().getScore();
	}
	
	@Override
	public Board getBoardAI() {
		return this.game.getAI().getMyBoard();
	}
	
	@Override
	public void changeAIStrategies(PlaceStrategies newPlaceStrategy, AttackStrategies newAttackStrategy) throws ModelException {
		this.game.changeAIStrategies(newPlaceStrategy, newAttackStrategy);
	}
	
	@Override
	public void registerGameObserver(GameObserver o) {
		this.game.addObserver(o);
	}
	
}
