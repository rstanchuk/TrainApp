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
	<%@ page import="trainapp.trainschedule.TrainSchedule" %>
</head>
<body>

	<%
		HttpSession sesh = request.getSession();
		SupportTicket[] tickets = (SupportTicket[])sesh.getAttribute("tickets");
		TrainSchedule[] schedules = (TrainSchedule[])sesh.getAttribute("schedules");
	%>

	<div class="row">
		<div class="col-sm-6">
			<div class="card">
			  <div class="card-header">
			  	<div class="row">
			  		<div class="col"><h3>Welcome</h3><h5>${FirstName} ${LastName}</h5></div>
			  		<div class="col"><a href="Elogout" class="btn btn-link" style="float: right; margin-top: 15px">Log Out</a></div>
			  	</div>
			    
			  </div>
			  
			  <div class="card-body">
			  	<div class="row">
			  		<div class="col"><h5 class="card-title">Your Username</h5></div>
			    	<div class="col"><p class="card-text">${UserName}</p></div>
			  	</div>
			  	<div class="row">
			  		<div class="col"><h5 class="card-title">Your SSN</h5></div>
			    	<div class="col"><p class="card-text">${SSN}</p></div>
			  	</div>
			  </div>
			</div>
		</div>
		
		<div class="col-sm-6">
			<div class="card" style="width: 30rem;">
			  <div class="card-header">Support Tickets</div>
			  <ul class="list-group list-group-flush">
			  	<% 
			  	for(int i = 0; i < tickets.length; i++) {%>
			  		<li class="list-group-item">
			  			<a href="/TrainApp/EmployeeViews/sendTicket.jsp?index=<%= i %>" class="btn btn-link"><%= tickets[i].getTitle() %></a>
			  		</li>
			  	<%}%>
			  </ul>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-6">
			<div class="card" style="width: 30rem;">
			  <div class="card-header">Alter Train Schedules</div>
			  <ul class="list-group list-group-flush">
			  	<% 
			  	for(int i = 0; i < schedules.length; i++) {%>
			  		<li class="list-group-item">
			  			<a href="/TrainApp/EmployeeViews/sendScheduleToEdit.jsp?index=<%= i %>" class="btn btn-link"><%= schedules[i].getTransitLine() %></a>
			  		</li>
			  	<%}%>
			  </ul>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-6">
			<form action="123" method="post">
			    <div class="mb-3">
			      <label class="form-label">Select Station</label>
			      <select class="form-select" name="origin">
			        <option value="">Hello1</option>
			        <option value="">Hello2</option>
			      </select>
			    </div>
			    <div class="mb-3">
			      <label class="form-label">It is </label>
			      <select class="form-select" name="destination">
			        <option value="">Origin</option>
			        <option value="">Destination</option>
			      </select>
			    </div>
			    <button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>
	
</body>
</html>