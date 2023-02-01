package quest.exception;

public class IdException extends RuntimeException {
	public IdException() {
		super("id inconnu");
	}
	
	public IdException(String message) {
		super(message);
	}
}
