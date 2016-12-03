package model.player;

import exception.ModelException;
import model.board.Board;
import model.player.placestrategy.PlaceStrategy;
import model.player.placestrategy.PlaceStrategyFactory;
import properties.PropertiesFile;

public class AI extends Player {
	
	private PlaceStrategy placeStrategy;
	
	public AI(PropertiesFile properties, Board myBoard, Board enemyBoard) throws ModelException {
		super("Computer", myBoard, enemyBoard);
		this.placeStrategy = PlaceStrategyFactory.create(properties.get("AI"), this);
	}

	public void placeShip() {
		this.placeStrategy.placeShip();
	}
}
