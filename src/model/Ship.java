package model;

public class Ship {
	private int x;
	private int y;
	private Orientation orientation;
	private ShipType type;
	
	public Ship(ShipType type, int x, int y, Orientation orientation){
		this.type = type;
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public ShipType getType() {
		return type;
	}

		
}
