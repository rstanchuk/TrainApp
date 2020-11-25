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
	<%@ page import="trainapp.forum.Message" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%
		HttpSession sesh = request.getSession();
		Message[] messages = (Message[])sesh.getAttribute("messages");
	%>
	
	<div class="card">
	  <div class="card-header">
	    Ticket Number: ${id}
	  </div>
	  <div class="card-body">
	    <h5 class="card-title">${username} - ${title}</h5>
	    <p class="card-text">${body}</p>
	  </div>
	</div>
	
	<%for(int i = 0; i < messages.length; i++) {%>
		<div class="card">
		  <div class="card-body">
		  	<% if(messages[i].getUserNameCustomer() != null) { %>
		  		<h5 class="card-title">Customer - <%= messages[i].getUserNameCustomer() %></h5>
		  	<%}%>
		  	<% if(messages[i].getUserNameEmployee() != null) { %>
		  		<h5 class="card-title">Employee - <%= messages[i].getUserNameEmployee() %></h5>
		  	<%}%>
		    <p class="card-text"><%= messages[i].getBody() %></p>
		  </div>
		</div>	
	<%}%>
	
	<form class="col-lg-5" style="margin-top: 10px" action="EmySupportTicket" method="post">
	
		<div class="card">
		  <h5 class="card-header">Message</h5>
		  <p style="color:red">${message}</p>
		  <div class="card-body">
		    <textarea class="form-control" placeholder="Body" name="body"></textarea>
		    <small>Only 500 characters</small>
		    <div class="col"><input type="submit" class="btn btn-primary" name="submit"></div>
		  </div>
		</div>
	
	</form>
	

</body>
</html>