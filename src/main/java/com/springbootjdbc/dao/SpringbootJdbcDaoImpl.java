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
		String query = "select * from employees where emp_no= " + id + ";";
		return jdbcTemplate.queryForObject(query, new EmployeesMapper());
	}

	@Override
	public Employees updateEmployeeById(Employees employees) {
		// TODO Auto-generated method stub
		String updateQuery = "Update employees SET birth_date  ='"+employees.getBirthDate()+"', first_name ='"+employees.getFirstName()+"' ,last_name ='"+employees.getLastName()+"' ,gender ='"+employees.getGender()+"' , hire_date ='"+employees.getHireDate()+"' where emp_no =  "+employees.getEmpNo()+";";
		jdbcTemplate.update(updateQuery);
		return getEmployeeById(employees.getEmpNo());
	}

	@Override
	public int deleteEmployeeById(int id) {
		String query = "delete from employees where emp_no= " + id + ";";
		return jdbcTemplate.update(query);
	}

	@Override
	public Employees createEmployee(Employees employees) {
		String sql = "Insert into employees values(?,?,?,?,?,?);";
		jdbcTemplate.update(sql,employees.getEmpNo(),employees.getBirthDate(),employees.getFirstName(),employees.getLastName(),employees.getGender(),employees.getHireDate());
		return getEmployeeById(employees.getEmpNo());
	}

}
