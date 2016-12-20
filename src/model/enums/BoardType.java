package model.enums;

public enum BoardType {

	PLAYER("Player"), AI("AI");
	
	private String name;
	
	private BoardType(String name) {
		this.name = name;
	}
}
