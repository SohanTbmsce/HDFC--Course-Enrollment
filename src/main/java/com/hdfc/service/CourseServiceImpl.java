package com.hdfc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;
import com.hdfc.exception.CourseNotFoundException;
import com.hdfc.mapper.CourseMapper;
import com.hdfc.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	
	private final CourseRepository repository;
	private final CourseMapper mapper;
	private Integer courseIdCounter = 1;
	public CourseServiceImpl(CourseRepository repository , CourseMapper mapper) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		this.mapper = mapper;
	}
	@Override
	public List<CourseResponseDto> getAllCourses() {
		// TODO Auto-generated method stub
		return repository.findAllCourses()
				.values()
				.stream()
				.map(mapper::toResponseDto)
				.toList();
	}
	@Override
	public CourseResponseDto getCourseById(Integer id) {
		// TODO Auto-generated method stub
		Course course = repository.findById(id);
		if(course == null) {
			throw new CourseNotFoundException("Course not found with id : " + id);
		}
		return mapper.toResponseDto(course);
	}
	@Override
	public CourseResponseDto createCourse(CourseRequestDto dto) {
		// TODO Auto-generated method stub
		Course course = mapper.toEntity(dto);
		course.setCourseId(courseIdCounter++);
		repository.save(course);
		return mapper.toResponseDto(course);
		
	}
	@Override
	public CourseResponseDto updateCourse(Integer id, CourseRequestDto dto) {
		// TODO Auto-generated method stub
		Course existingCourse = repository.findById(id);
		if(existingCourse == null) {
			throw new CourseNotFoundException("Course not found : " + id); 
		}
		existingCourse.setCourseName(dto.getCourseName());
		existingCourse.setTrainerName(dto.getTrainerName());
		existingCourse.setDurationInDays(dto.getDurationInDays());
		existingCourse.setMaxCapacity(dto.getMaxCapacity());
		existingCourse.setFees(dto.getFees());
		
		repository.save(existingCourse);
		return mapper.toResponseDto(existingCourse);
	}
	@Override
	public void deleteCourse(Integer id) {
		// TODO Auto-generated method stub
		Course course = repository.findById(id);
		if(course == null) {
			throw new CourseNotFoundException("Not found the course with id : " + id);
		}
		repository.deleteById(id);		
	}
	@Override
	public List<CourseResponseDto> getCourseByTrainer(String trainerName) {
		// TODO Auto-generated method stub
		return repository.findAllCourses()
				.values()
				.stream()
				.filter(course -> course.getTrainerName().equalsIgnoreCase(trainerName))
				.map(mapper::toResponseDto)
				.toList();

	}
	@Override
	public List<CourseResponseDto> getCourseByFeesLessThan(Double amount) {
		// TODO Auto-generated method stub
		return repository.findAllCourses()
				.values()
				.stream()
				.filter(course -> course.getFees() < amount)
				.map(mapper::toResponseDto)
				.toList();	
		}

	}

