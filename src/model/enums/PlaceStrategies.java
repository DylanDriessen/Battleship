package model.enums;

public enum PlaceStrategies {
	RANDOM("random", "Willekeurig"), BORDER("border", "Aan de rand");
	
	private String name, label;
	
	PlaceStrategies(String name, String label) {
		this.name = name;
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public String getName() {
		return this.name();
	}
	
	@Override
	public String toString(){
		return this.label;
	}
}
