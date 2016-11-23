package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements View {
	
	private JPanel panel1, panel2;
	private static final int WIDTH = 900;
	private static final int HEIGHT = 400;

	public GameFrame() {
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(null);
		
		this.panel1 = new GameJPanel(0, 0);
		
		this.panel2 = new GameJPanel(0, 0);
		
		this.add(panel1);
		this.add(panel2);
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
