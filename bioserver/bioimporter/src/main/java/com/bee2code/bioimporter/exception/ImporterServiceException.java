package com.bee2code.bioimporter.exception;

public class ImporterServiceException extends Exception {

	public ImporterServiceException() {
		super();
	}

	public ImporterServiceException(String message) {
		super(message);
	}

	public ImporterServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImporterServiceException(Throwable cause) {
		super(cause);
	}
}
