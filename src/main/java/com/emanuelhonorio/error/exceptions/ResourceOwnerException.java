package com.emanuelhonorio.error.exceptions;

public class ResourceOwnerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MESSAGE = "usuário não é o dono deste recurso";
	
	public ResourceOwnerException() {
		super(DEFAULT_MESSAGE);
	}
	
	public ResourceOwnerException(String mensagem) {
		super(mensagem);
	}

}
