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
	<%@ page import="trainapp.forum.SupportTicket" %>
	<%@ page import="javax.servlet.http.HttpSession" %>
</head>
<body>
		<%
			HttpSession sesh = request.getSession();
			SupportTicket[] tickets = (SupportTicket[])sesh.getAttribute("tickets");
		%>
		
		<div class="row">
			<div class="col-sm-6">
				<div class="card">
				  <div class="card-header">
				  	<div class="row">
				  		<div class="col"><h3>Welcome</h3><h5>${FirstName} ${LastName}</h5></div>
				  		<div class="col"><a href="Logout" class="btn btn-link" style="float: right; margin-top: 15px">Log Out</a></div>
				  	</div>
				    
				  </div>
				  <div class="card-body">
				  	<div class="row">
				  		<div class="col"><h5 class="card-title">Your Username</h5></div>
				    	<div class="col"><p class="card-text">${UserName}</p></div>
				  	</div>
				  	<div class="row">
				  		<div class="col"><h5 class="card-title">Your Email</h5></div>
				    	<div class="col"><p class="card-text">${Email}</p></div>
				  	</div>
				  	<div class="row">
				  		<div class="col"><h5 class="card-title">Your Discount</h5></div>
				    	<div class="col"><p class="card-text">${Discount}%</p></div>
				  	</div>
				  </div>
				</div>
			</div>
			
			<div class="col-sm-6">
				<div class="card" style="width: 30rem;">
				  <div class="card-header">Your Support Tickets<a href="CreateSupportTicket" class="btn btn-link">Create</a></div>
				  <ul class="list-group list-group-flush">
				  	<% 
				  	for(int i = 0; i < tickets.length; i++) {%>
				  		<li class="list-group-item">
				  			<a href="/TrainApp/CustomerViews/sendTicket.jsp?index=<%= i %>" class="btn btn-link"><%= tickets[i].getTitle() %></a>
				  			<a href="/TrainApp/CustomerViews/deleteTicket.jsp?index=<%= i %>" type="button" class="btn btn-danger">Delete</a>
				  		</li>
				  	<%}%>
				  </ul>
				</div>
				<form action="SearchSupportTicket" method="post">
					<div class="card" style="width: 30rem;">
					  <div class="card-header">Support Ticket Search</div>
					  <div class="card-body">
					  	<div class="row form-group">
					  		<input type="text" class="form-control" placeholder="Keyword" name="keyword">
					  	</div>
					  	<div class="row form-group">
					  		<input type="submit" class="btn btn-primary" name="submit" value="Search">
					  	</div>
					  </div>
					</div>
				</form>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col">
				<div class="card" style="width: 30rem;">
				  <div class="card-header">Search Train Schedules</div>
					  <div class="card-body">
					  	<form action="SearchTrainSchedule" method="post">
							<div class="mb-3">
						      <label class="form-label">Search by</label>
						      <select class="form-select" name="searchby">
						      	<option value="all">All</option>
						        <option value="origin">Origin</option>
						        <option value="destination">Destination</option>
						        <option value="date">Date of Travel (yyyy-mm-dd)</option>
						      </select>
						    </div>
						    <div class="mb-3">
						      <input type="text" class="form-control" placeholder="Search" name="keyword">
						    </div>
						    <div class="mb-3">
						      <label class="form-label">Sort by</label>
						      <select class="form-select" name="sortby">
						        <option value="arrival">Arrival Time</option>
						        <option value="departure">Departure Time</option>
						        <option value="fare">Fare</option>
						      </select>
						    </div>
						    <button type="submit" class="btn btn-primary">Search</button>
						</form>
					  </div>
					</div>
			</div>
			
			<div class="col">
				<div class="card" style="width: 30rem;">
				  <div class="card-header">Your Reservations</div>
					  <div class="card-body">
					  	This is where your reservations will go
					  </div>
				  </div>
			</div>
			
		</div>
	
		
	

</body>
</html>