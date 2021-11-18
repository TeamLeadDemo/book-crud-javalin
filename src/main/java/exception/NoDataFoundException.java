package exception;

// not used in the project
public class NoDataFoundException extends Exception{
	
	String msg;
	public NoDataFoundException(String msg) {
		this.msg = msg;
	}
	@Override
	public String getMessage() {
		return this.msg;
	}

}
