package com.javalab.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.board.dao.EmployeeDao;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto) {
		return dao.getEmployeesList(dto);
	}

	@Override
	public EmployeeCommonDto getEmployees(int employeeId) {
		
		return dao.getEmployees(employeeId);
	}

	@Override
	public int register(Employees emp) {
		return dao.register(emp);
	}

}