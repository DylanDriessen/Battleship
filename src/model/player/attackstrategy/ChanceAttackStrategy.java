package model.player.attackstrategy;

import java.util.ArrayList;
import java.util.Random;

import exception.ModelException;
import model.board.Position;
import model.board.Ship;
import model.player.AI;

public class ChanceAttackStrategy implements AttackStrategy {

	private AI ai;
	private ArrayList<Ship> ships;
	private Ship currShip;
	private int currAttackPosition;
	private boolean enemyShipsCollected;
	
	public ChanceAttackStrategy(AI ai) {
		this.ai = ai;
		init();
	}
	
	public void init() {
		this.ships = null;
		this.currShip = null;
		this.currAttackPosition = 0;
		this.enemyShipsCollected = false;
	}

	@Override
	public void attack() {
		Random r = new Random();

		if(!this.enemyShipsCollected) {
			this.ships = getShips();
			this.enemyShipsCollected = true;
		}
		
		boolean succeeded = false;
		while (!succeeded) {

			int x, y;
			int chance = r.nextInt(5);
			
			if (currShip != null) {
				System.out.println("Destroying current ship");
				Position p = this.currShip.getPositions().get(this.currAttackPosition++);
				x = p.getX();
				y = p.getY();
			} else if (chance == 1) {
				System.out.println("AI was lucky and found your ship");
				Position p = getNewShipPosition();
				x = p.getX();
				y = p.getY();
			} else {
				System.out.println("AI attacks randomly");
				x = r.nextInt(10);
				y = r.nextInt(10);
			}

			try {
				if (this.ai.getEnemyBoard().attack(new Position(x, y))) { // returns true if ship was hit
					Ship ship = this.ai.getEnemyBoard().getBoardPositions()[x][y].getShip();
					if (! ship.equals(currShip)) {
						this.ships.remove(ship);
						this.currShip = ship;
						this.currAttackPosition = 0;
					} else if (ship.isSunk()) {
						this.currShip = null;
						this.currAttackPosition = 0;
					}
				}
				
				succeeded = true;
				System.out.println("AI attacked position a (" + x + "," + y + ")");

			} catch (ModelException ignored) {
				// Ignore
			} 
		}
	}
	
	@Override
	public void reset() {
		init();
	}
	
	private Position getNewShipPosition() {
		this.currShip = ships.remove(0);
		return this.currShip.getAnchor();
	}
	
	private ArrayList<Ship> getShips() {
		return this.ai.getEnemyBoard().getShips();
	}
	
}
