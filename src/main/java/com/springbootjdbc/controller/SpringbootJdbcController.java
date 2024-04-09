package com.springbootjdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PutMapping("employee")
	public ResponseEntity<Employees> updateEmployeeById(@RequestBody Employees employees) {
		return springbootJdbcService.updateEmployeeById(employees);
	}
	
	@DeleteMapping("employee/{id}")
	public ResponseEntity<Integer> deleteEmployeeById(@PathVariable("id") int id ) {
		return springbootJdbcService.deleteEmployeeById(id);
	}
	
	@PostMapping("employee")
	public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees) {
		return springbootJdbcService.createEmployee(employees);
	}

}
