package model.board;

/**
 * @author Mathias, Wouter & Dylan
 */
public interface BoardObservable {
	
	void addObserver (BoardObserver o);
	void removeObserver (BoardObserver o);
	void notifyObservers();

}
