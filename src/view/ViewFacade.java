package view;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class ViewFacade {
	
	public Square[][] getButtonsPanel1(View view) {
		return view.getPanel1().getGrid().getButtons();
	}
	
	public Square[][] getButtonsPanel2(View view) {
		return view.getPanel2().getGrid().getButtons();
	}
	
	public JRadioButton getHorizontalButton(View view) {
		return view.getSelectionPanel().getHorizontal();
	}
	
	public JRadioButton getVerticalButton(View view) {
		return view.getSelectionPanel().getVertical();
	}
	
	public JComboBox getJComboBox(View view) {
		return view.getSelectionPanel().getShipsComboBox();
	}
	
}
