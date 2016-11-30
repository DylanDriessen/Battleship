package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import exception.ModelException;
import model.enums.Orientation;
import model.enums.ShipType;
import model.facade.ModelFacade;
import view.Square;
import view.combobox.ComboBox;
import view.combobox.ComboItem;
import view.facade.ViewFacade;

public class ZeeslagController implements Controller {

	private final ViewFacade viewFacade;
	private final ModelFacade modelFacade;
	private Orientation orientation;
	private ShipType shipType;
	
	public ZeeslagController(ModelFacade modelFacade, ViewFacade viewFacade) {
		this.modelFacade = modelFacade;
		this.viewFacade = viewFacade;
		
		Square[][] squares1 = this.viewFacade.getButtonsPanel1();
		Square[][] squares2 = this.viewFacade.getButtonsPanel2();
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				squares1[x][y].addMouseListener(new ClickListener());
				squares2[x][y].addMouseListener(new ClickListener());
			}
		}
		
		this.viewFacade.getHorizontalButton().addActionListener(new RadioListener());
		this.viewFacade.getVerticalButton().addActionListener(new RadioListener());
		this.viewFacade.getJComboBox().addActionListener(new ComboboxListener());
	}
	
	public void buttonClicked(int x, int y) {
		//TODO: shouldn't this be a Position object instead of x y?
		try {
			this.modelFacade.buttonClicked(x, y, this.shipType, this.orientation);
		} catch (ModelException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	private class ClickListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			Square button = (Square)e.getSource();
			int x = button.getX();
			int y = button.getY();
			buttonClicked(x, y);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			Square button = (Square)e.getSource();
			int x = button.getX();
			int y = button.getY();
			// System.out.println("hover over " + x + y + " bitchez");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			Square button = (Square)e.getSource();
			int x = button.getX();
			int y = button.getY();
			// System.out.println("exited " + x + y + " bitchez");
		}

	}
	
	private class ComboboxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ComboBox<ComboItem<ShipType>> box = (ComboBox<ComboItem<ShipType>>)e.getSource();
			ShipType shipType = ((ComboItem<ShipType>)box.getSelectedItem()).getValue();
			setShipType(shipType);
		}
		
	}
	
	private class RadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton radioButton = (JRadioButton)e.getSource();
			setOrientation(Orientation.valueOf(radioButton.getText().toUpperCase()));
		}
		
	}
	
}
