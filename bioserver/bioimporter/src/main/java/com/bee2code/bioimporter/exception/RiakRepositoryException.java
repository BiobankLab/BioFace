package com.bee2code.bioimporter.exception;

public class RiakRepositoryException extends Exception {

	public RiakRepositoryException() {
		super();
	}

	public RiakRepositoryException(String message) {
		super(message);
	}

	public RiakRepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RiakRepositoryException(Throwable cause) {
		super(cause);
	}
}
