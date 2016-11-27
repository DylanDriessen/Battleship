package model;

import java.lang.reflect.Constructor;

import model.Ship;
import exception.DomainException;
import model.ShipType;

public class ShipFactory {
	
	public static Ship createShipFromTemplate(ShipType shipType, int x, int y , Orientation orientation) throws DomainException{
		try {
			//Get fully qualified path name
			String type = shipType.getName();
			
			//Create class
			Class<?> productClass = Class.forName(type);
			
			//Get constructor for object
			Constructor<?> constructor = productClass.getConstructor(ShipType.class, int.class, int.class, Orientation.class);
			
			//Get instance using constructor
			Object shipO = constructor.newInstance(type, x, y, orientation);
			
			//Cast to ship and return
			Ship ship = (Ship) shipO;
			return ship;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DomainException("Could not create your ship because of factory issue.\n" + e.getMessage());
		}
		
	}		
}


