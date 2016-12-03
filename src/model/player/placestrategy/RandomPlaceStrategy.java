package model.player.placestrategy;

import java.util.Random;

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
	public void placeShip() {
		
		Random r = new Random();
		
		int succeededCount = 0;
		while (succeededCount < 5){
			
			int shipTypeIndex = r.nextInt(ShipType.values().length)-1;
			int xCoord = r.nextInt(9);
			int yCoord = r.nextInt(9);
			int orientationIndex = r.nextInt(Orientation.values().length)-1;
			
			Position p = new Position(xCoord, yCoord);
			Ship ship = new Ship(ShipType.values()[shipTypeIndex], p , Orientation.values()[orientationIndex]);
			
			try {
				//Ship plaatsen
				succeededCount++;
			} catch (Exception ignored){
				
			}
		}
		
	}
	
	
	

}
