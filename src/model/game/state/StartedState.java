package model.game.state;

import model.Game;
import model.enums.Orientation;
import model.enums.ShipType;

public class StartedState implements GameState {

	private Game game;
	
	public StartedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation) {
		
		
	}

}
