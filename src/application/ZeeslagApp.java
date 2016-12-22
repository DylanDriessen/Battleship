package application;

import javax.swing.JOptionPane;

import controller.ZeeslagController;
import model.facade.IModelFacade;
import model.facade.ModelFacade;
import properties.PropertiesFile;
import view.facade.IViewFacade;
import view.facade.ViewFacade;

/**
 * @author Mathias, Wouter & Dylan
 */
public class ZeeslagApp {
	
	public static void main(String[] args) {
		try {
			PropertiesFile properties = PropertiesFile.getInstance();
			IModelFacade model = new ModelFacade(properties);
			IViewFacade view = new ViewFacade(model);
			new ZeeslagController(model, view);
		} catch (Exception e) {
			JOptionPane.showInputDialog(null, "Het spel kon niet gestart worden wegens een probleem met het opstarten.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
