package view;

public interface View {

	void startView();
	void openSettings();
	GamePanel getPanelPlayer();
	GamePanel getPanelAI();
	SelectionPanel getSelectionPanel();
	void showErrorMessage(String message);
	String getStringInput(String message);
	
}
