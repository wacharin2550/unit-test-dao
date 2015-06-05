package example.java.dao.tdd.exception;

public class DaoOperationException extends Exception {

	private static final long serialVersionUID = 1352448558516147558L;

	public DaoOperationException(String message, Throwable throwable) {
		super(message);
		this.setStackTrace(throwable.getStackTrace());
	}

}
