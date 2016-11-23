package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import listener.ClickListener;

public class GameJPanel extends JPanel {

	private List<JButton> buttons = new ArrayList<JButton>();
	
	public GameJPanel(View gameFrame) {		
		this.setLayout(new GridLayout(10, 10));
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				JButton button = new JButton("" + x + y);
				button.setBackground(Color.LIGHT_GRAY);
				button.addActionListener(new ClickListener(gameFrame));
				this.buttons.add(button);
				this.add(button);
			}
		}
	}
	
	public List<JButton> getButtons() {
		return this.buttons;
	}
}
