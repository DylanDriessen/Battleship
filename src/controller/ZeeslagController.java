package controller;

import view.GameFrame;
import view.View;

public class ZeeslagController implements Controller {

	private View view;
	
	public ZeeslagController(View view) {
		this.view = view;
	}
	
	public void buttonClicked(int x, int y) {
		//TODO: change something in the model when a button is clicked etc. etc.
		System.out.println("buttonClicked() performed in ZeeslagController");
	}

}
