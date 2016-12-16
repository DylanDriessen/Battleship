package model.game;

public interface GameObservable {
	
	void addObserver (GameObserver o);
	void removeObserver (GameObserver o);
	void notifyGameChanged();
	
}
