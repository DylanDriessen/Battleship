package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listener.ClickListener;

public class GameFrame extends JFrame implements View {
	
	private GamePanel panel1, panel2;
	private static final int WIDTH = 850;
	private static final int HEIGHT = 500;

	public GameFrame() {
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(new GridLayout(1,2));
		
		this.panel1 = new GamePanel(this, "John");
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
