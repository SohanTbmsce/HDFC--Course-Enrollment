package com.hdfc.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hdfc.entity.Enrollment;

@Repository
public class EnrollmentRepository {

	private final Map<Integer , Enrollment> enrollments = new HashMap<>();
	
	public Map<Integer , Enrollment> findAllEnrollments(){
		return enrollments;
	}
	
	public Enrollment findById(Integer id) {
		return enrollments.get(id);
	}
	
	public void save(Enrollment enrollment) {
		enrollments.put(enrollment.getEnrollementId(), enrollment);
	}
	
	public void deleteById(Integer id) {
		enrollments.remove(id);
	}
	
	public boolean existsById(Integer id) {
		return enrollments.containsKey(id);
	}

}
