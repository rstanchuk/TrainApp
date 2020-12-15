<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
	    <%@include file="../bootstrap/bootstrap.min.css" %>
	</style>
	<%@ page import="trainapp.trainschedule.RevenueReport" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%
		RevenueReport[] reports = (RevenueReport[])session.getAttribute("reports");
	%>
	<div class="col">
		<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">TransitLine</th>
					<th scope="col">Revenue</th>
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0; i < reports.length; i++) {%>
				<tr>
					<td><%= reports[i].getTransitLine() %></td>
					<td>$<%= reports[i].getRevenue() %></td>
				</tr>
				<%} %>
				<tr>
					<td>Total</td>
					<td>$${totalRevenue }</td>
				</tr>
			</tbody>
		</table>
		</div>
</body>
</html>