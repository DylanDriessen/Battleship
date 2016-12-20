package view.facade;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import model.enums.ShipType;
import view.Square;
import view.combobox.ComboItem;

public interface IViewFacade {
	Square[][] getButtonsPanel1();
	Square[][] getButtonsPanel2();
	JRadioButton getHorizontalButton();
	JRadioButton getVerticalButton();
	JComboBox<ComboItem<ShipType>> getJComboBox();
	JButton getStartButton();
	JButton getSettingsButton();
	void openSettings();
	void showErrorMessage(String message);
	String getStringInput(String message);
	void startView();
}