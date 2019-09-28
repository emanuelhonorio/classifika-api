package com.emanuelhonorio.error.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MESSAGE = "recurso não encontrado";
	
	public ResourceNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}

}
