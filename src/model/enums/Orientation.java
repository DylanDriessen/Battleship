package model.enums;

public enum Orientation {
	VERTICAL("Verticaal"), HORIZONTAL("Horizontaal");
	
	private String name;
	
	Orientation(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static Orientation checkOrientation(String value) {
		if(value.toLowerCase().equals("verticaal")) {
			return Orientation.VERTICAL;
		} else if(value.toLowerCase().equals("horizontaal")) {
			return Orientation.HORIZONTAL;
		} else {
			throw new IllegalArgumentException(value + " is not a valid orientation.");
		}
	}
	
}
