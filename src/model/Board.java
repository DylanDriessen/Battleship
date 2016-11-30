package model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Orientation;
import model.Ship;

public class Board implements BoardObservable{
	
	private Player player;
	private List<BoardObserver> observers;
	private boolean[][] containsShip;
	
	public Board(String playerName) {
		this.player = new Player(playerName);
		this.containsShip = new boolean[10][10];
		this.observers = new ArrayList<BoardObserver>();
	}
	
	public void placeShip(Ship ship) {
		int x = ship.getAnchor().getX();
		int y = ship.getAnchor().getY();
		int length = ship.getShipType().getLength();
		
		System.out.println(ship.getShipType());
		
		if(ship.getOrientation().equals(Orientation.HORIZONTAL)) {
			for(int i = x; i < x + length; i++) {
				this.containsShip[i][y] = true;
			}
		} else {
			for(int i = y; i < y + length; i++) {
				this.containsShip[x][i] = true;
			}
		}
		
		System.out.println("notifying observers");
		
		this.notifyBoardChanged();
	}
	
	//Getters
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean[][] getContainsShip() {
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
