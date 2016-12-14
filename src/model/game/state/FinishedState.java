package model.game.state;

import javax.swing.JOptionPane;

import exception.ModelException;
import model.Game;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;

public class FinishedState implements GameState {

	private Game game;
	
	public FinishedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if(game.getPlayer().getMyBoard().getShipCounter() == 0){
			throw new ModelException("The game is finished");
		}
		
		if(game.getPlayer().getEnemyBoard().getShipCounter() == 0){
			throw new ModelException("The game is finished");
		}
	}

	@Override
	public void squareEntered(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {

	}

	@Override
	public void squareExited(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		
	}

}
