package model.facade;

import exception.ModelException;
import model.Position;
import model.board.Board;
import model.enums.AttackStrategies;
import model.enums.Orientation;
import model.enums.PlaceStrategies;
import model.enums.ShipType;
import model.game.GameObserver;
import properties.PropertiesFile;

public interface IModelFacade {

	PropertiesFile getProperties();
	void startGame() throws ModelException;
	void buttonClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	void buttonEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;	
	void buttonExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	
	String getPlayerName();
	int getPlayerScore();
	void setPlayerName(String playerName);
	Board getBoardPlayer();
	
	String getAIName();
	int getAIScore();
	Board getBoardAI();
	void placeAIShips(boolean visible);
	void changeAIStrategies(PlaceStrategies newPlaceStrategy, AttackStrategies newAttackStrategy) throws ModelException;
	
	void registerGameObserver(GameObserver o);
	
}