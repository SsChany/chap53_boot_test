package com.javalab.board.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Commit;
import org.springframework.ui.Model;

import com.javalab.board.service.EmployeeService;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;

import lombok.extern.slf4j.Slf4j;

/**
 * @ExtendWith(MockitoExtension.class)
 *  - JUnit5 테스트에서 Mockito 프레임워크를 통한 목(Mock)객체 
 *    를 사용하여 테스트할 수 있도록 해주는 기능. 
 *    요약하면 Mockito 프레임워크를 사용한 확장 테스트 기능을 부여함. 
 *  - @Disabled : Junit4의 @Ignore와 같은 기능
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
public class EmployeeControllerTest {

	/*
	  @Mock : 가상으로 만들어질 목객체 정의
	*/	
	@Mock
	private EmployeeService employeeService;

	/*
	  @Mock : 가상으로 만들어질 목객체 정의
	*/
	@Mock
	private Model model;

	/*
	  @InjectMocks : 가상으로 만들어질 목객체를 이용해서 테스트를 진행할 
	  				 목객체(컨트롤러) 의존성 주입
	*/
	@InjectMocks
	private EmployeeController employeeController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	// 사원 목록 조회 테스트
	//@Test 
	//@Disabled
	public void testGetEmployeeList() {
		// [1] 준비, 가상 사원 목록 ArrayList 정의와 생성
		List<EmployeeCommonDto> employeesList = new ArrayList<>();
		EmployeeCommonDto dto1 = new EmployeeCommonDto();
		dto1.setEmployeeId(1);
		dto1.setFirstName("길동");
		dto1.setLastName("홍");
		
		EmployeeCommonDto dto2 = new EmployeeCommonDto();
		dto2.setEmployeeId(2);
		dto2.setFirstName("수민");
		dto2.setLastName("이");
		
		employeesList.add(dto1);
		employeesList.add(dto2);

		/*
		 * [1] when().then() 으로 결과를 가정
		 *  - when : 가상으로 서비스 레이어의 사원 목록 조회 메소드 호출했을 때
		 *  - thenReturn(employeesList) : 위에서 만든 ArrayList가 
		 *    되돌아오도록 설정. 진행 시나리오를 설정.
		 */
		EmployeeCommonDto dto3= new EmployeeCommonDto(); // 형식적으로 전달
		when(employeeService.getEmployeesList(dto3))
			.thenReturn(employeesList);

		/*
		 * [2] 직접 컨트롤러 메소드 호출(실제 동작 실행)
		 * 컨트롤러의 getEmployeeList 메소드를 호출하게 되면 model에는
		 * employeesList라는 결과 객체가 담겨지게 된다.
		 * 그리고 반환결과는 "list"(jsp문자열)가 된다. 
		 */
		String viewName = employeeController.getEmployeeList(dto3, model);

		/*
		 * [3] 검증(verify) 및 단정(Assertions)
		 * 
		 * Mockito의 verify() 메소드로 1차 검증
		 *  - model이라는 Mock 객체에 list라는 이름으로 
		 *    employeesList가 저장되어 있는지 검증.
		 *  - list이름은 스펠링 틀리면 안됨
		 *  - employeesList는 타입만 맞으면 OK 
		 */
		verify(model).addAttribute("list", employeesList);

		// 뷰네임이 정상적으로 반환되었는지 검증
		assertEquals("/list", viewName);
	}

	// 사원 상세 보기 테스트
	@Test 
	//@Disabled
	public void testGetEmployee() {
		
		int employeeId = 100;
		
		EmployeeCommonDto dto = new EmployeeCommonDto();
		dto.setEmployeeId(employeeId);
		dto.setFirstName("길동");
		dto.setLastName("홍");
		
		/*
		 * [1] when.then 으로 결과를 가정
		 *  - when : 가상으로 서비스 레이어의 사원 목록 조회 메소드 호출했을 때
		 *  - thenReturn(dto) : 위에서 만든 dto가 
		 *    되돌아오도록 설정. 시나리오 짜놓음.
		 */
		when(employeeService.getEmployees(employeeId))
			.thenReturn(dto);

		/*
		 * [2] 직접 컨트롤러 메소드 호출(실제 동작 실행)
		 * 컨트롤러의 getEmployee 메소드를 호출하게 되면 
		 * model에는 employee라는 결과 객체가 담겨지게 된다.
		 * 그리고 반환 결과는 "/view"가 된다. 
		 */
		String viewName = employeeController.getEmployees(employeeId, model);

		/*
		 * [3] 검증 및 단정(Assertions)
		 * 
		 * Mockito의 verify() 메소드로 1차 검증
		 *  - model이라는 Mock 객체에 employee 라는 이름으로 
		 *    employee가 저장되어 있는지 검증.
		 *  - employee 이름은 스펠링 틀리면 안됨
		 *  - dto는 타입만 맞으면 OK 
		 */		
		verify(model).addAttribute("employee", dto);
		 
		// 뷰네임이 정상적으로 반환되었는지 검증
		assertEquals("/view", viewName);
		//assertEquals("길동", dto.getFirstName());
		
	}
	
	// 사원 등록 메소드
	//@Test 
	//@Disabled
	public void testRegister() {
	    // [1] 사원정보 객체 생성
	    Employees emp = new Employees();
	    emp.setEmployeeId(1);
	    emp.setFirstName("존");
	    emp.setLastName("오");
	    /*
	    Employees emp2 = new Employees();
	    emp2.setEmployeeId(1);
	    emp2.setFirstName("존");
	    emp2.setLastName("오");
		*/
	    
	    // [2] employeeService.register() 메소드의 동작 모의(시나리오 작성)
	    // register메소드를 호출하면서 위에서 만든 emp를 파라미터로 보내면
	    // 1 을 반환하기로 모의함.
	    when(employeeService.register(emp))
	    	.thenReturn(1);

	    // [3] 컨트롤러 사원 정보 조회 메소드 호출
	    String viewName = employeeController.register(emp);

	    // [4] 검증, 단정 
	    // employeeService.register() 메서드가 올바른 직원 개체로 호출되었는지 확인합니다.
	    verify(employeeService).register(emp);
	    // 반화된 뷰는 다음과 같다고 단정함.
	    assertEquals("redirect:/emp/list", viewName);
	    
	    // [5] 추가 단정(Additional assertion)
	    // 반환값이 위에서 실어 보낸 값이냐?
	    int savedId = employeeService.register(emp);
	    assertEquals(1, savedId);
	    
	}
	
	
}
