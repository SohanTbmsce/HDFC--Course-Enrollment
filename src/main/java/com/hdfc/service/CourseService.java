package com.hdfc.service;

import java.util.List;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;

public interface CourseService {
	
	List<CourseResponseDto> getAllCourses();
	CourseResponseDto getCourseById(Integer id);
	CourseResponseDto createCourse(CourseRequestDto dto);
	
	CourseResponseDto updateCourse(Integer id , CourseRequestDto dto);
	void deleteCourse(Integer id);
	
	List<CourseResponseDto> getCourseByTrainer(String trainerName);
	List<CourseResponseDto> getCourseByFeesLessThan(Double amount);
	
}
