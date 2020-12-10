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
	<%@ page import="trainapp.trainschedule.Station" %>
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
				<% 
				for(int i = 0; i < searchedSchedules.length; i++) {
					Station origin = cd.getStation(searchedSchedules[i].getOriginStation());
					Station dest = cd.getStation(searchedSchedules[i].getDesStation());
				%>
				<tr>
					<td>
						<a href="/TrainApp/CustomerViews/sendSearchSchedule.jsp?index=<%= i %>" class="btn btn-link">
							<%= searchedSchedules[i].getTransitLine() %>
						</a>
 					</td>
					<td><%= searchedSchedules[i].getDepTime() %></td>
					<td> <%= searchedSchedules[i].getArrivalTime() %></td>
					<td><%= origin.getName() %></td>
					<td><%= origin.getCity() %></td>
					<td><%= origin.getState() %></td>
					<td><%= dest.getName() %></td>
					<td><%= dest.getCity() %></td>
					<td><%= dest.getState() %></td>
					<td>$<%= searchedSchedules[i].getFare() %></td>
					<td><%= searchedSchedules[i].getTid() %></td>
				</tr>
				<%}%>
			</tbody>
		</table>
		</div>
</body>
</html>