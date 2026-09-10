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

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.service.CourseServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	private final CourseServiceImpl service;
	public CourseController(CourseServiceImpl service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto dto){
		
		CourseResponseDto response = service.createCourse(dto);
		return new ResponseEntity<>(
				response,
				HttpStatus.CREATED);

}
	@GetMapping
	public ResponseEntity<List<CourseResponseDto>> getAllCourses(){
		return ResponseEntity.ok(service.getAllCourses());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseResponseDto> getCourseByID(Integer id){
		CourseResponseDto response = service.getCourseById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/training/{trainerName}")
	public ResponseEntity<List<CourseResponseDto>> getCourseByTrainer(@PathVariable String trainerName){
		
		return ResponseEntity.ok(service.getCourseByTrainer(trainerName));
		
	}
	
	@GetMapping("/fees/{amount}")
	public ResponseEntity<List<CourseResponseDto>> getCourseByFeesLessThan(Double amount){
		
		return ResponseEntity.ok(service.getCourseByFeesLessThan(amount));
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Integer id, @Valid @RequestBody CourseRequestDto dto) {
		
		CourseResponseDto response = service.updateCourse(id, dto);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id){
		service.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
