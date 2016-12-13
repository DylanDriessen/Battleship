package model.player;

import exception.ModelException;
import model.board.Board;
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
		this.placeStrategy = PlaceStrategyFactory.create(properties.get("placeStrategyAI"), this);
		this.attackStrategy = AttackStrategyFactory.create(properties.get("attackStrategyAI"), this);
	}

	public void placeShips(boolean visible) {
		this.placeStrategy.placeShips(visible);
	}

	public void attack() {
		this.attackStrategy.attack();
	}
}
