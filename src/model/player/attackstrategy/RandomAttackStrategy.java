package model.player.attackstrategy;

import java.util.Random;

import exception.ModelException;
import model.Position;
import model.Ship;
import model.enums.Orientation;
import model.enums.ShipType;
import model.player.AI;

public class RandomAttackStrategy implements AttackStrategy {

	private AI ai;
	
	public RandomAttackStrategy(AI ai) {
		this.ai = ai;
	}

	@Override
	public void attack() {

		Random r = new Random();
		
		boolean succeeded = false;
		while (!succeeded){
			
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			
			//Position p = new Position(x, y);
			
			try {
				this.ai.getEnemyBoard().attack(new Position(x, y));
				succeeded = true;
				System.out.println("AI attacked position a (" + x + "," + y + ")");
				
			} catch (ModelException ignored){
				//Ignore
			}
		}

	}

}
