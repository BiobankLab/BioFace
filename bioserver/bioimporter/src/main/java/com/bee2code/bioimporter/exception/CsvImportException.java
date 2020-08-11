package com.bee2code.bioimporter.exception;

public class CsvImportException extends Exception {

	public CsvImportException() {
		super();
	}

	public CsvImportException(String message) {
		super(message);
	}

	public CsvImportException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvImportException(Throwable cause) {
		super(cause);
	}
}
