package application;

import javax.swing.JFrame;

import controller.Controller;
import controller.ZeeslagController;
import view.GameFrame;
import view.View;

public class ZeeslagApp {
	
	public static void main(String[] args) {
		View view = new GameFrame();
		view.startView();
		Controller controller = new ZeeslagController(view);
		//test Dylan
	}
	
}
