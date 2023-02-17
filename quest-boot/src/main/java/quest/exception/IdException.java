package quest.exception;

public class IdException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public IdException() {
		super("id inconnu");
	}
	
	public IdException(String message) {
		super(message);
	}
}
