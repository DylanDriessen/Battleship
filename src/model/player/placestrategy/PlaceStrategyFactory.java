package model.player.placestrategy;

import exception.ModelException;
import model.player.AI;

public class PlaceStrategyFactory {

	public static PlaceStrategy create(String strategy, AI ai) throws ModelException {
		PlaceStrategy placeStrategy = null;
		
		switch(strategy) {
			case "random":
				placeStrategy = new RandomPlaceStrategy(ai);
				break;
			default:
				throw new ModelException("Unknown placing strategy: could not create place strategy.");
		}
		
		return placeStrategy;
	}

}
