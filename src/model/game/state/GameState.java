package model.game.state;

import model.enums.Orientation;
import model.enums.ShipType;

public interface GameState {

	//TODO: write default methods (Fox will like that)
	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation);

}
