package model.player.placestrategy;

/**
 * @author Mathias, Wouter & Dylan
 */
public interface PlaceStrategy {

	void placeShips(boolean visible);
	default void reset() {
		return;
	}
	
}
