package model.facade;

import exception.ModelException;
import model.Position;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;
import model.game.GameObserver;

public interface IModelFacade {

	Board getBoardPlayer();
	Board getBoardAI();
	void startGame()throws ModelException;
	void buttonClicked(Position position, ShipType shipType, Orientation orientation, Board board)throws ModelException;
	void buttonEntered(Position position, ShipType shipType, Orientation orientation, Board board)throws ModelException;	
	void buttonExited(Position position, ShipType shipType, Orientation orientation, Board board)throws ModelException;
	void placeAIShips(boolean visible);
	String getPlayerName();	
	String getAIName();
	int getPlayerScore();
	int getAIScore();
	void registerGameObserver(GameObserver o);
	void setPlayerName(String playerName);
	
}