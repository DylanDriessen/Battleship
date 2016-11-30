package model.game.state;

import model.Game;
import model.enums.Orientation;
import model.enums.ShipType;

public class BattleState implements GameState {

	private Game game;
	
	public BattleState(Game game) {
		this.game = game;
	}
	
	@Override
	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation) {
		
		
	}

}
