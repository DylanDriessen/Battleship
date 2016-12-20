package model.game.state;

import exception.ModelException;
import model.game.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
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
	public void squareClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {	
		if(shipType == null) {
			throw new ModelException("Gelieve een schip te kiezen.");
		}
		if(orientation == null) {
			throw new ModelException("Gelieve een orientatie te kiezen.");
		}
		if (!board.equals(this.game.getPlayer().getMyBoard())) {
			return;
		}
		
		Ship ship = new Ship(shipType, position, orientation);
		
		this.game.getPlayer().getMyBoard().placeShip(ship, true);
	}

	@Override
	public void squareEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if(shipType == null) {
			return;
		}
		if(orientation == null) {
			return;
		}
		if (!board.equals(this.game.getPlayer().getMyBoard())) {
			return;
		}
		
		this.game.getPlayer().getMyBoard().placeGhostShip(position, shipType, orientation, true);
	}

	@Override
	public void squareExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if(shipType == null) {
			return;
		}
		if(orientation == null) {
			return;
		}
		if (!board.equals(this.game.getPlayer().getMyBoard())) {
			return;
		}
		
		this.game.getPlayer().getMyBoard().placeGhostShip(position, shipType, orientation, false);
	}

	@Override
	public void finishGame() throws ModelException {
		throw new NotImplementedException();
	}

}
