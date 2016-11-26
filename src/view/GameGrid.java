package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import listener.ClickListener;

public class GameGrid extends JPanel {

private List<JButton> buttons = new ArrayList<JButton>();
	
	public GameGrid(View gameFrame, int width) {		
		this.setLayout(new GridLayout(10, 10));
		this.setPreferredSize(new Dimension(width, width));
		
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
