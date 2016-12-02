package model.player;

import exception.ModelException;
import model.player.placestrategy.PlaceStrategy;
import model.player.placestrategy.PlaceStrategyFactory;
import properties.PropertiesFile;

public class AI extends Player {
	
	private PlaceStrategy placeStrategy;
	
	public AI(PropertiesFile properties) throws ModelException {
		super("Computer");
		
		this.placeStrategy = PlaceStrategyFactory.create(properties.get("AI"));
	}
	
	public void placeShip() {
		this.placeStrategy.placeShip();
	}
}
