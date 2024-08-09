package com.blog.mains.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.blog.mains.payloads.ApiResponse;
@RestControllerAdvice
//@ResponseBody
//@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message, false, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String>errors=new HashMap<String, String>();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		allErrors.forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			System.out.println("Field Name:"+fieldName);
			System.out.println("Error Message:"+errorMessage);
			errors.put(fieldName, errorMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HandlerMethodValidationException.class)
	 public ResponseEntity<Map<String, String>> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getAllErrors().forEach((error)->{
	        	String fieldName=((FieldError)error).getField();
	        	String errorMessage = error.getDefaultMessage();
	        	errors.put(fieldName, errorMessage);
	        	
	        });
	        return new  ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>>handleIllegalArgumentException(IllegalArgumentException ex)
	{
		Map<String, Object>errors=new HashMap<String, Object>();
		errors.put("Error", "Data Not Found that Corresponded Id ,provide correct Id");
		errors.put("Message", ex.getMessage());
//		errors.put("Path",ex.);
		errors.put("timeStamp",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
		errors.put("Status", HttpStatus.NOT_FOUND);
		return new  ResponseEntity<Map<String,Object>>(errors,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String, Object>>handleNoResourceFoundException(NoResourceFoundException ex)
	{ 
		Map<String, Object>errors=new HashMap<String, Object>();
		errors.put("Error", "Please Provide id to Perforn the Operation");
		errors.put("Message", ex.getMessage());
		errors.put("Path", ex.getResourcePath());
		errors.put("timeStamp",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
		errors.put("Status", HttpStatus.NOT_FOUND);
		return new  ResponseEntity<Map<String,Object>>(errors,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Map<String, Object>>handleApiException(ApiException ex)
	{
		Map<String, Object> errors=new HashMap<String, Object>();
		errors.put("Message", ex.getMessage());
		errors.put("timeStamp",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
		errors.put("Status", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Map<String,Object>>(errors,HttpStatus.UNAUTHORIZED);
	}
	

}
