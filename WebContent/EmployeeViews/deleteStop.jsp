<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>TrainApp</title>
	<%@ page import="trainapp.forum.SupportTicket" %>
	<%@ page import="trainapp.employee.dao.EmployeeDAO" %>
	<%@ page import="trainapp.employee.dao.EmployeeDAOimpl" %>
</head>
<body>
	<%
		String stopIDstr = (String) request.getParameter("stopID");
		int stopID = Integer.parseInt(stopIDstr);
		
		String transitLine = (String) request.getParameter("transitLine");
		
		EmployeeDAO ed = new EmployeeDAOimpl();
		
		ed.deleteStop(stopID, transitLine);
		response.sendRedirect("/TrainApp/Esession");
	%>
</body>
</html>