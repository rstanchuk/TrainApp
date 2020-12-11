<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TrainApp</title>
<%@ page import="trainapp.trainschedule.TrainSchedule" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		TrainSchedule[] schedules = (TrainSchedule[])sesh.getAttribute("schedules");
		
		sesh.setAttribute("schedule", schedules[index]);
		response.sendRedirect("/TrainApp/ETrainScheduleEdit");
	%>
</body>
</html>