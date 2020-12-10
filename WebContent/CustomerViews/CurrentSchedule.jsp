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
	<%@ page import="trainapp.trainschedule.Stop" %>
	<%@ page import="trainapp.trainschedule.Station" %>
	<%@ page import="trainapp.customer.dao.CustomerDAO" %>
	<%@ page import="trainapp.customer.dao.CustomerDAOimpl" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%		
		HttpSession sesh = request.getSession();
		TrainSchedule schedule = (TrainSchedule)sesh.getAttribute("currentschedule");
		
		Stop[] stops = (Stop[])sesh.getAttribute("stops");
		
		CustomerDAO cd = new CustomerDAOimpl();
		/* cd.getStation(stationID); */
	%>
	
	<div class="card">
	  <div class="card-header">
	    Transit Line: <%= schedule.getTransitLine() %> 
			  				
	  </div>
	  <div class="card-body">
	    <h5 class="card-title">Info</h5>
	    <p class="card-text">
	    	Departure Time: <%= schedule.getDepTime() %>
			<br /> 
			Arrival Time: <%= schedule.getArrivalTime() %>
			<br /> 
			Origin Station: <%= cd.getNameOfStation(schedule.getOriginStation()) %>
			<br /> 
			Destination Station: <%= cd.getNameOfStation(schedule.getDesStation()) %>
			<br /> 
			Fare: $<%= schedule.getFare() %>
			<br /> 
			Train ID: <%= schedule.getTid() %>
	    </p>
	  </div>
	</div>
	<div class="card">
	  <div class="card-header">
	    Stops:			
	  </div>
	</div>
	
	<%for(int i = 0; i < stops.length; i++) {%>
		<div class="card">
		  <div class="card-body">
		  	 <p class="card-text">
		  	 	<%
		  	 		Station current = cd.getStation(stops[i].getStationID());
		  	 	%>
		  	 	Station ID: <%= stops[i].getStationID() %>
		  	 	<br />
		  	 	Name: <%= current.getName() %>
		  	 	<br />
		  	 	City: <%= current.getCity() %>
		  	 	<br />
		  	 	State: <%= current.getState() %>
		  	 	<br />
		  	 	Arrival: <%= stops[i].getArrivalTime() %>
		  	 	<br />
		  	 	Departure: <%= stops[i].getDepartTime() %>
		  	 	<br />
		  	 </p>
		  </div>
		</div>	
	<%}%>
</body>
</html>