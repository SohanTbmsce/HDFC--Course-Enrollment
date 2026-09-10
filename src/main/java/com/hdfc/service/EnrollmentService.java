package com.hdfc.service;

import java.util.List;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;

public interface EnrollmentService {
	
	List<EnrollmentResponseDto> getAllEnrollments();
	EnrollmentResponseDto getEnrollmentById(Integer id);
	EnrollmentResponseDto enrollEmployee(EnrollmentRequestDto dto);
	
	EnrollmentResponseDto completeEnrollment(Integer id);
	EnrollmentResponseDto cancelEnrollment(Integer id);
	void deleteEnrollment(Integer enrollmentId);
	
	List<EnrollmentResponseDto> getEnrollmentByStatus(String status);
	List<EnrollmentResponseDto> getEmployeesById(Integer id);
	

}
