package com.home.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
}
