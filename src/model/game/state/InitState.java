package model.game.state;

import model.Game;
import model.Position;
import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;

public class InitState implements GameState {

	private Game game;
	
	public InitState(Game game) {
		this.game = game;
	}
	
	@Override
	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation) {	
		//TODO:
		// tell model to make a ship
		Ship ship = new Ship(shipType, new Position(x, y), orientation);
		
		// tell model that certain squares are occupied by a ship
		this.game.getBoard1().placeShip(ship);
		
		// since view observes model, it will draw the squares differently
	}

}
