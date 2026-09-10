package com.hdfc.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hdfc.entity.Course;

@Repository
public class CourseRepository {

	// findAll - Map<Integer , Course>
	// findById - Course(Integer id)
	// save - void(Course course)
	// void deleteById(Integer Id)
	// boolean existsById(Integer Id)
	
	private final Map<Integer , Course> courses = new HashMap<>();
	
	public Map<Integer , Course> findAllCourses(){
		return courses;
	}
	
	public Course findById(Integer id) {
		return courses.get(id);
	}
	
	public void save(Course course) {
		courses.put(course.getCourseId(), course);
	}
	
	public void deleteById(Integer id) {
		courses.remove(id);
	}
	
	public boolean existsById(Integer id) {
		return courses.containsKey(id);
	}

}
