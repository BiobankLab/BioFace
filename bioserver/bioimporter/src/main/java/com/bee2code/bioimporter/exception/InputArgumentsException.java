package com.bee2code.bioimporter.exception;

public class InputArgumentsException extends Exception {

	public InputArgumentsException() {
		super();
	}

	public InputArgumentsException(String message) {
		super(message);
	}

	public InputArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputArgumentsException(Throwable cause) {
		super(cause);
	}
}
