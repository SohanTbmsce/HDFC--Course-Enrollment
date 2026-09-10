package com.hdfc.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CourseRequestDto {
	
	@NotBlank(message = "course name cannot be blank")
	@Size(min = 2 , max = 100 , message = "name of the course should be valid")
	private String courseName;
	
	@NotBlank(message = "trainer name cannot be blank")
	@Size(min = 2 , max = 100 , message = "name of the trainer should be valid")
	private String trainerName;
	
	@NotNull(message = "course duration should not be zero or null")
	@Positive(message = "course duration should be greater than zero")
	private Integer durationInDays;
	
	@NotNull(message = "maximum capacity should not be zero or null")
	@Positive(message = "maximum capacity should be greater than zero")
	private Integer maxCapacity;
	
	@NotNull(message = "fees is required")
	@Positive(message = "fees should be greater than zero")
	private Double fees;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public Integer getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(Integer durationInDays) {
		this.durationInDays = durationInDays;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Double getFees() {
		return fees;
	}

	public void setFees(Double fees) {
		this.fees = fees;
	}
	
	


}
