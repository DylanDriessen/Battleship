package model.player.attackstrategy;

/**
 * @author Mathias, Wouter & Dylan
 */
public interface AttackStrategy {

	void attack();
	default void reset() {
		return;
	}
	
}
