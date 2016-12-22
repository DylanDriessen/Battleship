package model.game;

/**
 * @author Mathias, Wouter & Dylan
 */
public interface GameObservable {
	
	void addObserver (GameObserver o);
	void removeObserver (GameObserver o);
	void notifyObservers();
	
}
