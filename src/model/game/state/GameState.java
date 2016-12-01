package model.game.state;

import exception.ModelException;
import model.enums.Orientation;
import model.enums.ShipType;

public interface GameState {

	//TODO: write default methods (Fox will like that)
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation) throws ModelException;
	public void squareEntered(int x, int y, ShipType shipType, Orientation orientation) throws ModelException;
	public void squareExited(int x, int y, ShipType shipType, Orientation orientation) throws ModelException;

}
