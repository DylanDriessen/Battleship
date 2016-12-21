package model.player.attackstrategy;

public interface AttackStrategy {

	void attack();
	default void reset() {
		return;
	}
	
}
