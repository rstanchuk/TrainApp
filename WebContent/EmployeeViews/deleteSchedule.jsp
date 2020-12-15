<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>TrainApp</title>
	<%@ page import="trainapp.trainschedule.TrainSchedule" %>
	<%@ page import="trainapp.employee.dao.EmployeeDAO" %>
	<%@ page import="trainapp.employee.dao.EmployeeDAOimpl" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		TrainSchedule[] schedules = (TrainSchedule[])sesh.getAttribute("schedules");
		
		EmployeeDAO ed = new EmployeeDAOimpl();
		ed.deleteTrainSchedule(schedules[index]);
		response.sendRedirect("/TrainApp/Esession");
	%>
</body>
</html>