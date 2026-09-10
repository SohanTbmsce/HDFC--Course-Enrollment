package com.hdfc.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

	private Integer enrollementId;
	private Integer employeeId;
	private String employeeName;
	private Integer courseId;
	private LocalDate enrollmentDate;
	private String status;

}
