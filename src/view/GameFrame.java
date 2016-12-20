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
import model.enums.BoardType;
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
		this.panelPlayer = new GamePanel(this, playerName, playerScore, BoardType.PLAYER);
		this.modelFacade.registerBoardPlayerObserver(this.panelPlayer);
		this.panelPlayer.setBorder(padding);
		this.add(this.panelPlayer);
		
		//Init AI GamePanel
		String aiName = this.modelFacade.getAIName();
		int aiScore = this.modelFacade.getAIScore();
		this.panelAI = new GamePanel(this, aiName, aiScore, BoardType.AI);
		this.modelFacade.registerBoardAIObserver(this.panelAI);
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
		
		JFrame settingsFrame = new JFrame("Settings");
		
		
		
	    String difficulty = (String) JOptionPane.showInputDialog(settingsFrame, 
	        "Kies een moeilijkheidsgraad:",
	        "Instellingen",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        Settings, 
	        Settings[0]);

	    // favoritePizza will be null if the user clicks Cancel
	    System.out.printf("Difficulty is %s.\n", difficulty);
	}

	@Override
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public String getStringInput(String message) {
		return JOptionPane.showInputDialog(null, message);
	}

}
