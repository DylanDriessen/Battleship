package model.game.state;

import exception.ModelException;
import model.game.Game;
import model.board.Board;
import model.board.Position;
import model.enums.Orientation;
import model.enums.ShipType;

public class FinishedState implements GameState {

	private Game game;
	
	public FinishedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareLeftClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {		
		return;
	}

	@Override
	public void squareEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		return;
	}

	@Override
	public void squareExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		return;
	}

	@Override
	public void finishGame() throws ModelException {
		this.game.notifyObservers();
		this.game.resetGame();
		this.game.setState(this.game.getNewState());
	}

}
