package model.player.placestrategy;

import exception.ModelException;

public class PlaceStrategyFactory {

	public static PlaceStrategy create(String strategy) throws ModelException {
		PlaceStrategy placeStrategy = null;
		
		switch(strategy) {
			case "random":
				placeStrategy = new RandomPlaceStrategy();
				break;
			default:
				throw new ModelException("Unknown placing strategy: could not create place strategy.");
		}
		
		return placeStrategy;
	}

}
