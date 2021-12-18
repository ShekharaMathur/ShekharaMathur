package com.app.user.exception;

public class UserAlreadyExistsException extends RuntimeException {
	
	/**
	 * To handle duplicate data in db
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public UserAlreadyExistsException() {
    }

}
