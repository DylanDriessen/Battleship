package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ComboBoxModel;

public class GameFrame extends JFrame implements View {
	
	private GamePanel panel1, panel2;
	private SelectionPanel selectionPanel;
	private static final int WIDTH = 750;
	private static final int HEIGHT = 420;

	public GameFrame(String playerName) {
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(new GridLayout(1,2));
		
		this.selectionPanel = new SelectionPanel();
		this.add(this.selectionPanel);
		
		this.panel1 = new GamePanel(this, playerName);
		this.add(this.panel1);
		
		this.panel2 = new GamePanel(this, "Computer");
		this.add(this.panel2);
	
		revalidate();
	}

	public GamePanel getPanel1() {
		return this.panel1;
	}

	public GamePanel getPanel2() {
		return this.panel2;
	}
	
	public SelectionPanel getSelectionPanel() {
		return this.selectionPanel;
	}

	@Override
	public void startView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	

}
