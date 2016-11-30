package view.combobox;

import model.enums.ShipType;

public class ComboItem {

	private String label;
	private ShipType shipType;
	
	public ComboItem(String label) {
		this(label, null);
	}
	
	public ComboItem(String label, ShipType shipType) {
		this.label = label;
		this.shipType = shipType;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
		
}
