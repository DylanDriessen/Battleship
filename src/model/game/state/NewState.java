package model.game.state;

import exception.ModelException;
import model.game.Game;
import model.board.Board;
import model.board.Position;
import model.board.Ship;
import model.enums.AttackStrategies;
import model.enums.Orientation;
import model.enums.PlaceStrategies;
import model.enums.ShipType;

public class NewState implements GameState {

	private Game game;
	
	public NewState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareLeftClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {	
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
		this.game.getPlayer().getMyBoard().placeShip(ship, false, true);
	}
	
	public void squareRightClicked(Position position, Board board) {
		if (!board.equals(this.game.getPlayer().getMyBoard())) {
			return;
		}
		
		this.game.getPlayer().getMyBoard().removeShip(position);
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
		
		Ship ship = new Ship(shipType, position, orientation);
		this.game.getPlayer().getMyBoard().placeShip(ship, true, true);
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
		
		Ship ship = new Ship(shipType, position, orientation);
		this.game.getPlayer().getMyBoard().placeShip(ship, true, false);
	}
	
	@Override
	public void changeStrategies(PlaceStrategies newPlaceStrategy, AttackStrategies newAttackStrategy) throws ModelException {
		this.game.getAI().changeStrategies(newPlaceStrategy, newAttackStrategy);
	}

}
