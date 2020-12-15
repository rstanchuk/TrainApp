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
	<%@ page import="trainapp.employee.Employee" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>

	<%
		HttpSession sesh = request.getSession();
		Employee[] employees = (Employee[])sesh.getAttribute("employees");
	%>

	<div class="col-sm-6">
		<div class="card">
		  <div class="card-header">
		  	<div class="row">
		  		<div class="col"><h3>Admin Panel</h3></div>
		  		<div class="col"><a href="Elogout" class="btn btn-link" style="float: right; margin-top: 15px">Log Out</a></div>
		  	</div>
		  </div>
		</div>
		
		<div class="card" style="margin-top: 10px">
		  <div class="card-header">Customer Rep Panel<a href="AddEmployee" class="btn btn-link">Add</a></div>
		  <ul class="list-group list-group-flush">
		  		<%for(int i = 0; i < employees.length; i++) {%>
		  		<li class="list-group-item">
		  			<a href="/TrainApp/AdminViews/sendEmployee.jsp?index=<%= i %>" class="btn btn-link"><%= employees[i].getUserName() %></a>
		  			<a href="/TrainApp/AdminViews/deleteEmployee.jsp?index=<%= i %>" type="button" class="btn btn-danger">Delete</a>
		  		</li>
		  		<%} %>
		  </ul>
		</div>
		
		
	</div>
	
</body>
</html>