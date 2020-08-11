package com.bioface.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-07T16:16:28.926+02:00")

public class ApiException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7662722641741208575L;
	private int code;

	public ApiException(int code, String msg) {
		super(msg);
		this.code = code;
	}
}
