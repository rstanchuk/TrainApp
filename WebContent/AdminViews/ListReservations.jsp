<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TrainApp</title>
	<style type="text/css">
	    <%@include file="../bootstrap/bootstrap.min.css" %>
	</style>
	<%@ page import="trainapp.trainschedule.Reservation" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%
		Reservation[] reservations = (Reservation[])session.getAttribute("reservations");
	%>
		<div class="col">
		<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">Reservation ID</th>
					<th scope="col">UserName</th>
					<th scope="col">TransitLine</th>
					<th scope="col">Origin</th>
					<th scope="col">Destination</th>
					<th scope="col">Departure Time</th>
					<th scope="col">Arrival Time</th>
					<th scope="col">Round Trip</th>
					<th scope="col">Price</th>
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0; i < reservations.length; i++) {%>
				<tr>
					<td><%= reservations[i].getReservationID() %></td>
					<td><%= reservations[i].getUserName() %></td>
					<td><%= reservations[i].getTransitLine() %></td>
					<td><%= reservations[i].getOrigin() %></td>
					<td><%= reservations[i].getDestination() %></td>
					<td><%= reservations[i].getDepartureTime() %></td>
					<td><%= reservations[i].getArrivalTime() %></td>
					<td><%= reservations[i].isRoundTrip() %></td>
					<td>$<%= reservations[i].getPrice() %></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		</div>
</body>
</html>