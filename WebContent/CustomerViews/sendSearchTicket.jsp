<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TrainApp</title>
	<%@ page import="trainapp.forum.SupportTicket" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		SupportTicket[] tickets = (SupportTicket[])sesh.getAttribute("searchedTickets");
		
		sesh.setAttribute("ticket", tickets[index]);
		response.sendRedirect("/TrainApp/MySupportTicket");
	%>
</body>
</html>