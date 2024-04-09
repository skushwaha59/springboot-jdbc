package com.springbootjdbc.dao;

import java.util.List;

import com.springbootjdbc.model.Employees;

public interface SpringbootJdbcDao {

	List<Employees> getEmployees();

	Employees getEmployeeById(int id);

	Employees updateEmployeeById(Employees employees);

	int deleteEmployeeById(int id);

	Employees createEmployee(Employees employees);

}
