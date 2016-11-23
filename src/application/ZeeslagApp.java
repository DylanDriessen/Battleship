package application;

import javax.swing.JFrame;

import controller.ZeeslagController;
import view.SpelFrame;

public class ZeeslagApp {
	
	public static void main(String[] args) {
		SpelFrame view = new SpelFrame();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
		ZeeslagController controller = new ZeeslagController(view);
	}
	
}
