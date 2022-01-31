package com.title.exceptions;

public class UnauthorizedAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4324495731157589134L;

	public UnauthorizedAccessException() {
		super();
	}

	public UnauthorizedAccessException(final String message) {
		super(message);
	}

}