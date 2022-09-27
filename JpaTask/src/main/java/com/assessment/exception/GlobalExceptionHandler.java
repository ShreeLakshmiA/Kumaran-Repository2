package com.assessment.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	// Handle specific Exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, 
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoContentFoundException.class)
	public ResponseEntity<?> handleNoContentFoundException(NoContentFoundException e,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	// Handle Global Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
