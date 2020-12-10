<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TrainApp</title>
	<style type="text/css">
	    <%@include file="../bootstrap/bootstrap.min.css" %>
	</style>
	<%@ page import="trainapp.forum.SupportTicket" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
		<%
			HttpSession sesh = request.getSession();
			SupportTicket[] searchedTickets = (SupportTicket[])sesh.getAttribute("searchedTickets");
		%>
	  	
	  	<div class="col-sm-6">
			<div class="card" style="width: 30rem;">
			  <div class="card-header">Search Results</div>
			  <ul class="list-group list-group-flush">
			  	<% 
			  	for(int i = 0; i < searchedTickets.length; i++) {%>
			  		<li class="list-group-item">
			  			<a href="/TrainApp/CustomerViews/sendSearchTicket.jsp?index=<%= i %>" class="btn btn-link"><%= searchedTickets[i].getTitle() %></a>
			  		</li>
			  	<%}%>
			  </ul>
			</div>
		</div>
</body>
</html>