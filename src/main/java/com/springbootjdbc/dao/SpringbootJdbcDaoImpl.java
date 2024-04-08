package com.springbootjdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springbootjdbc.dao.mapper.EmployeesMapper;
import com.springbootjdbc.model.Employees;

@Component
public class SpringbootJdbcDaoImpl implements SpringbootJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Employees> getEmployees() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from employees limit 5;", new EmployeesMapper());
	}

	@Override
	public Employees getEmployeeById(int id) {
		String query = "select * from employees where emp_no= "+id+";";
		return jdbcTemplate.queryForObject(query, new EmployeesMapper());
	}

}
