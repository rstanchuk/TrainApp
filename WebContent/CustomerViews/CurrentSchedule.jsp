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
		TrainSchedule schedule = (TrainSchedule)sesh.getAttribute("currentschedule");
		
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
		<hr>
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
		
		<div class="card">
		  <div class="card-body">
		    <h5 class="card-title">Make Reservation</h5>
		    		
		    		
		    		<form action="CurrentSchedule" method="post">
					    <div class="mb-3">
					    	<p style="color: red">${error}</p>
					      <label class="form-label">Select Origin Station</label>
					      <select class="form-select" name="origin">
					      	<%for(int i = 0; i < stops.length; i++) {%>
					      	<%
					  	 		Station current = cd.getStation(stops[i].getStationID());
					  	 	%>
					        <option value="<%= stops[i].getStationID() %>"><%= current.getName() + "-" + current.getCity() + "-" + current.getState() %></option>
					        <%} %>
					      </select>
					    </div>
					    <div class="mb-3">
					      <label class="form-label">Select Destination Station</label>
					      <select class="form-select" name="destination">
					        <%for(int i = 0; i < stops.length; i++) {%>
					        <%
					  	 		Station current = cd.getStation(stops[i].getStationID());
					  	 	%>
					        <option value="<%= stops[i].getStationID() %>"><%= current.getName() + "-" + current.getCity() + "-" + current.getState() %></option>
					        <%} %>
					      </select>
					    </div>
					    <div class="mb-3">
					      <div class="form-check">
					        <input class="form-check-input" type="checkbox" value="check" name="round">
					        <label class="form-check-label">Round Trip</label>
					      </div>
					    </div>
					    <button type="submit" class="btn btn-primary">Submit</button>
	
					</form>
		    	
		    		
		  </div>
		</div>
	
	
</body>
</html>