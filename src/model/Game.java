package model;

import java.util.List;

public class Game {
	
	private List<Player> players;
	private String name;
	
	public void addPlayer(String name){
		Player newPlayer = new Player(name);
		players.add(newPlayer);
	}
	
	public Player getPlayer(String name){
		return null;
		
	}

	public String getName() {
		return name;
	}


}
