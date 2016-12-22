package exception;

/**
 * @author Wouter
 */
public class ModelException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModelException(String message) {
		super(message);
	}

	public ModelException(String message, Exception e) {
		super(message, e);
	}
}
