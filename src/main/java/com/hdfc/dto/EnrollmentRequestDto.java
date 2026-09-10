package com.hdfc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequestDto {
	
	@NotNull(message = "employee id cannot be null")
	@Positive(message = "employee id must be positive")
	private Integer employeeId;
	
	@NotBlank(message = "Employee name cannot be blank")
	@Size(min=3 , max=100 , message = "employee name must be valid")
	private String employeeName;
	
	@NotNull(message = "course id cannot be null")
	@Positive(message = "course id must be positive")
	private Integer courseId;

}
