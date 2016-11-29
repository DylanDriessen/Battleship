package model;

import model.enums.Orientation;
import model.enums.ShipType;

public class Ship {
	
	private final ShipType shipType;
	private final Position anchor;
	private final Orientation orientation;
	
	public Ship(ShipType type, Position position, Orientation orientation){
		this.shipType = type;
		this.anchor = position;
		this.orientation = orientation;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public Position getAnchor() {
		return anchor;
	}

	public Orientation getOrientation() {
		return orientation;
	}
		
}
