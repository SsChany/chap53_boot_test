[스프링 부트 단위테스트]

 
 프로젝트 생성 ; chap53_boot_test
 - 컨트롤러 테스트 관련 프로젝트 
 - 기존 프로젝트 복사[수업내용 게시판 52번 프로젝트]
 
1. pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.12</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	
	<groupId>com.javalab</groupId>
	<artifactId>bootmaven</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>chap40_boot_maven04_database</name>
	<description>Demo project for Spring Boot</description>
	
	<properties>
		<java.version>11</java.version>
	</properties>
	
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		<!-- jsp 템플릿 엔진 -->
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
 
		<!-- jstl -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>		

		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.3.0</version>
		</dependency>
		
<!--		<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
		</dependency>-->

		<!-- logback-classic 생략해도 자동 주입 -->
		<dependency>
		    <groupId>ch.qos.logback</groupId>
		    <artifactId>logback-classic</artifactId>
		    <scope>runtime</scope>
		</dependency>
				
		<dependency>
		    <groupId>org.bgee.log4jdbc-log4j2</groupId>
		    <artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
		    <version>1.16</version>
		</dependency>	
				
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		
		<!-- 버전 명시하지 않아도 알아서 받아줌 -->
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc8</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<!-- JUnit Jupiter API 생략해도 자동 주입(JUnit5 버전)-->
	    <dependency>
	        <groupId>org.junit.jupiter</groupId>
	        <artifactId>junit-jupiter-api</artifactId>
	        <scope>test</scope>
	    </dependency>
	    
	    <!-- JUnit Jupiter Engine 생략해도 자동 주입 -->
	    <dependency>
	        <groupId>org.junit.jupiter</groupId>
	        <artifactId>junit-jupiter-engine</artifactId>
	        <scope>test</scope>
	    </dependency>
	    
	    <!-- Mockito Core(목(Mock) - 생략해도 자동 주입됨) -->
	    <dependency>
	        <groupId>org.mockito</groupId>
	        <artifactId>mockito-core</artifactId>
	        <scope>test</scope>
	    </dependency>
	    
	    <!-- Mockito JUnit Jupiter -->
	    <dependency>
	        <groupId>org.mockito</groupId>
	        <artifactId>mockito-junit-jupiter</artifactId>
	        <scope>test</scope>
	    </dependency>
		
		
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

2. 컨트롤러 테스트 케이스 클래스 생성
 1) src/test/java
  - 패키지 : com.javalab.board.controller
  - 단위테스트 케이스 클래스 : EmployeeControllerTest

3. 단위테스트 케이스 클래스

package com.javalab.board.controller;
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
	@Test 
	@Disabled
	public void testGetEmployeeList() {
		// [1] 준비, 가상 사원 목록 ArrayList 정의와 생성
		List<EmployeeCommonDto> employeesList = 
					new ArrayList<>();
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
		 * [1] when.then 으로 결과를 가정
		 *  - when : 가상으로 서비스 레이어의 사원 목록 조회 메소드 
		     호출했을 때
		 *  - thenReturn(employeesList) : 위에서 만든 
		     ArrayList가 되돌아오도록 설정.
		 */
		EmployeeCommonDto dto3= new EmployeeCommonDto(); 
		
		when(employeeService.getEmployeesList(dto3))
			.thenReturn(employeesList);

		/*
		 * [2] 직접 컨트롤러 메소드 호출(실제 동작 실행)
		 * 컨트롤러의 getEmployeeList 메소드를 호출하게 되면 
		   model에는 employeesList라는 결과 객체가 담겨지게 된다.
		 * 그리고 반환결과는 "list"(jsp문자열) 가 된다. 
		 */
		String viewName = employeeController.getEmployeeList
											(dto3, model);

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
	@Disabled
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
		String viewName = employeeController.getEmployees
											(employeeId, model);

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
	@Test 
	@Disabled
	public void testRegister() {
	    // [1] 사원정보 객체 생성
	    Employees emp = new Employees();
	    emp.setEmployeeId(1);
	    emp.setFirstName("존");
	    emp.setLastName("오");

	    // [2] employeeService.register() 메서드의 동작 모의(시나리오 작성)
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
	}
	
	
[서비스 Layer]	
public interface EmployeeService {
	List<EmployeeCommonDto> 
		getEmployeesList(EmployeeCommonDto dto);		
	EmployeeCommonDto getEmployees(int employeeId);
	int register(Employees emp);

[Dao Layer]
@Mapper
public interface EmployeeDao {	
	public List<EmployeeCommonDto> getEmployeesList(
						EmployeeCommonDto dto);
	public EmployeeCommonDto getEmployees(int employeeId);
	public int register(Employees emp);
}


}

	    
[주의]

1. 매퍼XML에서 vo를 풀 경로가 아니고 이름만 사용할 경우 빨간 밑줄 
 - Window > preference > validation > MyBatis Mapper Validator 체크 해제
 	    
