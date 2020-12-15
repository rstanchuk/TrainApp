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
	<script>
		function yesnoCheck(that) {
		    if (that.value == "transit") {
		        document.getElementById("ifTransit").style.display = "block";
		        document.getElementById("ifName").style.display = "none";
		    } else {
		    	document.getElementById("ifName").style.display = "block";
		        document.getElementById("ifTransit").style.display = "none";
		    }
		}
	</script>
	<%@ page import="trainapp.admin.dao.AdminDAO" %>
	<%@ page import="trainapp.admin.dao.AdminDAOimpl" %>
	<%@ page import="trainapp.employee.Employee" %>
	<%@ page import="trainapp.trainschedule.Reservation" %>
	<%@ page import="trainapp.trainschedule.TransitLine" %>
	<%@ page import="trainapp.trainschedule.CustomerRevenueReport" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>

	<%
		HttpSession sesh = request.getSession();
		Employee[] employees = (Employee[])sesh.getAttribute("employees");
		
		CustomerRevenueReport bestCustomer = (CustomerRevenueReport)sesh.getAttribute("bestCustomer");
		TransitLine[] bestTransitLines = (TransitLine[])sesh.getAttribute("bestTransitLines");
		
		AdminDAO ad = new AdminDAOimpl();
		Reservation[] reservations = ad.getReservations();
	%>
	
	<div class="row">
	
	

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
			  			<a href="/TrainApp/AdminViews/sendEmployee.jsp?index=<%= i %>" class="btn btn-link" style="text-align: left">
				  			Username: <%= employees[i].getUserName() %>
				  			<br/>
				  			Name: <%= employees[i].getFirstName() + " " + employees[i].getLastName() %>
			  			</a>
			  			<a href="/TrainApp/AdminViews/deleteEmployee.jsp?index=<%= i %>" type="button" class="btn btn-danger">Delete</a>
			  		</li>
			  		<%} %>
			  </ul>
			</div>
			
			<div class="card" style="margin-top: 10px">
			  <form action="SalesReport" method="post">
			  <div class="card-header">Sales Report</div>
				  <div class="mb-3">
				      <label class="form-label">Year-Month</label>
				      <input type="text" class="form-control" placeholder="(yyyy-mm)" name="date">
				 </div>
				 <button type="submit" class="btn btn-primary" style="margin-bottom: 20px">Search</button>
			  </form>
			</div>
			
			<div class="card" style="margin-top: 10px">
			  <form action="ListReservations" method="post">
			  <div class="card-header">List Reservations</div>
				  <div class="mb-3">
				      <label class="form-label">Select By</label>
				      <select class="form-select" onchange="yesnoCheck(this);" name="type">
						  <option value="transit">Transit Line</option>
					      <option value="name">Name</option>
					  </select>
				 </div>
				 <div class="mb-3" id="ifTransit">
					 <label class="form-label">Transit Line</label>
					 <!-- <input type="text" class="form-control" placeholder="Transit Line" name="transitLine"> -->
					 <select class="form-select" name="transitLine">
					 	<%
					 	String transitLine = null;
					 	for(int i = 0; i < reservations.length; i++) {
					 		if(!(reservations[i].getTransitLine()).equals(transitLine)) {
					 	%>
						  <option value="<%= reservations[i].getTransitLine() %>"><%= reservations[i].getTransitLine() %></option>
						 <%
						 	transitLine = reservations[i].getTransitLine();
					 		}} %>
					  </select>
				 </div>
				 <div class="mb-3" id="ifName" style="display: none;">
					 <label class="form-label">First Name</label>
					 <input type="text" class="form-control" placeholder="First Name" name="firstName">
					 <br/>
					 <label class="form-label">Last Name</label>
					 <input type="text" class="form-control" placeholder="Last Name" name="lastName">
				 </div>
				 <button type="submit" class="btn btn-primary" style="margin-bottom: 20px">Search</button>
			  </form>
			</div>
			
			<div class="card" style="margin-top: 10px">
			  <form action="RevenueListing" method="post">
			  <div class="card-header">Lisiting of Revenue</div>
				  <div class="mb-3">
				      <label class="form-label">By</label>
				      <select class="form-select" name="type">
						  <option value="transit">Transit Line</option>
					      <option value="name">Name</option>
					  </select>
				 </div>
				 <button type="submit" class="btn btn-primary" style="margin-bottom: 20px">Search</button>
			  </form>
			</div>
			
		</div>
		
		<div class="col-sm-6">
			<div class="card">
			  <div class="card-header">Best Customer</div>
			  <div class="card-body">
			    <h5 class="card-title"><%= bestCustomer.getFirstName() + " " + bestCustomer.getLastName()%></h5>
			    <p class="card-text">UserName: <%= bestCustomer.getUserName()%></p>
			    <p class="card-text">Revenue: $<%= bestCustomer.getRevenue()%></p>
			  </div>
			</div>
			
			<div class="card" style="margin-top: 10px">
			  <div class="card-header">Best 5 Most Active Transit Lines</div>
			  <div class="card-body">
			  	<%for(int i = 0; i < bestTransitLines.length; i++) {%>
				    <h5 class="card-title">Transit Line: <%= bestTransitLines[i].getTransitLine()%></h5>
				    <p class="card-text">Revenue: $<%= bestTransitLines[i].getRevenue()%></p>
				    <p class="card-text">Number of Reservations: <%= bestTransitLines[i].getNumOfReservations()%></p>
				    <hr>
			    <%} %>
			  </div>
			</div>
			
		</div>
		
	</div>
	
</body>
</html>