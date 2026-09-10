package com.hdfc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<String> handleCourseNotFound(CourseNotFoundException exception){
		
		return new ResponseEntity<>(
				exception.getMessage(),
				HttpStatus.NOT_FOUND
				);
		
	}
	
	@ExceptionHandler(EnrollmentNotFoundException.class)
	public ResponseEntity<String> handleEnrollmentNotFound(EnrollmentNotFoundException exception){
		
		return new ResponseEntity<>(
				exception.getMessage(),
				HttpStatus.NOT_FOUND
				);
		
	}
	
	@ExceptionHandler(DuplicateEnrollmentException.class)
	public ResponseEntity<String> handleDuplicateEnrollment(DuplicateEnrollmentException exception){
		
		return new ResponseEntity<>(
				exception.getMessage(),
				HttpStatus.CONFLICT
				);
	}
	
	@ExceptionHandler(CourseCapacityFullException.class)
	public ResponseEntity<String> handleCourseCapacity(CourseCapacityFullException exception){
		
		return new ResponseEntity<>(
				exception.getMessage(),
				HttpStatus.CONFLICT
				);
	}
			
		
}


