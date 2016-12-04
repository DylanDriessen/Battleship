package model.enums;

import java.awt.Color;

public enum ButtonType {
	EMPTY(new Color(165, 214, 254), true, ""), 
	FOCUS(Color.GRAY, true, ""), 
	OCCUPIED(Color.RED, true, ""), 
	SHOT_MISSED(new Color(165, 214, 254), true, "X"), 
	SHOT_HIT(Color.MAGENTA, true, ""),
	SUNK(Color.BLACK, true, "");
	
	private Color color;
	private boolean border;
	private String text;
	
	ButtonType(Color color, boolean border, String text) {
		this.color = color;
		this.border = border;
		this.text = text;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public boolean hasBorder() {
		return this.border;
	}
	
	public String getText() {
		return this.text;
	}
}
