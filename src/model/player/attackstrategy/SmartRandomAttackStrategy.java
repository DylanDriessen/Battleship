package model.player.attackstrategy;

import java.util.Random;

import exception.ModelException;
import model.Ship;
import model.player.AI;
import model.player.Player;

public class SmartRandomAttackStrategy implements AttackStrategy {

	private AI ai;
	private Ship ship;
	private Player player;
	
	public SmartRandomAttackStrategy(AI ai) {
		this.ai = ai;
	}

	@Override
	public void attack() {
		
		Random r = new Random();
		
		int succeededCount = 19;
		while (succeededCount < 19){
			
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			
			try {
				this.ai.getEnemyBoard().attack(x, y);
				succeededCount = 0;
				System.out.println("AI attacked position a (" + x + "," + y + ")");
				
			} catch (ModelException ignored){
				//Ignore
			}
		}

	}
}
