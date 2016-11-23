package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameJPanel extends JPanel {

	private List<JButton> buttons = new ArrayList<JButton>();
	
	public GameJPanel() {		
		this.setLayout(new GridLayout(10, 10));
		
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				JButton button = new JButton("" + x + y);
				button.setBackground(Color.LIGHT_GRAY);
				this.buttons.add(button);
				this.add(button);
				System.out.println("Added button: " + button.getText());
			}
		}
	}
}
