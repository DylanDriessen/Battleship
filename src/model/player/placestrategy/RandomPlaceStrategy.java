package model.player.placestrategy;

import java.util.Random;

import exception.ModelException;
import model.Ship;
import model.ShipFactory;
import model.enums.Orientation;
import model.enums.ShipType;

public class RandomPlaceStrategy implements PlaceStrategy {
	
	@Override
	public void placeShip() {
		
		Random r = new Random();
		
		int succeededCount = 0;
		while (succeededCount < 5){
			
			int shipTemplateIndex = r.nextInt(ShipType.values().length);
			int xCoord = r.nextInt(9)-1;
			int yCoord = r.nextInt(9)-1;
			int orientationIndex = r.nextInt(Orientation.values().length);
			
			Ship ship = new Ship(shipTemplateIndex, xCoord, yCoord, orientationIndex);
			
			try {
				//Ship plaatsen
				succeededCount++;
			} catch (ModelException ignored){
				
			}
		}
		
	}
	
	
	

}
