package model.board;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import exception.ModelException;
import model.enums.Orientation;
import model.enums.ShipType;
import model.player.Player;

/**
 * @author Mathias, Wouter & Dylan
 */
public class Board implements BoardObservable {
	
	private List<BoardObserver> observers;
	private Map<ShipType, Integer> shipTypeCounter;
	private int shipCounter;
	private int maxNumberOfShips;
	private ArrayList<Position> changed;	
	private BoardPosition[][] boardPositions;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	private Player player;
	
	public Board(int maxNumberOfShips) {
		this.maxNumberOfShips = maxNumberOfShips;
		this.observers = new ArrayList<BoardObserver>();
		init();
	}
	
	public void init() {
		this.changed = new ArrayList<Position>();
		this.ships = new ArrayList<Ship>();
		this.shipCounter = 0;
		
		this.shipTypeCounter = new EnumMap<ShipType, Integer>(ShipType.class){{
			for(ShipType st : ShipType.values()) {
				put(st, 0);
			}
		}};
		
		this.boardPositions = new BoardPosition[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardPositions[i][j] = new BoardPosition();
				this.changed.add(new Position(i, j));
			}
		}
	}

	public void reset() {
		this.init();
	}
	
	/**
	 * Als de doorgegeven positie nog niet is aangevallen, zal deze aangevallen worden.
	 * 
	 * @param position
	 * 		De positie dat aangevallen wordt
	 * @return
	 * 		True bij het raken van een schip, anders False
	 * @throws ModelException
	 * 		Indien een positie aangevallen wordt dat al is aangevallen
	 */
	public boolean attack(Position position) throws ModelException {
		int x = position.getX();
		int y = position.getY();
		
		if (!boardPositions[x][y].isPlayed()) {
			this.setChangedButtons(new ArrayList<Position>());
			
			boolean hit = this.boardPositions[x][y].attack();
			if (hit) {
				this.player.decrementScore();
				
				if(this.boardPositions[x][y].getShip().isSunk()) {
					for (Position p : this.boardPositions[x][y].getShip().getPositions()) {
						this.boardPositions[p.getX()][p.getY()].sink();
						this.changed.add(new Position(p.getX(), p.getY()));	
					}
					
					this.shipCounter--;
					System.out.println("Schip gezonken.");
				}
			}
			
			this.changed.add(position);
			this.notifyObservers();
			return hit;
		} else {
			throw new ModelException("Je kan deze coördinaten niet opnieuw aanvallen.");
		}
	}
	
	/**
	 * Benadruk de gegeven positie
	 * 
	 * @param position
	 * 		De positie dat gefocused moet worden
	 * @param value
	 * 		Indien True, zal er gefocused worden, anders zal de focus verwijderd worden
	 */
	public void focus(Position position, boolean value) {
		int x = position.getX();
		int y = position.getY();
		
		if (!boardPositions[x][y].isPlayed()) {
			this.setChangedButtons(new ArrayList<Position>());
			
			this.boardPositions[x][y].setFocus(value);
			this.changed.add(position);
			
			this.notifyObservers();
		}
	}
	
	/**
	 * Methode dat een gegeven schip op het spelbord plaatst.
	 * 
	 * @param ship
	 * 		Het schip dat op het spelbord geplaatst moet worden
	 * @param ghost
	 * 		True om een tijdelijk ghostschip aan te duiden op het spelbord, 
	 * 		dit is een hulpmiddel om te tonen waar je de schepen kunt plaatsen.
	 * @param visible
	 * 		True als het schip zichtbaar is voor de user (schepen speler zijn zichtbaar, schepen AI niet)
	 * @throws ModelException
	 * 		Indien het schip op een ongeldige positie geplaatst wordt
	 * 		Indien het maximum aantal schepen op het spelbord is bereikt
	 * 		Indien het maximum aantal schepen van een type op het spelbord zijn geplaatst
	 */
	public void placeShip(Ship ship, boolean ghost, boolean visible) throws ModelException {
		if(this.shipCounter == this.maxNumberOfShips) {
			if(ghost) {
				return;
			} else {
				throw new ModelException("Je hebt het maximumaantal schepen bereikt.");
			}
		}
		if(maxAmountShipsReached(ship.getShipType())) {
			if(ghost) {
				return;
			} else {
				throw new ModelException("Je hebt te veel schepen van het type \"" + ship.getShipType().getName() + "\" geplaatst.");
			}
		}
		
		int x = ship.getAnchor().getX();
		int y = ship.getAnchor().getY();
		int length = ship.getShipType().getLength();
		this.setChangedButtons(new ArrayList<Position>());
		
		if(ship.getOrientation().equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				verifyEnvironment(i, y);
			}
			for(int i = x; i < x + length; i++) {
				changeBoardPositions(i, y, ship, ghost, visible);
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				changeBoardPositions(x, i, ship, ghost, visible);
			}
		}
		
		if(!ghost) {
			this.incrementCounters(ship.getShipType());
			this.ships.add(ship);
		}
		
		this.notifyObservers();
	}
	
	/**
	 * Verwijderd het schip, indien deze bestaat, op de gegeven positie.
	 * 
	 * @param position
	 * 		De positie waar het schip verwijderd moet worden
	 */
	public void removeShip(Position position) {
		int x = position.getX();
		int y = position.getY();
		
		if (this.boardPositions[x][y].containsShip()) {
			Ship ship = this.boardPositions[x][y].getShip();
			this.setChangedButtons(new ArrayList<Position>());
			this.decrementCounters(ship.getShipType());
			this.ships.remove(ship);
			for (Position p : ship.getPositions()) {
				this.boardPositions[p.getX()][p.getY()].removeShip();
				this.changed.add(new Position(p.getX(), p.getY()));	
			}
		}
		
		this.notifyObservers();
	}
	
	/**
	 * Update het spelbord (boardpositions)
	 * 
	 * @param x
	 * 		X-coördinaat spelbord
	 * @param y
	 * 		Y-coördinaat spelbord
	 * @param ship
	 * 		Schip dat op het bord geplaatst wordt
	 * @param ghost
	 * 		True indien het schip een ghostschip is
	 * @param visible
	 * 		True indien het schip zichtbaar is voor de user
	 */
	private void changeBoardPositions(int x, int y, Ship ship, boolean ghost, boolean visible) {
		if(ghost) {
			this.boardPositions[x][y].setFocus(visible);
		} else {
			this.boardPositions[x][y].setShip(ship, visible);
		}
		changed.add(new Position(x, y));
	}
		
	private boolean maxAmountShipsReached(ShipType shipType) {
		return this.shipTypeCounter.get(shipType) >= shipType.getAmount();
	}
	
	/**
	 * Controleer gegeven coördinaten om te zien of je er een schip kan plaatsen.
	 * 
	 * @param x
	 * 		X-coördinaat spelbord
	 * @param y
	 * 		Y-coördinaat spelbord
	 * @throws ModelException
	 * 		Indien het ongeldige coördinaten zijn voor een schip op te plaatsen.
	 */
	private void verifyEnvironment(int x, int y) throws ModelException {
		if(x > 9 || y > 9) {
			throw new ModelException("Je kan geen schip buiten het bord plaatsen.");
		}
		if(this.boardPositions[x][y].containsShip()) {
			throw new ModelException("Je kan geen schip bovenop een ander schip plaatsen.");
		}
		if(neighbourContainsShip(x, y)) {
			throw new ModelException("Je kan een schip niet zo dicht bij een naburig schip plaatsen.");
		}
	}
	
	/**
	 * Controleer of een schip grenst aan de doorgegeven coördinaten
	 * 
	 * @param x
	 * 		X-coördinaat spelbord
	 * @param y
	 * 		Y-coördinaat spelbord
	 * @return
	 * 		True indien er een schip aan deze positie grenst
	 */
	private boolean neighbourContainsShip(int x, int y) {
		boolean occupied = false;
		
		for(int i = x - 1; i <= x + 1 && !occupied; i++) {
			for(int j = y - 1; j <= y + 1 && !occupied; j++) {
				try {
					occupied = this.boardPositions[i][j].containsShip();
				} catch(ArrayIndexOutOfBoundsException e) {
					//Do nothing
				}
			}
		}
		
		return occupied;
	}
	
	private void incrementCounters(ShipType shipType) {
		Integer count = this.shipTypeCounter.get(shipType);
		count++;
		this.shipTypeCounter.put(shipType, count);
		this.shipCounter++;
	}
	
	private void decrementCounters(ShipType shipType) {
		Integer count = this.shipTypeCounter.get(shipType);
		count--;
		this.shipTypeCounter.put(shipType, count);
		this.shipCounter--;
	}
	
	//Observer
	@Override
	public void addObserver(BoardObserver o) {
		this.observers.add(o);
	}

	@Override
	public void removeObserver(BoardObserver o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(BoardObserver o : this.observers) {
			o.update(this);
		}
	}
	
	//Getters & setters
	public int getNbOfShips() {
		return this.shipCounter;
	}
	
	public int getNbOfShipTypeUsed(ShipType shipType) {
		return this.shipTypeCounter.get(shipType);
	}
	
	public BoardPosition[][] getBoardPositions() {
		return this.boardPositions.clone();
	}
	
	public ArrayList<Position> getChangedButtons() {
		return this.changed;
	}
	
	private void setChangedButtons(ArrayList<Position> changed) {
		this.changed = changed;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getShipCounter() {
		return shipCounter;
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}
}