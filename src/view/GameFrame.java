package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import model.game.Game;
import model.game.GameObserver;
import model.board.Board;
import model.facade.IModelFacade;

/**
 * @author Mathias, Wouter & Dylan
 */
public class GameFrame extends JFrame implements GameObserver {
	
	private static final long serialVersionUID = 1L;
	private GamePanel panelPlayer, panelAI;
	private SelectionPanel selectionPanel;
	private IModelFacade model;
	private SettingsFrame settingsFrame;
	private Game game;
	
	public static final int WIDTH = 940;
	public static final int HEIGHT = 360;
	public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
	public static final Border PADDING = BorderFactory.createEmptyBorder(10, 10, 10, 10);

	public GameFrame(IModelFacade model) {
		super();
		
		this.model = model;
		this.model.registerGameObserver(this);		
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(new GridLayout(1,3));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle("Zeeslag");
		
		//Init SelectionPanel
		this.selectionPanel = new SelectionPanel();
		this.selectionPanel.setBorder(PADDING);
		this.add(this.selectionPanel);
		
		//Init Player GamePanel
		String playerName = this.model.getPlayerName();
		int playerScore = this.model.getPlayerScore();
		Board boardPlayer = this.model.getBoardPlayer();
		this.panelPlayer = new GamePanel(this, playerName, playerScore, boardPlayer);
		this.panelPlayer.setBorder(PADDING);
		this.add(this.panelPlayer);
		
		//Init AI GamePanel
		String aiName = this.model.getAIName();
		int aiScore = this.model.getAIScore();
		Board boardAI = this.model.getBoardAI();
		this.panelAI = new GamePanel(this, aiName, aiScore, boardAI);
		this.panelAI.setBorder(PADDING);
		this.add(this.panelAI);
		
		//SettingFrame
		this.settingsFrame = new SettingsFrame(model);
		this.settingsFrame.setLocationRelativeTo(this);
	
		revalidate();
	}
	
	public void startView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void update(Game game) {
		this.game = game;
		this.endGame();
	}
	
	public void endGame() {
		if(this.game.getWinner() != null) {
			JOptionPane.showMessageDialog(null, "Game over!\n" +  this.game.getWinner().getName() + " won met " +  this.game.getWinner().getScore() + " punten.");
			this.selectionPanel.getStartButton().setEnabled(true);
			this.selectionPanel.getSettingsButton().setEnabled(true);
		}
	}
	
	public void openSettings() {
		this.settingsFrame.startView();
	}

	public void showMessage(String message) {
		JLabel label = new JLabel(message);
		label.setFont(DEFAULT_FONT);
		JOptionPane.showMessageDialog(this, label, "", JOptionPane.WARNING_MESSAGE);
	}

	public String getStringInput(String message) {
		JLabel label = new JLabel(message);
		label.setFont(DEFAULT_FONT);
		return JOptionPane.showInputDialog(this, label, "", JOptionPane.QUESTION_MESSAGE);
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

	public SettingsFrame getSettingsFrame() {
		return this.settingsFrame;
	}
}
