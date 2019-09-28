package com.emanuelhonorio.error.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "email já em uso";
	
	public EmailAlreadyUsedException() {
		super(DEFAULT_MESSAGE);
	}
}
