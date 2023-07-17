package com.javalab.board.dao;

import com.javalab.board.vo.Criteria;
import com.javalab.board.vo.Department;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;
import com.javalab.board.vo.Job;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * [Dao Layer Test]
 * 
 * @Test : 테스트할 메소드에 필요한 어노테이션
 * @Disabled : 테스트에서 제외할 메소드에 필요한 어노테이션
 *  둘중에 하나를 선택해서 테스트 할 것.
 * @Transactional : 트랜잭션 걸면 기본이 롤백됨.
 *  - @Commit : 실제로 디비에 넣고 싶으면 메소드 적용해줄것.
 *  @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *   - application.properties에 설정된 대로 데이터베이스 설정 사용해서 테스트
 */

/*
 * @SpringBootTest` 주석은 Spring Boot 애플리케이션에서 특정 클래스 또는 
 * 메서드가 Spring 애플리케이션의 컨텍스트에서 실행되어야 하는 테스트임을 
 * 나타내기 위해 사용됩니다. Spring 애플리케이션 컨텍스트를 부트스트랩하고 
 * 테스트를 위한 Spring 환경을 제공하기 위해 다양한 시나리오에서 사용할 수 
 * 있는 다목적 어노테이션.
 * 
 * Spring 애플리케이션 컨텍스트 부트스트랩: 애플리케이션에 정의된 모든 빈과 
 * 종속성을 포함하여 완전한 Spring 애플리케이션 컨텍스트의 로드 및 구성을 트리거. 
 * 응용 프로그램이 실행될 때 사용되는 것과 유사한 환경을 만듭니다.
 */

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @BeforeEach
    public void setup() {
    	log.info("여기는 테스트 하기 전에 매번 실행되는 메소드");
    }

    // 사원 목록 조회 테스트
    @Disabled 
    //@Test
    public void testGetEmployeesList() {
        EmployeeCommonDto dto = new EmployeeCommonDto();
        List<EmployeeCommonDto> employeesList = employeeDao.getEmployeesList(dto);
        assertNotNull(employeesList);
        for (EmployeeCommonDto employee : employeesList) {
            log.info(employee.toString());
        }
    }
    
    // 사원 상세 정보 조회 테스트
    @Disabled 
    //@Test
    public void testGetEmployees() {
        int employeeId = 100;
        EmployeeCommonDto employee = employeeDao.getEmployees(employeeId);
        assertNotNull(employee);
        assertEquals(employeeId, employee.getEmployeeId());
    }

    // 전체 사원수 조회 테스트
    @Disabled 
    //@Test
    public void testGetTotalEmployees() {
        Criteria cri = new Criteria();
        int totalEmployees = employeeDao.getTotalEmployees(cri);
        assertEquals(0, totalEmployees);
    }

    /*
     *  사원 등록 테스트
     *  @Commit : 클래스 차원에 @Transactional이 걸려 있으면 모든
     *  테스트는 롤백됨, 하지만 실제로 데이터베이스 들어가는 지 눈으로
     *  확인하고 싶을 때는 @Commit을 걸면 됨. 
     */
    @Test
    @Commit
    public void testRegister() {
        Employees emp = new Employees();
        emp.setEmployeeId(244);
        emp.setFirstName("정희");
        emp.setLastName("안");
        emp.setEmail("dream@a.com");
        emp.setHireDate("2023-06-05");
        emp.setSalary(50000L);

        // 처리 결과 건수(성공-1)
        int result = employeeDao.register(emp);
        assertEquals(1, result);
    }
}
