package model.enums;

/**
 * @author Wouter & Dylan
 */
public enum ShipType {
	VLIEGDEK("Vliegdekschip", "Vliegdekschepen", 5, 1),
	SLAG("Slagschip", "Slagschepen", 4, 2),
	ONDERZEEËR("Onderzeeër", "Onderzeeërs", 3, 3),
	TORPEDO("Torpedobootjager", "Torpedobootjagers", 3, 3),
	PATROUILLE("Patrouilleschip", "Patrouilleschepen", 2, 4);
	
	private String name, plural;
	private int length, amount;
	
	ShipType(String name, String plural, int length, int amount) {
		this.name = name;
		this.plural = plural;
		this.length = length;
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}
	
	public String getPlural() {
		return this.plural;
	}

	public int getLength() {
		return this.length;
	}

	public int getAmount() {
		return this.amount;
	}
	
	@Override
	public String toString(){
		return this.name;
	}

	
}
