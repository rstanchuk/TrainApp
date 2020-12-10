<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TrainApp</title>
	<%@ page import="trainapp.trainschedule.Reservation" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		Reservation[] reservations = (Reservation[])sesh.getAttribute("reservations");
		
		sesh.setAttribute("reservation", reservations[index]);
		response.sendRedirect("/TrainApp/MyReservation");
	%>
</body>
</html>