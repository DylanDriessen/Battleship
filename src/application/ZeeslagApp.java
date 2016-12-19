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
		PropertiesFile properties = new PropertiesFile();
		Game model;
		try {
			model = new Game(properties);
			ModelFacade modelFacade = new ModelFacade(model);
			GameFrame view = new GameFrame(modelFacade);
			ViewFacade viewFacade = new ViewFacade(view);
			new ZeeslagController(modelFacade, viewFacade);
		} catch (ModelException e) {
			JOptionPane.showInputDialog(null, "Het spel kon niet gestart worden wegens een probleem met het opstarten.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
