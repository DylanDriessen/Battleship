package view;

import java.awt.GridLayout;

import javax.swing.JFrame;

import model.Board;
import model.facade.ModelFacade;

public class GameFrame extends JFrame implements View {
	
	private static final long serialVersionUID = 1L;
	private GamePanel panel1, panel2;
	private SelectionPanel selectionPanel;
	private ModelFacade modelFacade;
	private static final int WIDTH = 940;
	private static final int HEIGHT = 340;

	public GameFrame(String playerName, ModelFacade modelFacade) {
		super();
		
		this.modelFacade = modelFacade;
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(new GridLayout(1,3));
		
		this.selectionPanel = new SelectionPanel();
		this.add(this.selectionPanel);
		
		Board board1 = this.modelFacade.getBoard1();
		this.panel1 = new GamePanel(this, playerName, board1);
		this.add(this.panel1);
		
		Board board2 = this.modelFacade.getBoard2();
		this.panel2 = new GamePanel(this, "Computer", board2);
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
