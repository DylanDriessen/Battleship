package model.enums;

import java.awt.Color;

public enum ButtonType {
	EMPTY(new Color(165, 214, 254)), 
	FOCUS(Color.GRAY), 
	OCCUPIED(Color.RED), 
	SHOT_MISSED(Color.BLUE), 
	SHOT_HIT(Color.MAGENTA);
	
	private Color color;
	
	ButtonType(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
}
