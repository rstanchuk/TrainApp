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
	<%@ page import="trainapp.trainschedule.TrainSchedule" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
	<%@ page import="trainapp.customer.dao.CustomerDAO" %>
	<%@ page import="trainapp.customer.dao.CustomerDAOimpl" %>
</head>
<body>
		<%
			HttpSession sesh = request.getSession();
			TrainSchedule[] searchedSchedules = (TrainSchedule[])sesh.getAttribute("searchSchedule");
			
			CustomerDAO cd = new CustomerDAOimpl();
		%>
		
		<div class="col">
			<div class="card" style="width: 30rem;">
			  <div class="card-header">Search Results</div>
			  <ul class="list-group list-group-flush">
			  	<% 
			  	for(int i = 0; i < searchedSchedules.length; i++) {%>
			  		<li class="list-group-item">
			  			<a href="/TrainApp/CustomerViews/sendSearchSchedule.jsp?index=<%= i %>" style="text-align: left" class="btn btn-link">
			  				Transit Line: <%= searchedSchedules[i].getTransitLine() %>
			  				<br /> 
			  				Departure Time: <%= searchedSchedules[i].getDepTime() %>
			  				<br /> 
			  				Arrival Time: <%= searchedSchedules[i].getArrivalTime() %>
			  				<br /> 
			  				Origin Station: <%= cd.getNameOfStation(searchedSchedules[i].getOriginStation()) %>
			  				<br /> 
			  				Destination Station: <%= cd.getNameOfStation(searchedSchedules[i].getDesStation()) %>
			  				<br /> 
			  				Fare: $<%= searchedSchedules[i].getFare() %>
			  				<br /> 
			  				Train ID: <%= searchedSchedules[i].getTid() %>
			  			</a>
			  		</li>
			  	<%}%>
			  </ul>
			</div>
		</div>
</body>
</html>