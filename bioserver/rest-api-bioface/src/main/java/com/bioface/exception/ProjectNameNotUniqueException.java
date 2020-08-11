package com.bioface.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProjectNameNotUniqueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3667662676334112064L;

	public ProjectNameNotUniqueException(String message) {
		super(message);
	}

}
