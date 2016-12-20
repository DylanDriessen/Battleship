package model.player.placestrategy;

import java.util.Random;

import exception.ModelException;
import model.Position;
import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;
import model.player.AI;

public class BorderPlaceStrategy implements PlaceStrategy {
	
	private AI ai;
	
	public BorderPlaceStrategy(AI ai) {
		this.ai = ai;
	}

	@Override
	public void placeShips(boolean visible) {
		
		Random r = new Random();
		
		int succeededCount = 0;
		while (succeededCount < 5){
			
			int shipTypeIndex = r.nextInt(ShipType.values().length);
			int orientationIndex = r.nextInt(Orientation.values().length);
			int x = 0;
			int y = 0;
			if(orientationIndex == 0){
				 x = Math.random() >= 0.5 ? 0:9;
				 y = r.nextInt(10); 
			} 	else if(orientationIndex == 1){
					x = r.nextInt(10);
					y = Math.random() >= 0.5 ? 0:9;
					
			}
			
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