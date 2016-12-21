package model.player.placestrategy;

public interface PlaceStrategy {

	void placeShips(boolean visible);
	default void reset() {
		return;
	}
	
}
