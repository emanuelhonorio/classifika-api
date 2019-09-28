package com.emanuelhonorio.error.exceptions;

import org.springframework.http.HttpStatus;

public class FotoStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "erro ao salvar foto";

	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public FotoStorageException() {
		super(DEFAULT_MESSAGE);
	}

	public FotoStorageException(String mensagem) {
		super(mensagem);
	}

	public FotoStorageException(String mensagem, HttpStatus status) {
		super(mensagem);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
