package view.facade;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import model.game.Game;
import view.GameFrame;
import view.Square;
import view.View;

public class ViewFacade {
	
	private View view;
	
	public ViewFacade(View view){
		this.view = view;
	}
	
	public Square[][] getButtonsPanel1() {
		return this.view.getPanel1().getGrid().getButtons();
	}
	
	public Square[][] getButtonsPanel2() {
		return this.view.getPanel2().getGrid().getButtons();
	}
	
	public JRadioButton getHorizontalButton() {
		return this.view.getSelectionPanel().getHorizontal();
	}
	
	public JRadioButton getVerticalButton() {
		return this.view.getSelectionPanel().getVertical();
	}
	
	public JComboBox getJComboBox() {
		return this.view.getSelectionPanel().getShipsComboBox();
	}
	
	public JButton getStartButton() {
		return this.view.getSelectionPanel().getStartButton();
	}
	
	public JButton getSettingsButton() {
		return this.view.getSelectionPanel().getSettingsButton();
	}

	public void openSettings() {
		this.view.openSettings();
		
	}
	
}
