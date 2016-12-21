package model.game.state;

import exception.ModelException;
import model.board.Board;
import model.board.Position;
import model.enums.AttackStrategies;
import model.enums.Orientation;
import model.enums.PlaceStrategies;
import model.enums.ShipType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface GameState {

	public void squareLeftClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	default void squareRightClicked(Position position, Board board) throws ModelException {
		return;
	}
	public void squareEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	public void squareExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException;
	default void finishGame() throws ModelException {
		throw new NotImplementedException();
	}
	default void changeStrategies(PlaceStrategies newPlaceStrategy, AttackStrategies newAttackStrategy) throws ModelException {
		return;
	}
}
