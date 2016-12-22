package model.player.placestrategy;

import exception.ModelException;
import model.enums.PlaceStrategies;
import model.player.AI;

/**
 * @author Mathias, Wouter & Dylan
 */
public class PlaceStrategyFactory {

	public static PlaceStrategy create(PlaceStrategies strategy, AI ai) throws ModelException {
		PlaceStrategy placeStrategy = null;
		
		switch(strategy) {
			case RANDOM:
				placeStrategy = new RandomPlaceStrategy(ai);
				break;
			case BORDER:
				placeStrategy = new BorderPlaceStrategy(ai);
				break;
			default:
				throw new ModelException("Unknown placing strategy: could not create place strategy.");
		}
		
		return placeStrategy;
	}

}
