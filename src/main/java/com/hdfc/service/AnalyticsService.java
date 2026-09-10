package com.hdfc.service;

import com.hdfc.dto.CourseResponseDto;

public interface AnalyticsService {
	long getCourseCount();
	long getEnrollmentCount();
	CourseResponseDto getMostPopularCourse();
}
