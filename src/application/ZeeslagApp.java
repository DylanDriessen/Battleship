package application;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;
import controller.ZeeslagController;
import view.GameFrame;
import view.View;

public class ZeeslagApp {
	
	public static void main(String[] args) {
		String playerName = JOptionPane.showInputDialog(null, "Please enter your name:");
		View view = new GameFrame(playerName);
		view.startView();
		Controller controller = new ZeeslagController(view);
	}
	
}
