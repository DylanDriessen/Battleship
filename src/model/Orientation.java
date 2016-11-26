package model;

public enum Orientation {

	VERTICAl("Vertical"), HORIZONTAL("Horizontal");
	
	private String name;
	
	Orientation(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
