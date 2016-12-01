package model.game.state;

import exception.ModelException;
import model.Game;
import model.Position;
import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;

public class NewState implements GameState {

	private Game game;
	
	public NewState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation) throws ModelException {	
		if(shipType == null) {
			throw new ModelException("Gelieve een schip te kiezen.");
		}
		if(orientation == null) {
			throw new ModelException("Gelieve een orientatie te kiezen.");
		}
		
		Ship ship = new Ship(shipType, new Position(x, y), orientation);
		
		this.game.getBoard1().placeShip(ship);
		
		// since view observes model, it will draw the squares differently
	}

	@Override
	public void squareEntered(int x, int y, ShipType shipType, Orientation orientation) throws ModelException {
		if(shipType == null) {
			return;
		}
		if(orientation == null) {
			return;
		}

		this.game.getBoard1().placeGhostShip(x, y, shipType, orientation);
	}

	@Override
	public void squareExited(int x, int y, ShipType shipType, Orientation orientation) throws ModelException {
		if(shipType == null) {
			return;
		}
		if(orientation == null) {
			return;
		}
		
		this.game.getBoard1().removeGhostShip(x, y, shipType, orientation);
	}

}
