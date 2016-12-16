package model.player.attackstrategy;

import exception.ModelException;
import model.player.AI;

public class AttackStrategyFactory {

	public static AttackStrategy create(String strategy, AI ai) throws ModelException {
		AttackStrategy attackStrategy = null;
		
		switch(strategy) {
			case "random":
				attackStrategy = new RandomAttackStrategy(ai);
				break;
			case "smartRandom":
				attackStrategy = new SmartRandomAttackStrategy(ai);
				break;
			case "chance":
				attackStrategy = new ChanceAttackStrategy(ai);
				break;
			default:
				throw new ModelException("Unknown attacking strategy: could not create place strategy.");
		}
		
		return attackStrategy;
	}
	
}
