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
import model.facade.IModelFacade;

public class GameFrame extends JFrame implements GameObserver {
	
	private static final long serialVersionUID = 1L;
	private GamePanel panelPlayer, panelAI;
	private SelectionPanel selectionPanel;
	private IModelFacade model;
	
	public static final int WIDTH = 940;
	public static final int HEIGHT = 360;
	public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

	public GameFrame(IModelFacade model) {
		super();
		
		this.model = model;
		this.model.registerGameObserver(this);		
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		
		this.setLayout(new GridLayout(1,3));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		this.selectionPanel = new SelectionPanel();
		this.selectionPanel.setBorder(padding);
		this.add(this.selectionPanel);
		
		//Init Player GamePanel
		String playerName = this.model.getPlayerName();
		int playerScore = this.model.getPlayerScore();
		Board boardPlayer = this.model.getBoardPlayer();
		this.panelPlayer = new GamePanel(this, playerName, playerScore, boardPlayer);
		this.panelPlayer.setBorder(padding);
		this.add(this.panelPlayer);
		
		//Init AI GamePanel
		String aiName = this.model.getAIName();
		int aiScore = this.model.getAIScore();
		Board boardAI = this.model.getBoardAI();
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

	public void startView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void gameChanged(Game game) {
		if(game.getWinner() != null) {
			JOptionPane.showMessageDialog(null, "Game over!\n" +  game.getWinner().getName() + " won met " +  game.getWinner().getScore() + " punten.");
			this.selectionPanel.getStartButton().setEnabled(true);
			this.selectionPanel.getSettingsButton().setEnabled(true);
		}
	}
	
	public void openSettings() {
		SettingFrame settings = new SettingFrame(this.model);
		settings.startView();
	}

	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public String getStringInput(String message) {
		return JOptionPane.showInputDialog(null, message);
	}

}
