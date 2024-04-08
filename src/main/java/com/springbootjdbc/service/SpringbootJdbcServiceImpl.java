package com.springbootjdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springbootjdbc.dao.SpringbootJdbcDao;
import com.springbootjdbc.model.Employees;


@Service
public class SpringbootJdbcServiceImpl implements SpringbootJdbcService {
	
	@Autowired
	private SpringbootJdbcDao springbootJdbcDao;

	@Override
	public ResponseEntity<List<Employees>> getEmployees() {
		// TODO Auto-generated method stub
		List<Employees> emp = springbootJdbcDao.getEmployees();
		return new ResponseEntity<List<Employees>>(emp,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employees> getEmployeeById(int id) {
		Employees employees = springbootJdbcDao.getEmployeeById(id);
		return new ResponseEntity<Employees>(employees,HttpStatus.OK);
	}

}
