package model.enums;

public enum AttackStrategies {
	RANDOM("random", "Willekeurig"), SMARTRANDOM("smartRandom", "Mensachtig"), CHANCE("chance", "Superkrachtig");
	
	private String name, label;
	
	AttackStrategies(String name, String label) {
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
