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

import listener.ClickListener;

public class GameFrame extends JFrame implements View {
	
	private GamePanel panel1, panel2;
	private static final int WIDTH = 750;
	private static final int HEIGHT = 420;

	public GameFrame(String playerName) {
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(new GridLayout(1,2));
		
		this.add(new SelectionPanel());
		
		this.panel1 = new GamePanel(this, playerName);
		this.add(panel1);
		
		this.panel2 = new GamePanel(this, "Computer");
		this.add(panel2);
	
		revalidate();
	}

	public JPanel getPanel1() {
		return this.panel1;
	}

	public JPanel getPanel2() {
		return this.panel2;
	}
	
	

	@Override
	public void startView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}	

}
