package model;

public class Ship {
	private String name;
	private int length;
	private int x;
	private int y;
	private Orientation orientation;
	
	public Ship(String name, int length, int x, int y, Orientation orientation){
		this.name = name;
		this.length = length;
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
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
	
	
}
