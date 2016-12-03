package model.player.placestrategy;

import java.util.Random;

import exception.ModelException;
import model.Ship;
import model.ShipFactory;
import model.enums.Orientation;
import model.enums.ShipType;

public class RandomPlaceStrategy implements PlaceStrategy {
	
	@Override
	public void placeShip() throws ModelException {
		
		Random r = new Random();
		ShipFactory sf = new ShipFactory();
		
		int succeededCount = 0;
		while (succeededCount < 5){
			
			int shipTemplateIndex = r.nextInt(ShipType.values().length);
			int xCoord = r.nextInt(9);
			int yCoord = r.nextInt(9);
			int orientationIndex = r.nextInt(Orientation.values().length);
			
			Ship ship = ShipFactory.createShipFromTemplate(
					ShipType.values()[shipTemplateIndex], 
					xCoord, 
					yCoord, 
					Orientation.values()[orientationIndex]);
			
			try {
				//Ship plaatsen
				succeededCount++;
			} catch (Exception ignored){
				
			}
		}
		
	}
	
	
	

}
