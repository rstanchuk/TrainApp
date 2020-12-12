<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TrainApp</title>
	<style type="text/css">
	    <%@include file="../bootstrap/bootstrap.min.css" %>
	</style>
	<%@ page import="javax.servlet.http.HttpSession" %>
	<%@ page import="trainapp.trainschedule.CustomerReport" %>
</head>
<body>
	<%
		HttpSession sesh = request.getSession();
		CustomerReport[] reports = (CustomerReport[])sesh.getAttribute("reports");
	%>
	
		<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">UserName</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Date</th>
					<th scope="col">Reservation ID</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i = 0; i < reports.length; i++) { %>
					<td><%= reports[i].getUserName() %></td>
					<td><%= reports[i].getFirstName() %></td>
					<td> <%= reports[i].getLastName() %></td>
					<td><%= reports[i].getDepartureTime() %></td>
					<td><%= reports[i].getReservationID() %></td>
				<%} %>
			</tbody>
		</table>
</body>
</html>