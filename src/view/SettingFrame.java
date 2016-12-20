package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exception.ModelException;
import model.enums.AttackStrategies;
import model.enums.PlaceStrategies;
import model.facade.IModelFacade;
import model.player.attackstrategy.AttackStrategy;
import model.player.placestrategy.PlaceStrategy;
import properties.PropertiesFile;

public class SettingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<PlaceStrategies> placeComboBox;
	private JComboBox<AttackStrategies> attackComboBox;
	private JButton saveButton;
	private IModelFacade model;

	public SettingFrame(IModelFacade model) {
		this.model = model;
		PropertiesFile properties = this.model.getProperties();
		
		this.setSize(new Dimension(200, 200));
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 20, 20));
		this.add(panel);
		setContentPane(panel);

		PlaceStrategies currPlaceStrategy = PlaceStrategies.valueOf(properties.get("placeStrategyAI").toUpperCase());
		this.placeComboBox = new JComboBox<PlaceStrategies>(PlaceStrategies.values());
		this.placeComboBox.setSelectedItem(currPlaceStrategy);
		panel.add(this.placeComboBox);

		AttackStrategies currAttackStrategy = AttackStrategies.valueOf(properties.get("attackStrategyAI").toUpperCase());
		this.attackComboBox = new JComboBox<AttackStrategies>(AttackStrategies.values());
		this.attackComboBox.setSelectedItem(currAttackStrategy);
		panel.add(this.attackComboBox);

		this.saveButton = new JButton();
		this.saveButton.setFont(GameFrame.DEFAULT_FONT);
		this.saveButton.setText("Opslagen");
		SaveListener listener = new SaveListener();
		this.saveButton.addMouseListener(listener);
		panel.add(this.saveButton);

		this.revalidate();
	}

	public void startView() {
		setVisible(true);
	}
	
	public void endView() {
		setVisible(false);
	}
	
	public void saveSettings() {
		PlaceStrategies newPlaceStrategy = (PlaceStrategies) placeComboBox.getSelectedItem();
		AttackStrategies newAttackStrategy = (AttackStrategies) attackComboBox.getSelectedItem();
		
		PropertiesFile properties = this.model.getProperties();
		properties.set("placeStrategyAI", newPlaceStrategy.toString());
		properties.set("attackStrategyAI", newAttackStrategy.toString());
		properties.write();
		
		try {
			this.model.changeAIStrategies(newPlaceStrategy, newAttackStrategy);
		} catch (ModelException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		this.endView();
	}

	private class SaveListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			saveSettings();
		}

	}

}
