package model.enums;

public enum AttackStrategies {
	RANDOM("random"), SMARTRANDOM("smartRandom"), CHANCE("chance");
	
	private String name;
	
	AttackStrategies(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
