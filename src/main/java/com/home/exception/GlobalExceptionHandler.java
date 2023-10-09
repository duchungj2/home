package com.home.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<String, String>();
//        ex.getBindingResult().getFieldErrors()
//                .stream().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
//        return new ResponseEntity<>(getErrorsMap(errors), ex.getStatusCode());
//    }

    private Map<String, Map<String, String>> getErrorsMap(Map<String, String> errors) {
        Map<String, Map<String, String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

	// Nên bắt cả Exception.class
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnwantedException(Exception e) {
		// Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
//		e.printStackTrace(); // Thực tế người ta dùng logger
		return ResponseEntity.status(500).body("Unknow error");
	}
}
