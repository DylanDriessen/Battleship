package model.enums;

public enum Orientation {

	VERTICAL("Vertical"), HORIZONTAL("Horizontal");
	
	private String name;
	
	Orientation(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
