package com.app.user.exception;

public class UserAlreadyPresentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyPresentException() {
		super();

	}

	public UserAlreadyPresentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public UserAlreadyPresentException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserAlreadyPresentException(String message) {
		super(message);

	}

	public UserAlreadyPresentException(Throwable cause) {
		super(cause);

	}

}
