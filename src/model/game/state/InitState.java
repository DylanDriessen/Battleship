package model.game.state;

public class InitState implements GameState {

	@Override
	public void buttonClicked(int x, int y) {
		System.out.println("Placed ship");
		
		//TODO:
		// tell model to make a ship
		// tell model that certain squares are occupied by a ship
		// since view observes model, it will draw the squares differently
		
	}

}
