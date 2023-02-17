package quest.exception;

public class FormateurException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public FormateurException() {
		
	}
	
	public FormateurException(String message) {
		super(message);
	}
}
