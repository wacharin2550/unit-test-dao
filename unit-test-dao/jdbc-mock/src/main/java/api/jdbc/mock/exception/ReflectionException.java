package api.jdbc.mock.exception;

public class ReflectionException extends Exception {

	private static final long serialVersionUID = 1352448558516147558L;
	
	public ReflectionException(String message, Throwable throwable){
		super(message);
		this.setStackTrace(throwable.getStackTrace());
	}

}
