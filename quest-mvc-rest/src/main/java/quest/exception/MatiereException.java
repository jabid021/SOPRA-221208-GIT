package quest.exception;

public class MatiereException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MatiereException() {
		
	}
	
	public MatiereException(String message) {
		super(message);
	}
}
