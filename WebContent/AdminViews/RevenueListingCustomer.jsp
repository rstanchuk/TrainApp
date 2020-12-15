<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
	    <%@include file="../bootstrap/bootstrap.min.css" %>
	</style>
	<%@ page import="trainapp.trainschedule.CustomerRevenueReport" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%
		CustomerRevenueReport[] reports = (CustomerRevenueReport[])session.getAttribute("reports");
	%>
	<div class="col">
		<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">UserName</th>
					<th scope="col">Revenue</th>
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0; i < reports.length; i++) {%>
				<tr>
					<td><%= reports[i].getFirstName() %></td>
					<td><%= reports[i].getLastName() %></td>
					<td><%= reports[i].getUserName() %></td>
					<td>$<%= reports[i].getRevenue() %></td>
				</tr>
				<%} %>
				<tr>
					<td>Total</td>
					<td></td>
					<td></td>
					<td>$${totalRevenue }</td>
				</tr>
			</tbody>
		</table>
		</div>
</body>
</html>