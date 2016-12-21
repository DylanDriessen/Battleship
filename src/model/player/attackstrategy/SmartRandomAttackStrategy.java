package model.player.attackstrategy;

import java.util.Random;

import exception.ModelException;
import model.board.Position;
import model.board.Ship;
import model.enums.Direction;
import model.player.AI;

public class SmartRandomAttackStrategy implements AttackStrategy {

	private AI ai;
	private Position lastHit = null;
	private Direction direction;
	private boolean found = false;
	
	public SmartRandomAttackStrategy(AI ai) {
		this.ai = ai;
	}

	@Override
	public void attack() {
		
		Random r = new Random();
		
		boolean succeeded = false;
		while (!succeeded){
			
			int x, y;
			
			if (found) { // ship direction found
				System.out.println("found ship direction");
				x = lastHit.getX() + direction.getX();
				y = lastHit.getY() + direction.getY();
			} else if (lastHit != null) { // ship found but direction unknown
				System.out.println("searching ship direction");
				this.direction = Direction.randomDirection();
				x = lastHit.getX() + direction.getX();
				y = lastHit.getY() + direction.getY();
			} else { // no ship found
				System.out.println("attacking random position");
				x = r.nextInt(10);
				y = r.nextInt(10);
			}

			try {
				if (this.ai.getEnemyBoard().attack(new Position(x, y))) { // returns true if ship was hit
					shipWasHit(x, y);
				} else if (found) {
					changeDirection();
				}
				succeeded = true;
				System.out.println("AI attacked position a (" + x + "," + y + ")");
				
			} catch (ModelException ignored){
				// ignored
			} catch (ArrayIndexOutOfBoundsException e) {
				if (found) {
					changeDirection();
				}
			}
		}
	}
	
	public void changeDirection() {
		Ship ship = this.ai.getEnemyBoard().getBoardPositions()[lastHit.getX()][lastHit.getY()].getShip();
		int backTrack = ship.getTimesHit() - 1;
		this.direction = Direction.oppositeDirection(this.direction);
		
		if (this.direction == Direction.EAST || this.direction == Direction.WEST)
			this.lastHit = new Position(lastHit.getX() + direction.getX() * backTrack, lastHit.getY());
		else {
			this.lastHit = new Position(lastHit.getX(), lastHit.getY() + direction.getY() * backTrack);
		}
	}
	
	public void shipWasHit(int x, int y) {
		Ship ship = this.ai.getEnemyBoard().getBoardPositions()[x][y].getShip();
		if (ship.getTimesHit() > 1 && !ship.isSunk()) {
			this.lastHit = new Position(x, y);
			this.found = true;
		} else if (ship.isSunk()) {
			this.lastHit = null;
			this.found = false;
		} else if (lastHit == null) {
			this.lastHit = new Position(x, y);
			this.found = false;
		}
	}
}
