package com.app.user.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BaseRuntimeException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

    public BaseRuntimeException(String message){
        super(message);
    }

    public BaseRuntimeException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}