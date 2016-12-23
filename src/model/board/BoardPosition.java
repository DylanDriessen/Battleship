package model.board;

import model.enums.ButtonType;

/**
 * @author Mathias, Wouter & Dylan
 */
public class BoardPosition {
	
	private Ship ship;
	private boolean isPlayed = false;
	private ButtonType color = ButtonType.EMPTY;
	
	public BoardPosition() {}
	
	public boolean isPlayed() {
		return this.isPlayed;
	}
	
	public boolean isHit() {
		return this.containsShip() && this.isPlayed();
	}
	
	public boolean containsShip() {
		return this.ship != null;
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public void setShip(Ship ship, boolean visible) {
		this.ship = ship;
		if(visible) {
			setButtonType(ButtonType.OCCUPIED);
		}
	}
	
	public void removeShip() {
		this.ship = null;
		setButtonType(ButtonType.EMPTY);
	}
	
	public boolean attack() {
		setPlayed(true);
		if(this.ship != null) {
			this.ship.hitShip();
			setButtonType(ButtonType.SHOT_HIT);
			return true;
		} else {
			setButtonType(ButtonType.SHOT_MISSED);
			return false;
		}
	}
	
	public void sink() {
		if (this.ship.isSunk()) {
			setButtonType(ButtonType.SUNK);
		}
	}
	
	private void setPlayed(boolean value) {
		this.isPlayed = value;
	}
	
	public void setFocus(boolean focus) {
		if (focus) {
			this.setButtonType(ButtonType.FOCUS);
		} else {
			this.setButtonType(ButtonType.EMPTY);
		}
	}

	public ButtonType getButtonType() {
		return this.color;
	}
	
	private void setButtonType(ButtonType color) {
		this.color = color;
	}
}
