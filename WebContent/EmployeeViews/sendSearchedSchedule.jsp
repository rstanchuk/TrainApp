<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>TrainApp</title>
	<%@ page import="trainapp.trainschedule.TrainSchedule" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		TrainSchedule[] schedules = (TrainSchedule[])sesh.getAttribute("searchedSchedules");
		
		sesh.setAttribute("currentSchedule", schedules[index]);
		response.sendRedirect("/TrainApp/EcurrentSchedule");
	%>
</body>
</html>