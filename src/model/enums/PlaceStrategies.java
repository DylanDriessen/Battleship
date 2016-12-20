package model.enums;

public enum PlaceStrategies {
	RANDOM("random"), BORDER("border");
	
	private String name;
	
	PlaceStrategies(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
