package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.ZeeslagController;
import view.View;

public class ClickListener extends ZeeslagController implements ActionListener {

	public ClickListener(View view) {
		super(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = ((JButton)e.getSource());
		int x = Integer.parseInt(button.getText().charAt(0) + "");
		int y = Integer.parseInt(button.getText().charAt(1) + "");

		System.out.println("Clicked button with coördinates: x = " + x + ", y = " + y);

		buttonClicked(x, y);
	}

}
