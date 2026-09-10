package com.hdfc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	@Mapping(target = "courseId" , ignore = true)
	Course toEntity(CourseRequestDto dto);
	CourseResponseDto toResponseDto(Course course);
}
