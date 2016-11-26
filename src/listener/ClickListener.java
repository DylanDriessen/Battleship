package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.ZeeslagController;
import view.Square;
import view.View;

public class ClickListener extends ZeeslagController implements ActionListener {

	public ClickListener(View view) {
		super(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Square button = ((Square)e.getSource());
		int x = button.getX();
		int y = button.getY();
		buttonClicked(x, y);
	}

}
