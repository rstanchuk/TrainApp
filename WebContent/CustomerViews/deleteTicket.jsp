<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TrainApp</title>
	<%@ page import="trainapp.forum.SupportTicket" %>
	<%@ page import="trainapp.customer.dao.CustomerDAO" %>
	<%@ page import="trainapp.customer.dao.CustomerDAOimpl" %>
</head>
<body>
	<%
		String indexStr = (String) request.getParameter("index");
		int index = Integer.parseInt(indexStr);
		
		HttpSession sesh = request.getSession();
		SupportTicket[] tickets = (SupportTicket[])sesh.getAttribute("tickets");
		
		CustomerDAO cd = new CustomerDAOimpl();
		cd.deleteSupportTicket(tickets[index]);
		response.sendRedirect("/TrainApp/Session");
	%>
</body>
</html>