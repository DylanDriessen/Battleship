package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import model.ModelFacade;
import model.Orientation;
import model.Player;
import model.Ship;
import model.ShipType;
import view.GameFrame;
import view.View;

public class ZeeslagController implements Controller {

	private View view;
	
	public ZeeslagController(View view) {
		this.view = view;
	}
	
	public void buttonClicked(int x, int y) {
		//TODO: change something in the model when a button is clicked etc. etc.
		System.out.println("Clicked button with coördinates: x = " + x + ", y = " + y);
	}

}
	
	 

