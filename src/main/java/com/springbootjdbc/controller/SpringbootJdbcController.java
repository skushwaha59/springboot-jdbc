package com.springbootjdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springbootjdbc.model.Employees;
import com.springbootjdbc.service.SpringbootJdbcService;


@RestController
public class SpringbootJdbcController {
	
	@Autowired
	private SpringbootJdbcService springbootJdbcService;
	
	@GetMapping("employees")
	public ResponseEntity<List<Employees>> getEmployees() {
		return springbootJdbcService.getEmployees();
	}
	
	@GetMapping("employee/{id}")
	public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") int id ) {
		return springbootJdbcService.getEmployeeById(id);
	}

}
