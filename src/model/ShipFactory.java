package model;

import java.lang.reflect.Constructor;

import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;
import exception.ModelException;

public class ShipFactory {
	
	public static Ship createShipFromTemplate(ShipType shipType, int x, int y , Orientation orientation) throws ModelException{
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
			throw new ModelException("We konden je schip niet maken door een probleem met de applicatie!\n" + e.getMessage());
		}
		
	}		
}


