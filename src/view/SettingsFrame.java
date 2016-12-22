package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.enums.AttackStrategies;
import model.enums.PlaceStrategies;
import model.facade.IModelFacade;
import properties.PropertiesFile;

/**
 * @author Mathias, Wouter
 */
public class SettingsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<PlaceStrategies> placeComboBox;
	private JComboBox<AttackStrategies> attackComboBox;
	private JButton saveButton;
	private IModelFacade model;
	private JLabel placeStrategyLabel, attackStrategyLabel;

	public SettingsFrame(IModelFacade model) {
		this.model = model;
		PropertiesFile properties = this.model.getProperties();
		
		this.setSize(new Dimension(300, 270));
		this.setResizable(false);
		this.setTitle("Instellingen");
		
		JPanel panel = new JPanel();		
		panel.setLayout(new GridBagLayout());
		panel.setBorder(GameFrame.PADDING);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
	    gbc.gridheight = 1;
	    gbc.ipady = 6;
		this.add(panel);
		setContentPane(panel);

		this.placeStrategyLabel = new JLabel("Plaatsingsstrategie:");
		this.placeStrategyLabel.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridy = 0;
		gbc.insets = new Insets(20, 0, 3, 0);
		panel.add(this.placeStrategyLabel, gbc);
		
		PlaceStrategies currPlaceStrategy = PlaceStrategies.valueOf(properties.get("placeStrategyAI").toUpperCase());
		this.placeComboBox = new JComboBox<PlaceStrategies>(PlaceStrategies.values());
		this.placeComboBox.setSelectedItem(currPlaceStrategy);
		this.placeComboBox.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 15, 0);
		panel.add(this.placeComboBox, gbc);

		this.attackStrategyLabel = new JLabel("Aanvalsstrategie:");
		this.attackStrategyLabel.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 3, 0);
		panel.add(this.attackStrategyLabel, gbc);
		
		AttackStrategies currAttackStrategy = AttackStrategies.valueOf(properties.get("attackStrategyAI").toUpperCase());
		this.attackComboBox = new JComboBox<AttackStrategies>(AttackStrategies.values());
		this.attackComboBox.setSelectedItem(currAttackStrategy);
		this.attackComboBox.setFont(GameFrame.DEFAULT_FONT);
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 25, 0);
		panel.add(this.attackComboBox, gbc);

		this.saveButton = new JButton();
		this.saveButton.setFont(GameFrame.DEFAULT_FONT);
		this.saveButton.setText("Bewaren");
		gbc.gridy = 4;
		panel.add(this.saveButton, gbc);

		this.revalidate();
	}

	public void startView() {
		setVisible(true);
	}
	
	public void endView() {
		setVisible(false);
	}
	
	public JComboBox<PlaceStrategies> getPlaceComboBox() {
		return this.placeComboBox;
	}
	
	public JComboBox<AttackStrategies> getAttackComboBox() {
		return this.attackComboBox;
	}
	
	public JButton getSaveButton() {
		return this.saveButton;
	}

}
