package com.emanuelhonorio.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emanuelhonorio.error.exceptions.EmailAlreadyUsedException;
import com.emanuelhonorio.error.exceptions.FotoStorageException;
import com.emanuelhonorio.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.error.exceptions.ResourceOwnerException;

@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmailAlreadyUsedException.class)
	public ResponseEntity<?> handleEmailAlredyUsedException(EmailAlreadyUsedException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(FotoStorageException.class)
	public ResponseEntity<?> handleFotoStorageException(FotoStorageException ex) {
		return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(ResourceOwnerException.class)
	public ResponseEntity<?> handleResourceOwnerException(ResourceOwnerException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
