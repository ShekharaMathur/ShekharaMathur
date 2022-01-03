package com.app.user.exception;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.app.user.utils.ApiError;

@ControllerAdvice
public class UserException {
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {

		final JSONArray desc = new JSONArray();
		JSONObject err = new JSONObject();
		err.put("Reason", ex.getMessage());
		desc.add(err);
		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Resource Not Found", desc);
		System.out.println(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

	}

}
