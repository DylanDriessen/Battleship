package model.enums;

import java.awt.Color;

/**
 * @author Mathias
 */
public enum ButtonType {
	EMPTY(new Color(165, 214, 254), true, "", null), 
	FOCUS(new Color(67, 144, 178), false, "", null), 
	OCCUPIED(new Color(255, 165, 58), false, "", "Rechtermuisklik om dit schip te verwijderen"), 
	SHOT_MISSED(new Color(165, 214, 254), true, "X", null), 
	SHOT_HIT(new Color(255, 94, 59), false, "", null),
	SUNK(new Color(40, 40, 70), false, "", null);
	
	private Color color;
	private boolean border;
	private String text;
	private String tooltip;
	
	ButtonType(Color color, boolean border, String text, String tooltip) {
		this.color = color;
		this.border = border;
		this.text = text;
		this.tooltip = tooltip;
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
	
	public String getToolTip() {
		return this.tooltip;
	}
}
