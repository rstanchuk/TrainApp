<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TrainApp</title>
	<%@ page import="trainapp.employee.Employee" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
	<%@ page import="trainapp.admin.dao.AdminDAO" %>
	<%@ page import="trainapp.admin.dao.AdminDAOimpl" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		Employee[] employees = (Employee[])sesh.getAttribute("employees");
		
		AdminDAO ad = new AdminDAOimpl();
		ad.deleteEmployee(employees[index]);
		response.sendRedirect("/TrainApp/Asession");
	%>
</body>
</html>