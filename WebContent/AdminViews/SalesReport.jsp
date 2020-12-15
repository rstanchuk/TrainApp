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
	<%@ page import="trainapp.trainschedule.RevenueReport" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
	<%
		RevenueReport[] reports = (RevenueReport[])session.getAttribute("revenueReports");
	%>
	
		<div class="col">
		<table class="table">
		  	<thead class="thead-light">
				<tr>
					<th scope="col">Transit Line</th>
					<th scope="col">Sales</th>
					
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
					<td>$${totalSales}</td>
				</tr>
			</tbody>
		</table>
		</div>
</body>
</html>