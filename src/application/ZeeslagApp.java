package application;

import javax.swing.JFrame;

import controller.ZeeslagController;
import view.GameFrame;

public class ZeeslagApp {
	
	public static void main(String[] args) {
		GameFrame view = new GameFrame();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
		ZeeslagController controller = new ZeeslagController(view);
	}
	
}
