package exception;

public class DomainException extends Exception {
	public DomainException(String message) {
		super(message);
	}

	public DomainException(String message, Exception e) {
		super(message, e);
	}
}
