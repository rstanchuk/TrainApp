<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>TrainApp</title>
	<%@ page import="trainapp.employee.Employee" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		Employee[] employees = (Employee[])sesh.getAttribute("employees");
		
		sesh.setAttribute("employee", employees[index]);
		response.sendRedirect("/TrainApp/EditEmployee");
	%>
</body>
</html>