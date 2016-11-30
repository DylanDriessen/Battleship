package application;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.ZeeslagController;
import model.Game;
import model.facade.ModelFacade;
import view.GameFrame;
import view.facade.ViewFacade;

public class ZeeslagApp {
	
	public static void main(String[] args) {
		String playerName = null;
		while(playerName == null || playerName.isEmpty()) {
			playerName = JOptionPane.showInputDialog(null, "Please enter your name:");
		}
		
		//TODO: view should "observe" playerName from the model:
		// I think code should work without the playerName parameter in the GameFrame constructor?
		Game model = new Game(playerName);
		ModelFacade modelFacade = new ModelFacade(model);
		GameFrame view = new GameFrame(playerName, modelFacade);
		ViewFacade viewFacade = new ViewFacade(view);
		new ZeeslagController(modelFacade, viewFacade);
		view.startView();
	}
	
}
