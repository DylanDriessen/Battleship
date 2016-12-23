package view.facade;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import model.enums.ShipType;
import model.facade.IModelFacade;
import view.GameFrame;
import view.Square;
import view.combobox.ComboItem;

/**
 * @author Mathias, Wouter & Dylan
 */
public class ViewFacade implements IViewFacade{
	
	private GameFrame view;
	
	public ViewFacade(IModelFacade model){
		this.view = new GameFrame(model);
	}
	
	@Override
	public Square[][] getButtonsPanel1() {
		return this.view.getPanelPlayer().getGrid().getButtons();
	}
	
	@Override
	public Square[][] getButtonsPanel2() {
		return this.view.getPanelAI().getGrid().getButtons();
	}
	
	@Override
	public JRadioButton getHorizontalButton() {
		return this.view.getSelectionPanel().getHorizontal();
	}
	
	@Override
	public JRadioButton getVerticalButton() {
		return this.view.getSelectionPanel().getVertical();
	}
	
	@Override
	public JComboBox<ComboItem<ShipType>> getJComboBox() {
		return this.view.getSelectionPanel().getShipsComboBox();
	}
	
	@Override
	public JButton getStartButton() {
		return this.view.getSelectionPanel().getStartButton();
	}
	
	@Override
	public JButton getSettingsButton() {
		return this.view.getSelectionPanel().getSettingsButton();
	}
	
	@Override
	public JButton getSaveButton() {
		return this.view.getSettingsFrame().getSaveButton();
	}
	
	@Override
	public void openSettings() {
		this.view.openSettings();	
	}
	
	@Override
	public void closeSettings() {
		this.view.getSettingsFrame().endView();	
	}
	
	@Override
	public void showErrorMessage(String message) {
		this.view.showMessage(message);
	}
	
	@Override
	public String getStringInput(String message) {
		return this.view.getStringInput(message);
	}
	
	@Override
	public void startView() {
		this.view.startView();
	}

	@Override
	public void updatePlayerNameLabel(String playerName) {
		this.view.getPanelPlayer().getScoreboard().setPlayerName(playerName);
		this.view.getPanelPlayer().getScoreboard().updateLabel();
	}
	
	@Override
	public void disableShipsAvailableLabel(boolean value) {
		this.view.getSelectionPanel().disableShipsAvailableLabel(value);
	}

	@Override
	public void updateShipsAvailableLabel(ShipType shipType) {
		this.view.getSelectionPanel().setSelectedShipType(shipType);
		int amount = this.view.getSelectionPanel().calcAvailableShipsOfSelectedType();
		this.view.getSelectionPanel().setAvailableShipsOfSelectedType(amount);
		this.view.getSelectionPanel().updateShipsAvailableLabel();
	}
	
}
