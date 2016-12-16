package model.game.state;

import exception.ModelException;
import model.game.Game;
import model.Position;
import model.Ship;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;

public class NewState implements GameState {

	private Game game;
	
	public NewState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {	
		if(shipType == null) {
			throw new ModelException("Gelieve een schip te kiezen.");
		}
		if(orientation == null) {
			throw new ModelException("Gelieve een orientatie te kiezen.");
		}
		if (!board.equals(this.game.getPlayer().getMyBoard())) {
			return;
		}
		
		Ship ship = new Ship(shipType, new Position(x, y), orientation);
		
		this.game.getPlayer().getMyBoard().placeShip(ship, true);
		
		// since view observes model, it will draw the squares differently
	}

	@Override
	public void squareEntered(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if(shipType == null) {
			return;
		}
		if(orientation == null) {
			return;
		}
		if (!board.equals(this.game.getPlayer().getMyBoard())) {
			return;
		}
		
		this.game.getPlayer().getMyBoard().placeGhostShip(x, y, shipType, orientation);
	}

	@Override
	public void squareExited(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if(shipType == null) {
			return;
		}
		if(orientation == null) {
			return;
		}
		if (!board.equals(this.game.getPlayer().getMyBoard())) {
			return;
		}
		
		this.game.getPlayer().getMyBoard().removeGhostShip(x, y, shipType, orientation);
	}

}
