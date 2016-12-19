package view;

public interface View {

	public void startView();
	public void openSettings();
	public GamePanel getPanelPlayer();
	public GamePanel getPanelAI();
	public SelectionPanel getSelectionPanel();
	
}
