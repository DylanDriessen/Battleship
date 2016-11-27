package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {

	private GameGrid grid;
	private Scoreboard scoreboard;
	
	public GamePanel(View gameFrame, String playerName) {
		super();
		
		this.grid = new GameGrid(gameFrame, 200);
		this.scoreboard = new Scoreboard(playerName);
		
//		this.setLayout(new GridBagLayout());
//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setPreferredSize(new Dimension (10,10));
//		GridBagConstraints gbc = new GridBagConstraints();
//		
//		gbc.anchor = GridBagConstraints.LINE_START;
//	    gbc.gridx = 0;
//	    gbc.gridy = 0;
//	    gbc.weighty = 0.1;
//	    
		this.add(scoreboard);
		
		this.add(grid);
		scoreboard.setAlignmentY(TOP_ALIGNMENT);
		grid.setAlignmentY(BOTTOM_ALIGNMENT);
//		
//		gbc.weighty = 0.90;
//	    gbc.gridy = 1;
//		this.add(grid, gbc);
		
		}
        
	}

