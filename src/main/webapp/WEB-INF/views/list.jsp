<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!-- Inline Style Sheet 인라인 스타일 시트 -->
	<style>
		table, th, td {
			border: 1px solid black;
	</style>
</head>

<body>
	<table>
		<caption>사원목록</caption>
		 <tr>
			 <td width="50px" align="center">id</td>
			 <td align="center">name</td>
			 <td align="center">email</td>
			 <td align="center">phoneNumber</td>
			 <td align="center">hireDate</td>
			 <td align="center">jobId</td>
			 <td align="center">salary</td>
			 <td align="center">pct</td>
			 <td align="center">managerId</td>
			 <td align="center">departmentId</td>
		 </tr>
	
		 <c:forEach items="${list}" var="employees">
			 <tr>
				 <td>
					<a href="<c:url value='/emp/read?id=${employees.employeeId}' />">
						<c:out value="${employees.employeeId}" />
					</a>			 	
				 </td>
				 <td>${employees.firstName} ${employees.lastName}</td>
				 <td>${employees.email}</td>
				 <td>${employees.phoneNumber}</td>
				 <td>${employees.hireDate}</td>
				 <td>${employees.jobId}</td>
				 <td>${employees.salary}</td>
				 <td>${employees.commissionPct}</td>
				 <td>${employees.managerId }</td>
				 <td>${employees.departmentId }</td>	
			 </tr>
		 </c:forEach>
	
	</table>

</body>
</html>
