package model.facade;

import exception.ModelException;
import model.game.Game;
import model.game.GameObserver;
import model.Position;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;

public class ModelFacade implements IModelFacade {
	
	private Game game;

	public ModelFacade(Game game){
		this.game = game;
	}
	
	@Override
	public Board getBoardPlayer() {
		return this.game.getPlayer().getMyBoard();
	}
	
	@Override
	public Board getBoardAI() {
		return this.game.getAI().getMyBoard();
	}
	
	@Override
	public void startGame() throws ModelException {
		this.game.startGame();
	}
	
	@Override
	public void buttonClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonClicked(position, shipType, orientation, board);
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
	public void placeAIShips(boolean visible) {
		this.game.getAI().placeShips(visible);
	}
	
	@Override
	public String getPlayerName() {
		return this.game.getPlayer().getName();
	}
	
	@Override
	public String getAIName() {
		return this.game.getAI().getName();
	}
	
	@Override
	public int getPlayerScore() {
		return this.game.getPlayer().getScore();
	}
	
	@Override
	public int getAIScore() {
		return this.game.getAI().getScore();
	}
	
	@Override
	public void registerGameObserver(GameObserver o) {
		this.game.addObserver(o);
	}
	
	@Override
	public void setPlayerName(String playerName) {
		this.game.getPlayer().setName(playerName);
	}
	
}
