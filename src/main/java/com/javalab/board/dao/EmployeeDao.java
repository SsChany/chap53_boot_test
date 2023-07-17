package com.javalab.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javalab.board.vo.Criteria;
import com.javalab.board.vo.Department;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;
import com.javalab.board.vo.Job;



/*
 * 매퍼 인터페이스 : Service Layer와 매퍼xml(sql쿼리문)을 연결해주는 역할(bridge)
 */
@Mapper
public interface EmployeeDao {
	
	public List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	public EmployeeCommonDto getEmployees(int employeeId);
	public int getTotalEmployees(Criteria cri);	// 페이징을 위한 사원숫자 조회	
	public int register(Employees emp);
	public List<Department> getDepartmentList();
	public List<Job> getJobList();

}