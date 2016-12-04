package model.facade;

import exception.ModelException;
import model.Game;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;

public class ModelFacade {
	
	private Game game;
	
	public ModelFacade(Game game){
		this.game = game;
	}
	
	public Board getBoardPlayer() {
		return this.game.getPlayer().getMyBoard();
	}
	
	public Board getBoardAI() {
		return this.game.getAI().getMyBoard();
	}
	
	public void startGame() throws ModelException {
		this.game.startGame();
	}
	
	public void buttonClicked(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonClicked(x, y, shipType, orientation, board);
	}
	
	public void buttonEntered(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonEntered(x, y, shipType, orientation, board);
	}
	
	public void buttonExited(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		this.game.buttonExited(x, y, shipType, orientation, board);
	}

	public void placeAIShips(boolean visible) {
		this.game.getAI().placeShips(visible);
	}
	
	/*
	public void addPlayer() throws DomainException{
		try {
			String Playername = showJOptionInputDialog("Enter your name:", "Add Player");
			//game.addPlayer(Playername);
			JOptionPane.showMessageDialog(null, "Player ready to play");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Input not valid");
			e.printStackTrace();
		} catch (CancelledException e) {
			return;
		}
	}
	private String showJOptionInputDialog(String message, String title) throws CancelledException {
		String value = JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);

		if (value == null) {
			throw new CancelledException("User pressed the cancel button");
		} else {
			return value;
		}
	}

	
	public Player getPlayer(String name){
		return getPlayer(name);
	}
	
	public void applyShip(Ship ship, Player player){
		
	}
	
	public Ship getShip(ShipType type, int x, int y, Orientation orientation){
		return null;
		
	}
	*/
	
}
