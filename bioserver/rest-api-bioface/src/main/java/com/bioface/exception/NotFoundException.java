package com.bioface.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-07T16:16:28.926+02:00")

public class NotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3799424611589017665L;
	private int code;

	public NotFoundException(int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
