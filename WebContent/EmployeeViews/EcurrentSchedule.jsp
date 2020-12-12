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
	<%@ page import="trainapp.customer.Customer" %>
	<%@ page import="trainapp.customer.dao.CustomerDAO" %>
	<%@ page import="trainapp.customer.dao.CustomerDAOimpl" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%		
		HttpSession sesh = request.getSession();
		TrainSchedule schedule = (TrainSchedule)sesh.getAttribute("currentSchedule");
		
		Stop[] stops = (Stop[])sesh.getAttribute("stops");
		
		CustomerDAO cd = new CustomerDAOimpl();
		
		Customer c = (Customer)sesh.getAttribute("Customer");
		
		Station origin = cd.getStation(schedule.getOriginStation());
		Station dest = cd.getStation(schedule.getDesStation());
	%>
	
		<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">Transit Line</th>
					<th scope="col">Departure Time</th>
					<th scope="col">Arrival Time</th>
					<th scope="col">Origin Station</th>
					<th scope="col">Origin City</th>
					<th scope="col">Origin State</th>
					<th scope="col">Destination Station</th>
					<th scope="col">Destination City</th>
					<th scope="col">Destination State</th>
					<th scope="col">Fare</th>
					<th scope="col">Train ID</th>
				</tr>
			</thead>
			<tbody>
					<td><%= schedule.getTransitLine() %></td>
					<td><%= schedule.getDepTime() %></td>
					<td> <%= schedule.getArrivalTime() %></td>
					<td><%= origin.getName() %></td>
					<td><%= origin.getCity() %></td>
					<td><%= origin.getState() %></td>
					<td><%= dest.getName() %></td>
					<td><%= dest.getCity() %></td>
					<td><%= dest.getState() %></td>
					<td>$<%= schedule.getFare() %></td>
					<td><%= schedule.getTid() %></td>
			</tbody>
		</table>
		
		<div class="form-group">
			<div class="col"><h3>Stops Listings for <%= schedule.getTransitLine() %></h3></div>
		</div>
	  	<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">Station ID</th>
					<th scope="col">Name</th>
					<th scope="col">City</th>
					<th scope="col">State</th>
					<th scope="col">Arrival Time</th>
					<th scope="col">Departure Time</th>
			</thead>
			<tbody>
				<% 
				for(int i = 0; i < stops.length; i++)
				{%>
				<%
		  	 		Station current = cd.getStation(stops[i].getStationID());
		  	 	%>
				<tr>
					<td><%= current.getStationID() %></td>
					<td><%= current.getName() %></td>
					<td><%= current.getCity() %></td>
					<td><%= current.getState() %></td>
					<td><%= stops[i].getArrivalTime() %></td>
					<td><%= stops[i].getDepartTime() %></td>
				</tr>
				<%}%>
			</tbody>
		</table>
</body>
</html>