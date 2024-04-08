package com.springbootjdbc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springbootjdbc.model.Employees;

public class EmployeesMapper implements RowMapper<Employees> {

	@Override
	public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employees employees = new Employees();
		employees.setEmpNo(rs.getInt(1));
		employees.setBirthDate(rs.getDate(2).toLocalDate());
		employees.setFirstName(rs.getString(3));
		employees.setLastName(rs.getString(4));
		employees.setGender(rs.getString(5));
		employees.setHireDate(rs.getDate(6).toLocalDate());
		return employees;
	}

}
