package view.facade;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import model.enums.ShipType;
import view.Square;
import view.View;
import view.combobox.ComboItem;

public class ViewFacade {
	
	private View view;
	
	public ViewFacade(View view){
		this.view = view;
	}
	
	public Square[][] getButtonsPanel1() {
		return this.view.getPanelPlayer().getGrid().getButtons();
	}
	
	public Square[][] getButtonsPanel2() {
		return this.view.getPanelAI().getGrid().getButtons();
	}
	
	public JRadioButton getHorizontalButton() {
		return this.view.getSelectionPanel().getHorizontal();
	}
	
	public JRadioButton getVerticalButton() {
		return this.view.getSelectionPanel().getVertical();
	}
	
	public JComboBox<ComboItem<ShipType>> getJComboBox() {
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

	public void showErrorMessage(String message) {
		this.view.showErrorMessage(message);
	}
	
}
