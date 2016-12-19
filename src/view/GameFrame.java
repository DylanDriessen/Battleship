package view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import model.game.Game;
import model.game.GameObserver;
import model.board.Board;
import model.facade.ModelFacade;

public class GameFrame extends JFrame implements View, GameObserver {
	
	private static final long serialVersionUID = 1L;
	private GamePanel panelPlayer, panelAI;
	private SelectionPanel selectionPanel;
	private ModelFacade modelFacade;
	
	public static final int WIDTH = 940;
	public static final int HEIGHT = 360;
	public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

	public GameFrame(ModelFacade modelFacade) {
		super();
		
		this.modelFacade = modelFacade;
		this.modelFacade.registerGameObserver(this);		
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		
		this.setLayout(new GridLayout(1,3));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		this.selectionPanel = new SelectionPanel();
		this.selectionPanel.setBorder(padding);
		this.add(this.selectionPanel);
		
		//Init Player GamePanel
		String playerName = this.modelFacade.getPlayerName();
		int playerScore = this.modelFacade.getPlayerScore();
		Board boardPlayer = this.modelFacade.getBoardPlayer();
		this.panelPlayer = new GamePanel(this, playerName, playerScore, boardPlayer);
		this.panelPlayer.setBorder(padding);
		this.add(this.panelPlayer);
		
		//Init AI GamePanel
		String aiName = this.modelFacade.getAIName();
		int aiScore = this.modelFacade.getAIScore();
		Board boardAI = this.modelFacade.getBoardAI();
		this.panelAI = new GamePanel(this, aiName, aiScore, boardAI);
		this.panelAI.setBorder(padding);
		this.add(this.panelAI);
	
		revalidate();
	}

	public GamePanel getPanelPlayer() {
		return this.panelPlayer;
	}

	public GamePanel getPanelAI() {
		return this.panelAI;
	}
	
	public SelectionPanel getSelectionPanel() {
		return this.selectionPanel;
	}

	@Override
	public void startView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void gameChanged(Game game) {
		if(game.getWinner() != null) {
			JOptionPane.showMessageDialog(null, "Game over!\n" +  game.getWinner().getName() + " won met " +  game.getWinner().getScore() + " punten.");
		}
	}
	
	@Override
	public void openSettings() {
		String[] Settings = { "Makkelijk", "Medium", "Moeilijk" };
		
	    String difficulty = (String) JOptionPane.showInputDialog(null, 
	        "Kies een moeilijkheidsgraad:",
	        "Instellingen",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        Settings, 
	        Settings[0]);

	    // favoritePizza will be null if the user clicks Cancel
	    System.out.printf("Difficulty is %s.\n", difficulty);
	}

}
