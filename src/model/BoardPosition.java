package model;

public class BoardPosition {

	private boolean isPlayed = false;
	private Ship ship = null;
	
	public boolean containsShip() {
		return this.ship != null;
	}
	
	public boolean isPlayed() {
		return this.isPlayed;
	}
	
	public boolean isHit() {
		return containsShip() && isPlayed();
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public void setPlayed() {
		setPlayed(true);
		if(this.ship != null) {
			this.ship.hitShip();
		}
	}
	
	private void setPlayed(boolean value) {
		this.isPlayed = value;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
}
