package pojo;

import java.time.LocalDate;

public class ErrorPojo {
	private String errorMessage;
	private LocalDate date;
	
	public ErrorPojo() {
		super();
	}

	public ErrorPojo(String errorMessage, LocalDate date) {
		super();
		this.errorMessage = errorMessage;
		this.date = date;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ErrorPojo [errorMessage=" + errorMessage + ", date=" + date + "]";
	}
	
	
	
}
