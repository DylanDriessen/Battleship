package model.facade;

import exception.ModelException;
import model.game.Game;
import model.game.GameObserver;
import view.GamePanel;
import model.Position;
import model.board.Board;
import model.board.BoardObserver;
import model.enums.Orientation;
import model.enums.ShipType;

public class ModelFacade {
	
	private Game game;

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
	
	public void buttonClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonClicked(position, shipType, orientation, board);
	}
	
	public void buttonEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonEntered(position, shipType, orientation, board);
	}
	
	public void buttonExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonExited(position, shipType, orientation, board);
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

	public void setPlayerName(String playerName) {
		this.game.getPlayer().setName(playerName);
	}

	public void registerBoardAIObserver(BoardObserver panelAI) {
		this.game.getAI().getMyBoard().addObserver(panelAI);
	}

	public void registerBoardPlayerObserver(BoardObserver panelPlayer) {
		this.game.getPlayer().getMyBoard().addObserver(panelPlayer);
	}
	
}
