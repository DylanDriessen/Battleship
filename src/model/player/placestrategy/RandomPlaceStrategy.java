package model.player.placestrategy;

import java.util.Random;

import exception.ModelException;
import model.board.Position;
import model.board.Ship;
import model.enums.Orientation;
import model.enums.ShipType;
import model.player.AI;

/**
 * @author Mathias, Wouter & Dylan
 */
public class RandomPlaceStrategy implements PlaceStrategy {
	
	private AI ai;
	
	public RandomPlaceStrategy(AI ai) {
		this.ai = ai;
	}

	@Override
	public void placeShips(boolean visible) {
		
		Random r = new Random();
		
		int succeededCount = 0;
		while (succeededCount < 5){
			
			int shipTypeIndex = r.nextInt(ShipType.values().length);
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			int orientationIndex = r.nextInt(Orientation.values().length);
			
			Position p = new Position(x, y);
			Ship ship = new Ship(ShipType.values()[shipTypeIndex], p , Orientation.values()[orientationIndex]);
			
			try {
				this.ai.getMyBoard().placeShip(ship, false, visible);
				succeededCount++;
				System.out.println("AI placed a " + ship.getShipType().getName() + " on (" + x + "," + y + ") with orientation " + ship.getOrientation().getName());
			} catch (ModelException ignored){
				//Ignore
			}
		}
		
	}
}
