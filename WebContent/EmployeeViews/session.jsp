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
	<%@ page import="trainapp.trainschedule.Station" %>
</head>
<body>

	<%
		HttpSession sesh = request.getSession();
		SupportTicket[] tickets = (SupportTicket[])sesh.getAttribute("tickets");
		TrainSchedule[] schedules = (TrainSchedule[])sesh.getAttribute("schedules");
		Station[] stations = (Station[])sesh.getAttribute("stations");
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
			
			<div class="card" style="margin-top: 10px">
			  <div class="card-header">Alter Train Schedules</div>
			  <ul class="list-group list-group-flush">
			  	<% 
			  	for(int i = 0; i < schedules.length; i++) {%>
			  		<li class="list-group-item">
			  			<a href="/TrainApp/EmployeeViews/sendScheduleToEdit.jsp?index=<%= i %>" class="btn btn-link"><%= schedules[i].getTransitLine() %></a>
			  			<a href="/TrainApp/EmployeeViews/deleteSchedule.jsp?index=<%= i %>" type="button" class="btn btn-danger">Delete</a>
			  		</li>
			  	<%}%>
			  </ul>
			</div>
			
			<div class="card" style="margin-top: 10px">
				<div class="card-header">Search Schedules by Train Station</div>
	 				<div class="card-body">
				<form action="EsearchTrainSchedules" method="post">
				    <div class="mb-3">
				      <label class="form-label">Select Station</label>
				      <select class="form-select" name="stationID">
				      	<% for(int i = 0; i < stations.length; i++) { %>
				        	<option value="<%= stations[i].getStationID()%>"><%= stations[i].getName() + "-" + stations[i].getCity() + "-" + stations[i].getState() %></option>
				        <%} %>
				      </select>
				    </div>
				    <div class="mb-3">
				      <label class="form-label">It is </label>
				      <select class="form-select" name="location">
				        <option value="origin">Origin</option>
				        <option value="destination">Destination</option>
				        <option value="all">All</option>
				      </select>
				    </div>
				    <button type="submit" class="btn btn-primary">Submit</button>
				</form>
				</div>
			</div>
			
			
			<div class="card" style="margin-top: 10px">
				<div class="card-header">Customers on given transit line</div>
	 				<div class="card-body">
				<form action="EsearchCustomers" method="post">
				    <div class="mb-3">
				      <label class="form-label">Select Transit Line</label>
				      <select class="form-select" name="transitLine">
				      	<%
				      	String transitLine = null;
				      	for(int i = 0; i < schedules.length; i++) { 
				      		if(!(schedules[i].getTransitLine()).equals(transitLine)) {
				      	%>
				        	<option value="<%= schedules[i].getTransitLine()%>"><%= schedules[i].getTransitLine() %></option>
				        <%
				        transitLine = schedules[i].getTransitLine();
				      		}} %>
				      </select>
				    </div>
				    <div class="mb-3">
				      <label>Date (yyyy-mm-dd):</label>
		    		  <input type="text" class="form-control" placeholder="(yyyy-mm-dd)" name="date">
				    </div>
				    <button type="submit" class="btn btn-primary">Submit</button>
				</form>
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
	
	
</body>
</html>