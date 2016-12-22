package model.enums;

import java.awt.Color;

/**
 * @author Mathias
 */
public enum ButtonType {
	EMPTY(new Color(165, 214, 254), true, ""), 
	FOCUS(new Color(67, 144, 178), false, ""), 
	OCCUPIED(new Color(255, 165, 58), false, ""), 
	SHOT_MISSED(new Color(165, 214, 254), true, "X"), 
	SHOT_HIT(new Color(255, 94, 59), false, ""),
	SUNK(new Color(40, 40, 70), false, "");
	
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
