package model.board;

import java.util.ArrayList;

import model.enums.Orientation;
import model.enums.ShipType;

public class Ship {
	
	private int timesHit = 0;

	private final ShipType shipType;
	private final Position anchor;
	private final Orientation orientation;
	
	public Ship(ShipType type, Position position, Orientation orientation){
		this.shipType = type;
		this.anchor = position;
		this.orientation = orientation;
	}
	
	public int getTimesHit() {
		return this.timesHit;
	}
	
	public void hitShip() {
		this.timesHit++;
	}
	
	public boolean isSunk() {
		return this.timesHit == this.shipType.getLength();
	}

	public ShipType getShipType() {
		return shipType;
	}

	public Position getAnchor() {
		return anchor;
	}
	
	/**
	 * Geef al de coördinaten terug waar dit schip zich op bevind.
	 * 
	 * @return
	 * 		lijst van coördinaten waar het schip zich op bevind.
	 */
	public ArrayList<Position> getPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		
		int x = this.getAnchor().getX();
		int y = this.getAnchor().getY();
		int length = this.getShipType().getLength();
		
		if(this.getOrientation().equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				positions.add(new Position(i, y));
			}
		} else {
			for(int i = y; i < y + length; i++) {
				positions.add(new Position(x, i));
			}
		}
		
		return positions;
	}

	public Orientation getOrientation() {
		return orientation;
	}
		
}
