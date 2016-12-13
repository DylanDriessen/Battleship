package model.board;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import exception.ModelException;
import model.enums.ButtonType;
import model.enums.Orientation;
import model.enums.ShipType;
import model.player.Player;
import model.player.placestrategy.RandomPlaceStrategy;
import model.Position;
import model.Ship;

public class Board implements BoardObservable{
	
	private List<BoardObserver> observers;
	private Map<ShipType, Integer> shipTypeCounter;
	private int shipCounter;
	private ArrayList<Position> changed;	
	private BoardPosition[][] boardPositions;
	private Player player;
	
	public Board() {
		this.observers = new ArrayList<BoardObserver>();
		this.shipTypeCounter = new EnumMap<ShipType, Integer>(ShipType.class){{
			for(ShipType st : ShipType.values()) {
				put(st, 0);
			}
		}};
		this.boardPositions = new BoardPosition[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardPositions[i][j] = new BoardPosition();
			}
		}
	}
	
	public void attack(int x, int y) throws ModelException {
		if (!boardPositions[x][y].isPlayed()) {
			this.setChangedButtons(new ArrayList<Position>());
			
			//TODO: kan dit simpeler geschreven worden? :p
			boolean hit = this.boardPositions[x][y].attack();
			if (hit) {
				
				Position[][] isHit = new Position[x][y];
//				isHit(isHit);
				this.player.decrementScore();
				
				if(this.boardPositions[x][y].getShip().isSunk()) {
					for (Position p : this.boardPositions[x][y].getShip().getPositions()) {
						this.boardPositions[p.getX()][p.getY()].sink();
						this.changed.add(new Position(p.getX(), p.getY()));
					}
				} else {
					this.changed.add(new Position(x, y));
				}
			} else {
				this.changed.add(new Position(x, y));
			}
			
			this.notifyBoardChanged();
		} else {
			throw new ModelException("Je kan deze co�rdinaten niet meer aanvallen");
		}
	}
	
//	public void isHit(Position[][] isHit){
//		List <Position[][]> aroundHit = new ArrayList<>();
//		
//		switch() {
//		case "up":
//			
//			break;
//		case "down":
//			
//			break;
//		case "left":
//			
//			break;
//		case "right":
//		
//			break;
//		
//		default:
//			throw new ModelException("/");
//		
//		
//		
//	}
	
	public void focus(int x, int y, boolean value) {
		if (!boardPositions[x][y].isPlayed()) {
			this.setChangedButtons(new ArrayList<Position>());
			
			this.boardPositions[x][y].setFocus(value);
			this.changed.add(new Position(x, y));
			
			this.notifyBoardChanged();
		}
	}
	
	public void placeShip(Ship ship, boolean visible) throws ModelException {
		//TODO: kan dit met template?
		if(this.shipCounter == 5) {
			throw new ModelException("Je hebt het maximumaantal schepen bereikt.");
		}
		if(maxAmountShipsReached(ship.getShipType())) {
			throw new ModelException("Je hebt te veel schepen van het type \"" + ship.getShipType().getName() + "\" geplaatst.");
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
				this.boardPositions[i][y].setShip(ship);
				changed.add(new Position(i, y));
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.boardPositions[x][i].setShip(ship);
				changed.add(new Position(x, i));
			}
		}
		
		this.incrementCounters(ship.getShipType());	
		if(visible) {
			this.notifyBoardChanged();
		}
	}
	
	public void placeGhostShip(int x, int y, ShipType type, Orientation orientation) throws ModelException {
		//TODO: kan dit met template?
		if(this.shipCounter == 5) {
			return;
		}
		if(maxAmountShipsReached(type)) {
			return;
		}
		
		int length = type.getLength();
		this.setChangedButtons(new ArrayList<Position>());
		
		if(orientation.equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				verifyEnvironment(i, y);
			}
			for(int i = x; i < x + length; i++) {
				this.boardPositions[i][y].setFocus(true);
				this.changed.add(new Position(i, y));
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.boardPositions[x][i].setFocus(true);
				this.changed.add(new Position(x, i));
			}
		}
		
		this.notifyBoardChanged();
	}
	
	public void removeGhostShip(int x, int y, ShipType type, Orientation orientation) throws ModelException {
		//TODO: kan dit met template?
		int length = type.getLength();
		this.setChangedButtons(new ArrayList<Position>());
		
		if(orientation.equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				verifyEnvironment(i, y);
			}
			for(int i = x; i < x + length; i++) {
				this.boardPositions[i][y].setFocus(false);
				this.changed.add(new Position(i, y));
			}
		} else {
			for(int i = y; i < y + length; i++) {
				verifyEnvironment(x, i);
			}
			for(int i = y; i < y + length; i++) {
				this.boardPositions[x][i].setFocus(false);
				this.changed.add(new Position(x, i));
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
		if(this.boardPositions[x][y].containsShip()) {
			throw new ModelException("Je kan geen schip bovenop een ander schip plaatsen.");
		}
		if(neighbourContainsShip(x, y)) {
			throw new ModelException("Je kan een schip niet zo dicht bij een naburig schip plaatsen.");
		}
	}
		
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
	
	//Getters & setters
	public int getNbOfShips() {
		return this.shipCounter;
	}
	
	public BoardPosition[][] getBoardPositions() {
		return this.boardPositions;
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
