package view.facade;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import model.enums.ShipType;
import view.combobox.ComboItem;
import view.square.Square;

/**
 * @author Wouter
 */
public interface IViewFacade {
	Square[][] getButtonsPanel1();
	Square[][] getButtonsPanel2();
	JRadioButton getHorizontalButton();
	JRadioButton getVerticalButton();
	JComboBox<ComboItem<ShipType>> getJComboBox();
	JButton getStartButton();
	JButton getSettingsButton();
	JButton getSaveButton();
	void openSettings();
	void closeSettings();
	void showErrorMessage(String message);
	String getStringInput(String message);
	void startView();
	void updatePlayerNameLabel(String playerName);
	void disableShipsAvailableLabel(boolean value);
	void updateShipsAvailableLabel(ShipType shipType);
}
