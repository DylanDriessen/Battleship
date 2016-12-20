/**
 * @author Mathias Spanhove, Dylan Driessen, Wouter Cypers
 */

package model.game.state;

import exception.ModelException;
import model.game.Game;
import model.player.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.Position;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;

public class StartedState implements GameState {

	private Game game;
	
	
	public StartedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {	
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		//Attack enemy board
		this.game.getPlayer().getEnemyBoard().attack(position);
		
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
			this.game.setState(this.game.getFinishedState());
			this.game.finishGame();
		}
		
	}
	
	@Override
	public void squareEntered(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		this.game.getPlayer().getEnemyBoard().focus(position, true);
	}

	@Override
	public void squareExited(Position position, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		this.game.getPlayer().getEnemyBoard().focus(position, false);
	}
	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void finishGame() throws ModelException {
		//TODO
		throw new NotImplementedException();
	}

}
