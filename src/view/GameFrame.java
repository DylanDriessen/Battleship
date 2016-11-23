package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	
	private JPanel panel1, panel2;
	private static final int BREEDTE = 900;
	private static final int HOOGTE = 400;

	public GameFrame() {
		super();
		this.setSize(BREEDTE, HOOGTE);
		this.setResizable(false);
		this.setLayout(null);
		
		this.panel1 = new GameJPanel(0, 0);
		
		this.panel2 = new GameJPanel(0, 0);
	}

	public JPanel getPanel1() {
		return this.panel1;
	}

	public JPanel getPanel2() {
		return this.panel2;
	}	

}
