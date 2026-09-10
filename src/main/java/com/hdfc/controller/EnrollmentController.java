package com.hdfc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.service.EnrollmentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	
	private final EnrollmentServiceImpl service;
	public EnrollmentController(EnrollmentServiceImpl service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<EnrollmentResponseDto> enrollEmployee(@Valid @RequestBody EnrollmentRequestDto dto) {
		
		EnrollmentResponseDto response = service.enrollEmployee(dto);
		return new ResponseEntity<>(
				response , 
				HttpStatus.CREATED
				);				
	}
	
	@GetMapping
	public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments(){
		
		return ResponseEntity.ok(service.getAllEnrollments());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentResponseDto> getEnrollmentById(@PathVariable Integer enrollmentId){
		
		return ResponseEntity.ok(service.getEnrollmentById(enrollmentId));
	}
	
	@PutMapping("/{id}/cancel")
	public ResponseEntity<EnrollmentResponseDto> cancelEnrollment(@PathVariable Integer id){
		
		EnrollmentResponseDto response = service.cancelEnrollment(id);
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/{id}/create")
	public ResponseEntity<EnrollmentResponseDto> completeEnrollment(@PathVariable Integer id) {
		
		EnrollmentResponseDto response = service.completeEnrollment(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/status/{status}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByStatus(@PathVariable String status) {

        return ResponseEntity.ok(service.getEnrollmentByStatus(status));
        
    }
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<EnrollmentResponseDto>> getEmployeesById(Integer id){
		
		return ResponseEntity.ok(service.getEmployeesById(id));
	
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteEnrollment(Integer enrollmentId){
		
		service.deleteEnrollment(enrollmentId);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	
}
