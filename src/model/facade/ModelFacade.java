package model.facade;

import exception.ModelException;
import model.game.Game;
import model.game.GameObserver;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;

public class ModelFacade {
	
	private Game game;

	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ModelFacade(Game game){
		this.game = game;
	}
	
	public Board getBoardPlayer() {
		return this.game.getPlayer().getMyBoard();
	}
	
	public Board getBoardAI() {
		return this.game.getAI().getMyBoard();
	}
	
	public void startGame() throws ModelException {
		this.game.startGame();
	}
	
	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonClicked(x, y, shipType, orientation, board);
	}
	
	public void buttonEntered(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonEntered(x, y, shipType, orientation, board);
	}
	
	public void buttonExited(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonExited(x, y, shipType, orientation, board);
	}

	public void placeAIShips(boolean visible) {
		this.game.getAI().placeShips(visible);
	}
	
	public String getPlayerName() {
		return this.game.getPlayer().getName();
	}
	
	public String getAIName() {
		return this.game.getAI().getName();
	}

	public int getPlayerScore() {
		return this.game.getPlayer().getScore();
	}
	
	public int getAIScore() {
		return this.game.getAI().getScore();
	}
	
	public void registerGameObserver(GameObserver o) {
		this.game.addObserver(o);
	}
	
}
