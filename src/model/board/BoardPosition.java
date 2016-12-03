package model.board;

import java.awt.Color;

import model.Ship;

public class BoardPosition {
	
	private Ship ship;
	private boolean isPlayed = false;
	private Color color = new Color(165, 214, 254);
	
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
	
	public void setShip(Ship ship) {
		this.ship = ship;
		setColor(Color.RED);
	}
	
	public void setPlayed() {
		setPlayed(true);
		if(this.ship != null) {
			this.ship.hitShip();
			if (this.ship.isSunk()) {
				setColor(Color.BLACK);
			} else {
				setColor(Color.MAGENTA);
			}
		} else {
			setColor(Color.BLUE);
		}
	}
	
	private void setPlayed(boolean value) {
		this.isPlayed = value;
	}
	
	public void setFocus(boolean focus) {
		if (focus) {
			this.setColor(Color.GRAY);
		} else {
			this.setColor(new Color(165, 214, 254));
		}
	}

	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
