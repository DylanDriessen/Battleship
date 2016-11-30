package application;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.ZeeslagController;
import model.Game;
import view.GameFrame;
import view.View;

public class ZeeslagApp {
	
	public static void main(String[] args) {
		String playerName = null;
		while(playerName == null || playerName.isEmpty()) {
			playerName = JOptionPane.showInputDialog(null, "Please enter your name:");
		}
		//TODO: view should "observe" playerName from the model:
		// I think code should work without the playerName parameter in the GameFrame constructor?
		GameFrame view = new GameFrame(playerName);
		Game model = new Game(playerName);
		Controller controller = new ZeeslagController(view, model);
		view.startView();
	}
	
}
