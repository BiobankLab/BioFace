package com.bioface.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DeserializationDataExceptionApi extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6740976321637528111L;

	public DeserializationDataExceptionApi(String message) {
		super(message);
	}

}
