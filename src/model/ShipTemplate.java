package model;

public enum ShipTemplate {

	VLIEGDEK("Vliegdekschip", 5, 1),
	SLAG("Slagschip", 4, 2),
	ONDERZEE�R("Onderzee�r", 3, 3),
	TORPEDO("Torpedobootjager", 3, 3),
	PATROUILLE("Patrouilleschip", 2, 4);
	
	private String name;
	private int length, amount;
	
	ShipTemplate(String name, int length, int amount) {
		this.name = name;
		this.length = length;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}

	public int getAmount() {
		return amount;
	}
	
}