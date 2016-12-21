package model.player;

import exception.ModelException;
import model.board.Board;
import model.enums.AttackStrategies;
import model.enums.PlaceStrategies;
import model.player.attackstrategy.AttackStrategy;
import model.player.attackstrategy.AttackStrategyFactory;
import model.player.placestrategy.PlaceStrategy;
import model.player.placestrategy.PlaceStrategyFactory;
import properties.PropertiesFile;

public class AI extends Player {
	
	private PlaceStrategy placeStrategy;
	private AttackStrategy attackStrategy;
	
	public AI(PropertiesFile properties, Board myBoard, Board enemyBoard) throws ModelException {
		super("Computer", myBoard, enemyBoard);
		
		PlaceStrategies placeProperty = PlaceStrategies.valueOf(properties.get("placeStrategyAI").toUpperCase());
		AttackStrategies attackProperty = AttackStrategies.valueOf(properties.get("attackStrategyAI").toUpperCase());
		this.placeStrategy = PlaceStrategyFactory.create(placeProperty, this);
		this.attackStrategy = AttackStrategyFactory.create(attackProperty, this);
	}
	
	public void changeStrategies(PlaceStrategies newPlaceStrategy, AttackStrategies newAttackStrategy) throws ModelException {
		this.placeStrategy = PlaceStrategyFactory.create(newPlaceStrategy, this);
		this.attackStrategy = AttackStrategyFactory.create(newAttackStrategy, this);
		resetStrategies();
	}

	public void placeShips(boolean visible) {
		this.placeStrategy.placeShips(visible);
	}

	public void attack() {
		this.attackStrategy.attack();
	}
	
	@Override
	public void reset() {
		super.reset();
		resetStrategies();
	}
	
	private void resetStrategies() {
		this.placeStrategy.reset();
		this.attackStrategy.reset();
	}
}
