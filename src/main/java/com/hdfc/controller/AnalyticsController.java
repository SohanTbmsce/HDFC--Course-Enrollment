package com.hdfc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.CourseResponseDto;
import com.hdfc.service.AnalyticsService;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

	private final AnalyticsService service;

	public AnalyticsController(AnalyticsService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/course-count")
	public ResponseEntity<Long> getCourseCount(){
		return ResponseEntity.ok(service.getCourseCount());
	}
	
	@GetMapping("/enrollment-count")
	public ResponseEntity<Long> getEnrollmentCount(){
		return ResponseEntity.ok(service.getEnrollmentCount());
	}
	
	@GetMapping("/popular-course")
	public ResponseEntity<CourseResponseDto> getMostPopularCourse(){
		return ResponseEntity.ok(service.getMostPopularCourse());
	}
}
