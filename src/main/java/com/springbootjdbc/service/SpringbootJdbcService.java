package com.springbootjdbc.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springbootjdbc.model.Employees;

public interface SpringbootJdbcService {

	ResponseEntity<List<Employees>> getEmployees();

	ResponseEntity<Employees> getEmployeeById(int id);

}
