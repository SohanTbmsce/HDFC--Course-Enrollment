package com.hdfc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.entity.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
	@Mapping(target = "enrollementId" , ignore = true)
	@Mapping(target = "enrollmentDate" , ignore = true)
	@Mapping(target = "status" , ignore = true)
	Enrollment toEntity(EnrollmentRequestDto dto);
	EnrollmentResponseDto toResponseDto(Enrollment enrollment);
}
