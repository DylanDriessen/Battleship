package model.player.attackstrategy;

import java.util.Random;

import exception.ModelException;
import model.board.Position;
import model.player.AI;

/**
 * @author Dylan
 */
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
			
			Position p = new Position(x, y);
			
			try {
				this.ai.getEnemyBoard().attack(p);
				succeeded = true;
				System.out.println("AI attacked position a (" + x + "," + y + ")");
				
			} catch (ModelException ignored){
				//Ignore
			}
		}

	}

}
