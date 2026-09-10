package com.hdfc.service;

import org.springframework.stereotype.Service;

import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;
import com.hdfc.mapper.CourseMapper;
import com.hdfc.repository.CourseRepository;
import com.hdfc.repository.EnrollmentRepository;

@Service
public class AnalyticsImpl implements AnalyticsService{
	private final EnrollmentRepository e_repository;
	private final CourseRepository repository;
	private final CourseMapper mapper;
	public AnalyticsImpl(EnrollmentRepository e_repository , CourseRepository repository , CourseMapper mapper) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		this.e_repository = e_repository;
		this.mapper = mapper;
	}
	@Override
	public long getCourseCount() {
		// TODO Auto-generated method stub
		return repository.findAllCourses().values().stream().count();
	}
	@Override
	public long getEnrollmentCount() {
		// TODO Auto-generated method stub
		return e_repository.findAllEnrollments().values().stream().count();
	}
	@Override
	public CourseResponseDto getMostPopularCourse() {
		// TODO Auto-generated method stub
		Course popularCourse = repository.findAllCourses().values().stream()
				.max((Course course1,Course course2) -> {
					long count1 = e_repository.findAllEnrollments().values().stream()
							.filter(enrollment -> enrollment.getCourseId().equals(course1.getCourseId())).count();
					long count2 = e_repository.findAllEnrollments().values().stream()
							.filter(enrollment -> enrollment.getCourseId().equals(course2.getCourseId())).count();
					return Long.compare(count1, count2);
				}).orElse(null);
		if(popularCourse == null) {
			return null;
		}
		return mapper.toResponseDto(popularCourse);
	}

}
