package com.hdfc.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.entity.Course;
import com.hdfc.entity.Enrollment;
import com.hdfc.exception.CourseCapacityFullException;
import com.hdfc.exception.CourseNotFoundException;
import com.hdfc.exception.DuplicateEnrollmentException;
import com.hdfc.exception.EnrollmentNotFoundException;
import com.hdfc.mapper.EnrollmentMapper;
import com.hdfc.repository.CourseRepository;
import com.hdfc.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	private final EnrollmentRepository enrollmentRepository;
	private final CourseRepository courseRepository;
	private final EnrollmentMapper mapper;
	private Integer enrollmentIdCounter = 1;
	

	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository,
			EnrollmentMapper mapper) {
		super();
		this.enrollmentRepository = enrollmentRepository;
		this.courseRepository = courseRepository;
		this.mapper = mapper;
	}

	@Override
	public List<EnrollmentResponseDto> getAllEnrollments() {
		// TODO Auto-generated method stub
		return enrollmentRepository.findAllEnrollments()
				.values()
				.stream()
				.map(mapper::toResponseDto)
				.toList();
	}

	@Override
	public EnrollmentResponseDto getEnrollmentById(Integer id) {
		// TODO Auto-generated method stub
		Enrollment enrollment = enrollmentRepository.findById(id);
		if(enrollment == null) {
			throw new EnrollmentNotFoundException("Enrollment not found : " + id);
		}
		return mapper.toResponseDto(enrollment);
	}

	@Override
	public EnrollmentResponseDto enrollEmployee(EnrollmentRequestDto dto) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findById(dto.getCourseId());
		if(course == null) {
			throw new CourseNotFoundException("Not found : " + dto.getCourseId());
		}
		
		boolean isAlreadyEnrolled = enrollmentRepository.findAllEnrollments()
				.values()
				.stream()
				.anyMatch(enrollment ->
				enrollment.getEmployeeId().equals(dto.getEmployeeId())
				&& enrollment.getCourseId().equals(dto.getCourseId())
				&& !"CANCELLED".equals(enrollment.getStatus()));
		if(isAlreadyEnrolled) {
			throw new DuplicateEnrollmentException("Employee is already enrolled to course : " + dto.getCourseId());
		}
		long enrolledCount = enrollmentRepository.findAllEnrollments()
				.values()
				.stream().filter(enrollment -> enrollment.getEmployeeId().equals(dto.getCourseId()))
				.filter(enrollment -> enrollment.getCourseId().equals(dto.getCourseId()))
				.count();
		if(enrolledCount > course.getMaxCapacity()) {
			throw new CourseCapacityFullException("Employee has already reached max course enrollment capacity : " + course.getMaxCapacity());
		}
		Enrollment enrollment = mapper.toEntity(dto);
		enrollment.setEnrollementId(enrollmentIdCounter++);
		enrollment.setEnrollmentDate(LocalDate.now());
		enrollmentRepository.save(enrollment);
		return mapper.toResponseDto(enrollment);
	}

	@Override
	public EnrollmentResponseDto completeEnrollment(Integer id) {
		// TODO Auto-generated method stub
		Enrollment enrollment = enrollmentRepository.findById(id);
		if(enrollment == null) {
			throw new EnrollmentNotFoundException("Not found enrollment : " + id);
		}
		enrollment.setStatus("COMPLETED");
		enrollmentRepository.save(enrollment);
		return mapper.toResponseDto(enrollment);
	}

	@Override
	public EnrollmentResponseDto cancelEnrollment(Integer id) {
		// TODO Auto-generated method stub
		Enrollment enrollment = enrollmentRepository.findById(id);
		if(enrollment == null) {
			throw new EnrollmentNotFoundException("Not found enrollment : " + id);
		}
		enrollment.setStatus("CANCELLED");
		enrollmentRepository.save(enrollment);
		return mapper.toResponseDto(enrollment);
	}

	@Override
	public List<EnrollmentResponseDto> getEnrollmentByStatus(String status) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findAllEnrollments()
				.values()
				.stream()
				.filter(enrollment -> enrollment.getStatus().equals(status))
				.map(mapper::toResponseDto)
				.toList();
	}

	@Override
	public List<EnrollmentResponseDto> getEmployeesById(Integer id) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findAllEnrollments()
		.values()
		.stream()
		.filter(enrollment -> enrollment.getEmployeeId().equals(id))
		.map(mapper::toResponseDto)
		.toList();
	}

	@Override
	public void deleteEnrollment(Integer enrollmentId) {
		// TODO Auto-generated method stub
		Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
		if(enrollment == null) {
			throw new EnrollmentNotFoundException("Not found enrollment : " + enrollmentId);
		}
		enrollmentRepository.deleteById(enrollmentId);
	}

}
