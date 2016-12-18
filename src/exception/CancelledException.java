package exception;

public class CancelledException extends Exception {

	private static final long serialVersionUID = 1L;

	public CancelledException(String message) {
		super(message);
	}
	
}
