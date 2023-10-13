package com.home.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExistsException.class)
	public Map<String, String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("email", ex.getMessage());
		return errors;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(BadCredentialsException.class)
	public Map<String, String> handleLoginFailedException(BadCredentialsException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("otherError", "Email or password invalid");
		return errors;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(RefreshTokenException.class)
	public Map<String, String> handleUserAlreadyExistsException(RefreshTokenException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("requestRefreshToken", ex.getMessage());
		return errors;
	}

	// Handle Exception.class
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnwantedException(Exception e) {
		logger.error(e.getMessage());
		return ResponseEntity.status(500).body("Unknow error");
	}
}
