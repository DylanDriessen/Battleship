package model.player.placestrategy;

import java.util.Random;

import exception.ModelException;
import model.Position;
import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;
import model.player.AI;

public class RandomPlaceStrategy implements PlaceStrategy {
	
	private AI ai;
	
	public RandomPlaceStrategy(AI ai) {
		this.ai = ai;
	}

	@Override
	public void placeShips() {
		
		Random r = new Random();
		
		int succeededCount = 0;
		while (succeededCount < 5){
			
			int shipTypeIndex = r.nextInt(ShipType.values().length);
			int x = r.nextInt(9);
			int y = r.nextInt(9);
			int orientationIndex = r.nextInt(Orientation.values().length);
			
			Position p = new Position(x, y);
			Ship ship = new Ship(ShipType.values()[shipTypeIndex], p , Orientation.values()[orientationIndex]);
			
			try {
				this.ai.getMyBoard().placeShip(ship, false);
				succeededCount++;
				System.out.println("AI placed a " + ship.getShipType().getName() + " on (" + x + "," + y + ") with orientation " + ship.getOrientation().getName());
			} catch (ModelException ignored){
				//Ignore
			}
		}
		
	}
	
	
	

}
