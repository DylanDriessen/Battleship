package model;

import java.util.List;

import model.Ship;

public class Board implements BoardObservable{
	
	private Player player;
	private List<BoardObserver> boardObservers;
	
	public Player Board(Player player){
		return player;
		
	}

	@Override
	public void addObserver(BoardObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(BoardObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyBoardChanged() {
		// TODO Auto-generated method stub
		
	}
	
	

}
