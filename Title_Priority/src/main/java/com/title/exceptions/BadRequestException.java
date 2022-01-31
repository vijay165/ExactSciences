package com.title.exceptions;


public class BadRequestException extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(final String message) {
		super(message);
	}

}