package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import exception.ModelException;
import model.enums.Orientation;
import model.enums.ShipType;
import model.Ship;

public class Board implements BoardObservable{
	
	private Player player;
	private List<BoardObserver> observers;
	private int[][] containsShip;
	private Map<ShipType, Integer> shipTypeCounter;
	private int shipCounter;
	
	public Board(String playerName) {
		this.player = new Player(playerName);
		this.containsShip = new int[10][10];
		this.observers = new ArrayList<BoardObserver>();
		this.shipTypeCounter = new EnumMap<ShipType, Integer>(ShipType.class){{
			put(ShipType.ONDERZEEËR, 0);
			put(ShipType.PATROUILLE, 0);
			put(ShipType.SLAG, 0);
			put(ShipType.TORPEDO, 0);
			put(ShipType.VLIEGDEK, 0);
		}};
	}
	
	public void placeShip(Ship ship) throws ModelException {
		if(this.shipCounter == 5) {
			throw new ModelException("Je hebt het maximumaantal schepen bereikt.");
		}
		if(maxAmountShipsReached(ship.getShipType())) {
			throw new ModelException("Je hebt te veel schepen van het type \"" + ship.getShipType().getName() + "\" geplaatst.");
		}
		
		int x = ship.getAnchor().getX();
		int y = ship.getAnchor().getY();
		int length = ship.getShipType().getLength();
		
		if(ship.getOrientation().equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				verifyEnvironment(i, y);
			}
			for(int i = x; i < x + length; i++) {
				this.containsShip[i][y] = 2;
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.containsShip[x][i] = 2;
			}
		}
		
		this.incrementCounters(ship.getShipType());	
		this.notifyBoardChanged();
	}
	
	public void placeGhostShip(int x, int y, ShipType type, Orientation orientation) throws ModelException {
		if(this.shipCounter == 5) {
			return;
		}
		if(maxAmountShipsReached(type)) {
			return;
		}
		
		int length = type.getLength();
		
		if(orientation.equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				verifyEnvironment(i, y);
			}
			for(int i = x; i < x + length; i++) {
				this.containsShip[i][y] = 1;
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.containsShip[x][i] = 1;
			}
		}
		
		this.notifyBoardChanged();
	}
	
	public void removeGhostShip(int x, int y, ShipType type, Orientation orientation) throws ModelException {
		int length = type.getLength();
		
		if(orientation.equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				verifyEnvironment(i, y);
			}
			for(int i = x; i < x + length; i++) {
				this.containsShip[i][y] = 0;
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.containsShip[x][i] = 0;
			}
		}
		
		this.notifyBoardChanged();
	}
	
	private boolean maxAmountShipsReached(ShipType shipType) {
		return this.shipTypeCounter.get(shipType) >= shipType.getAmount();
	}
	
	private void verifyEnvironment(int x, int y) throws ModelException {
		if(x > 9 || y > 9) {
			throw new ModelException("Je kan geen schip buiten het bord plaatsen.");
		}
		if(this.containsShip[x][y] == 2) {
			throw new ModelException("Je kan geen schip bovenop een ander schip plaatsen.");
		}
		if(neighbourContainsShip(x, y)) {
			throw new ModelException("Je kan een schip niet zo dicht bij een naburig schip plaatsen.");
		}
	}
		
	private boolean neighbourContainsShip(int x, int y) {
		int occupied = 0;
		
		for(int i = x - 1; i <= x + 1 && !(occupied == 2); i++) {
			for(int j = y - 1; j <= y + 1 && !(occupied == 2); j++) {
				try {
					occupied = this.containsShip[i][j];
				} catch(ArrayIndexOutOfBoundsException e) {
					//Do nothing
				}
			}
		}
		
		return occupied == 2;
	}
	
	private void incrementCounters(ShipType shipType) {
		Integer count = this.shipTypeCounter.get(shipType);
		count++;
		this.shipTypeCounter.put(shipType, count);
		this.shipCounter++;
	}
	
	//Getters
	public Player getPlayer() {
		return this.player;
	}
	
	public int[][] getContainsShip() {
		return containsShip;
	}

	@Override
	public void addObserver(BoardObserver o) {
		this.observers.add(o);
		
	}

	@Override
	public void removeObserver(BoardObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyBoardChanged() {
		for(BoardObserver o : this.observers) {
			o.boardChanged(this);
		}
	}

}
