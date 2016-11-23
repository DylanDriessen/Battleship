package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listener.ClickListener;

public class GameFrame extends JFrame implements View {
	
	private GameJPanel panel1, panel2;
	private static final int WIDTH = 900;
	private static final int HEIGHT = 400;

	public GameFrame() {
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(new GridLayout(1,2));
		
		this.panel1 = new GameJPanel();
		this.panel1.setBackground(Color.BLUE);
		this.panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		for(JButton b : (this.panel1).getButtons()) {
			b.addActionListener(new ClickListener(this));
		}
		this.add(panel1);
		
		this.panel2 = new GameJPanel();
		this.panel2.setBackground(Color.RED);
		this.panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		for(JButton b : (this.panel2).getButtons()) {
			b.addActionListener(new ClickListener(this));
		}
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
