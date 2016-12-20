package model.player.attackstrategy;

import exception.ModelException;
import model.enums.AttackStrategies;
import model.player.AI;

public class AttackStrategyFactory {

	public static AttackStrategy create(AttackStrategies strategy, AI ai) throws ModelException {
		AttackStrategy attackStrategy = null;
		
		switch(strategy) {
			case RANDOM:
				attackStrategy = new RandomAttackStrategy(ai);
				break;
			case SMARTRANDOM:
				attackStrategy = new SmartRandomAttackStrategy(ai);
				break;
			case CHANCE:
				attackStrategy = new ChanceAttackStrategy(ai);
				break;
			default:
				throw new ModelException("Unknown attacking strategy: could not create place strategy.");
		}
		
		return attackStrategy;
	}
	
}
