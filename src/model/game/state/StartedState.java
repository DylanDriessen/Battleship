package model.game.state;

import javax.swing.JOptionPane;

import exception.ModelException;
import model.game.Game;
import model.player.Player;
import model.Position;
import model.Ship;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;
import model.facade.ModelFacade;
import view.GameFrame;
import view.View;

public class StartedState implements GameState {

	private Game game;
	
	
	public StartedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {	
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		//Attack enemy board
		this.game.getPlayer().getEnemyBoard().attack(x, y);
		
		Player winner = null;
		//Check if player has won. If so, end the game
		if(this.game.getPlayer().getEnemyBoard().getShipCounter() == 0){
			winner = this.game.getPlayer();
		} else {
			//Let AI attack player
			this.game.getAI().attack();
			//Check if AI has won. If so, end the game
			if(this.game.getPlayer().getMyBoard().getShipCounter() == 0) {
				winner = this.game.getAI();
			}
		}
		
		if(winner != null) {
			this.game.setWinner(winner);
			this.game.notifyGameChanged();
			this.game.reset();
			this.game.setState(this.game.getNewState());
		}
		
	}
	
	@Override
	public void squareEntered(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		this.game.getPlayer().getEnemyBoard().focus(x, y, true);
	}

	@Override
	public void squareExited(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		this.game.getPlayer().getEnemyBoard().focus(x, y, false);
	}
	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
