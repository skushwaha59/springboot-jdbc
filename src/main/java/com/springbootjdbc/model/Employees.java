package com.springbootjdbc.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Employees {

	private Integer empNo;
	private LocalDate birthDate;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate hireDate;

}
