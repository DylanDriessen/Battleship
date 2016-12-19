package application;

import javax.swing.JOptionPane;

import controller.ZeeslagController;
import exception.ModelException;
import model.game.Game;
import model.facade.ModelFacade;
import properties.PropertiesFile;
import view.GameFrame;
import view.facade.ViewFacade;

public class ZeeslagApp {
	
	public static void main(String[] args) {
		String playerName = null;
		while(playerName == null || playerName.isEmpty()) {
			playerName = JOptionPane.showInputDialog(null, "Gelieve uw naam in te vullen:");
		}
		PropertiesFile properties = new PropertiesFile();
		Game model;
		try {
			model = new Game(properties, playerName);
			ModelFacade modelFacade = new ModelFacade(model);
			GameFrame view = new GameFrame(modelFacade);
			ViewFacade viewFacade = new ViewFacade(view);
			new ZeeslagController(modelFacade, viewFacade);
			view.startView();
		} catch (ModelException e) {
			JOptionPane.showInputDialog(null, "Het spel kon niet gestart worden wegens een probleem met het opstarten.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
