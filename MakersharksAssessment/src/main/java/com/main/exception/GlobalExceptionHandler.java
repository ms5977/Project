package com.main.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, Object>errors=new HashMap<String, Object>();
		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		allErrors.forEach((error)->{
				String fieldName = ((FieldError)error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
		});
		
		return new ResponseEntity<Map<String,Object>>(errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(SupplierNotFoundException.class)
	public ResponseEntity<Map<String, Object>>handleSupplierNotFoundException(SupplierNotFoundException ex)
	{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Map<String, Object>errors=new HashMap<String, Object>();
		String message = ex.getMessage();
		errors.put("Message", message);
		errors.put("Status", HttpStatus.NOT_FOUND);
		errors.put("timestamp", LocalDateTime.now().format(formatter));
		
		return new ResponseEntity<Map<String,Object>>(errors,HttpStatus.NOT_FOUND);
	}
}
