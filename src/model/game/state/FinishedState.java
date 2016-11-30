package model.game.state;

import model.Game;
import model.enums.Orientation;
import model.enums.ShipType;

public class FinishedState implements GameState {

	private Game game;
	
	public FinishedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation) {

	}

}
