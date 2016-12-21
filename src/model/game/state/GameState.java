package model.game.state;

import exception.ModelException;
import model.Position;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface GameState {

	public void squareClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	public void squareEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	public void squareExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	default void finishGame() throws ModelException {
		throw new NotImplementedException();
	}

}
