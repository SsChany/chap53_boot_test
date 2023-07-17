package com.javalab.board.service;

import java.util.List;

import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;


public interface EmployeeService {

	List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	EmployeeCommonDto getEmployees(int employeeId);
	int register(Employees emp);

}