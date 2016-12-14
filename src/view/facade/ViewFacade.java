package view.facade;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import model.Game;
import view.GameFrame;
import view.Square;
import view.View;

public class ViewFacade {
	
	private View view;
	
	public ViewFacade(View view){
		this.view = view;
	}
	
	public Square[][] getButtonsPanel1() {
		return view.getPanel1().getGrid().getButtons();
	}
	
	public Square[][] getButtonsPanel2() {
		return view.getPanel2().getGrid().getButtons();
	}
	
	public JRadioButton getHorizontalButton() {
		return view.getSelectionPanel().getHorizontal();
	}
	
	public JRadioButton getVerticalButton() {
		return view.getSelectionPanel().getVertical();
	}
	
	public JComboBox getJComboBox() {
		return view.getSelectionPanel().getShipsComboBox();
	}
	
	public JButton getStartButton() {
		return view.getSelectionPanel().getStartButton();
	}
	
}
