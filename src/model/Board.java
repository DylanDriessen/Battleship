package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import exception.ModelException;
import model.enums.ButtonType;
import model.enums.Orientation;
import model.enums.ShipType;
import model.Ship;

public class Board implements BoardObservable{
	
	private Player player;
	private List<BoardObserver> observers;
	private ButtonType[][] containsShip;
	private Map<ShipType, Integer> shipTypeCounter;
	private int shipCounter;
	private ArrayList<Position> changed = new ArrayList<Position>();
	
	public Board(String playerName) {
		this.player = new Player(playerName);
		this.containsShip = new ButtonType[10][10];
		this.observers = new ArrayList<BoardObserver>();
		this.shipTypeCounter = new EnumMap<ShipType, Integer>(ShipType.class){{
			for(ShipType st : ShipType.values()) {
				put(st, 0);
			}
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
				this.containsShip[i][y] = ButtonType.OCCUPIED;
				changed.add(new Position(i, y));
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.containsShip[x][i] = ButtonType.OCCUPIED;
				changed.add(new Position(x, i));
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
				this.containsShip[i][y] = ButtonType.FOCUS;
				changed.add(new Position(i, y));
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.containsShip[x][i] = ButtonType.FOCUS;
				changed.add(new Position(x, i));
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
				this.containsShip[i][y] = ButtonType.EMPTY;
				changed.add(new Position(i, y));
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.containsShip[x][i] = ButtonType.EMPTY;
				changed.add(new Position(x, i));
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
		if(this.containsShip[x][y] == ButtonType.OCCUPIED) {
			throw new ModelException("Je kan geen schip bovenop een ander schip plaatsen.");
		}
		if(neighbourContainsShip(x, y)) {
			throw new ModelException("Je kan een schip niet zo dicht bij een naburig schip plaatsen.");
		}
	}
		
	private boolean neighbourContainsShip(int x, int y) {
		ButtonType occupied = ButtonType.EMPTY;
		
		for(int i = x - 1; i <= x + 1 && !(occupied == ButtonType.OCCUPIED); i++) {
			for(int j = y - 1; j <= y + 1 && !(occupied == ButtonType.OCCUPIED); j++) {
				try {
					occupied = this.containsShip[i][j];
				} catch(ArrayIndexOutOfBoundsException e) {
					//Do nothing
				}
			}
		}
		
		return occupied == ButtonType.OCCUPIED;
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
	
	public ButtonType[][] getContainsShip() {
		return this.containsShip;
	}
	
	public ArrayList<Position> getChangedButtons() {
		return this.changed;
	}

	@Override
	public void addObserver(BoardObserver o) {
		this.observers.add(o);
	}

	@Override
	public void removeObserver(BoardObserver o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyBoardChanged() {
		for(BoardObserver o : this.observers) {
			o.boardChanged(this);
		}
	}

}
