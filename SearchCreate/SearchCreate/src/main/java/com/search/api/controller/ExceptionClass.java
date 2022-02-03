package com.search.api.controller;

public class ExceptionClass extends RuntimeException {
	private static final long serialVersionUID = 1L;

	  public ExceptionClass(Exception e) {
	    super(e);
	  }

}
