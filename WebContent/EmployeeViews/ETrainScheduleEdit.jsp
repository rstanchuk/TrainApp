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
	<%@ page import="javax.servlet.http.HttpSession" %>
	<%@ page import="trainapp.trainschedule.TrainSchedule" %>
	<%@ page import="trainapp.trainschedule.Station" %>
	<%@ page import="trainapp.trainschedule.Stop" %>
	<%@ page import="trainapp.customer.dao.*" %>
</head>
<body>
	<%
		TrainSchedule schedule = (TrainSchedule)session.getAttribute("schedule");
		CustomerDAO cd = new CustomerDAOimpl();
		Station origin = cd.getStation(schedule.getOriginStation());
		Station dest = cd.getStation(schedule.getDesStation());
		Stop[] stops = cd.getStops(schedule.getTransitLine());
	%>
	
	<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">Transit Line</th>
					<th scope="col">Departure Time</th>
					<th scope="col">Arrival Time</th>
					<th scope="col">Origin Station</th>
					<th scope="col">Origin Station ID</th>
					<th scope="col">Origin City</th>
					<th scope="col">Origin State</th>
					<th scope="col">Destination Station</th>
					<th scope="col">Destination Station ID</th>
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
					<td> <%= schedule.getOriginStation() %></td>
					<td><%= origin.getCity() %></td>
					<td><%= origin.getState() %></td>
					<td><%= dest.getName() %></td>
					<td> <%= schedule.getDesStation() %></td>
					<td><%= dest.getCity() %></td>
					<td><%= dest.getState() %></td>
					
					<td>$<%= schedule.getFare() %></td>
					<td><%= schedule.getTid() %></td>
			</tbody>
		</table>
		
		<div class="card">
  			<div class="card-body">
				<p style="color: red">${error}</p>
				<form action="ETrainScheduleEdit" method="post">
				  <div class="form-group">
				    <label for="formGroupExampleInput2">Transit Line</label>
				    <input type="text" class="form-control" name="transitLine" value="<%= schedule.getTransitLine() %>" disabled>
				    <% session.setAttribute("nameOfTransitLine", schedule.getTransitLine());%>
				  </div>
				  <div class="form-group">
				    <label for="formGroupExampleInput2">Departure Time</label>
				    <input type="text" class="form-control" name="departureTime" value="<%= schedule.getDepTime() %>">
				  </div>
				  <div class="form-group">
				    <label for="formGroupExampleInput2">ArrivalTime</label>
				    <input type="text" class="form-control" name="arrivalTime" value="<%= schedule.getArrivalTime() %>">
				  </div>
				  <div class="form-group">
				    <label for="formGroupExampleInput2">Origin Station ID</label>
				    <input type="text" class="form-control" name="originStationID" value="<%= schedule.getOriginStation() %>">
				  </div>
				  <div class="form-group">
				    <label for="formGroupExampleInput2">Destination Station ID</label>
				    <input type="text" class="form-control" name="destinationStationID" value="<%= schedule.getDesStation() %>">
				  </div>
				  <div class="form-group">
				    <label for="formGroupExampleInput2">Fare</label>
				    <input type="text" class="form-control" name="fare" value="<%= schedule.getFare() %>">
				  </div>
				  <div class="form-group">
				    <label for="formGroupExampleInput2">Train ID</label>
				    <input type="text" class="form-control" name="trainID" value="<%= schedule.getTid() %>">
				  </div>
				  <input type="submit" class="btn btn-primary">
				</form>
			</div>
		</div>
		
		<div class="form-group" style="margin-top: 10px">
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
					<th scope="col">Submit</th>
			</thead>
			<tbody>
				<% session.setAttribute("transitLine", stops[0].getTransitLine()); %>
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
					<td><a href="/TrainApp/EmployeeViews/deleteStop.jsp?stopID=<%= current.getStationID() %>&transitLine=<%= stops[0].getTransitLine() %>" type="button" class="btn btn-danger">Delete</a></td>
				</tr>
				<tr>
					<form action="EditStop" method="post">
						<td><input type="text" class="form-control" name="stopID" value="<%= current.getStationID() %>"></td>
						<td><%= current.getName() %></td>
						<td><%= current.getCity() %></td>
						<td><%= current.getState() %></td>
						<td><input type="text" class="form-control" name="arrivalTime" value="<%= stops[i].getArrivalTime() %>"></td>
						<td><input type="text" class="form-control" name="departureTime" value="<%= stops[i].getDepartTime() %>"></td>
						<td><input type="submit" class="btn btn-primary"></td>
					</form>
				</tr>
				<%}%>
			</tbody>
		</table>
</body>
</html>