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
	<%@ page import="trainapp.trainschedule.Reservation" %>
	<%@ page import="trainapp.trainschedule.Station" %>
	<%@ page import="trainapp.customer.dao.CustomerDAO" %>
	<%@ page import="trainapp.customer.dao.CustomerDAOimpl" %>
</head>
<body>
		<%
			HttpSession sesh = request.getSession();
			SupportTicket[] tickets = (SupportTicket[])sesh.getAttribute("tickets");
			Reservation[] reservations = (Reservation[])sesh.getAttribute("reservations");
			CustomerDAO cd = new CustomerDAOimpl();
			Station[] stations = cd.getStations();
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
					  	<input type="submit" class="btn btn-primary" name="submit" value="Search">
					  </div>
					</div>
				</form>
			</div>
		</div>
		
		
		<div class="row">
			<!-- Search -->
			<div class="col-sm-6">
				<div class="card" >
		  		<div class="card-header">Search Train Schedules</div>
				<form action="SearchTrainSchedule" method="post">
					<div class="form-group">
						<p style="color: red">${error }</p>
						<br/>
						<label>Origin:</label>
						<select class="form-select" name="origin">
							<option value="all">All</option>
							<%for(int i = 0; i < stations.length; i++) {%>
					      	<option value="<%=stations[i].getName() %>"><%=stations[i].getName() + "-" + stations[i].getCity() + "-" + stations[i].getState()%></option>
					      	<%} %>
					    </select>
					    <br/>
						<label>Destination:</label>
						<select class="form-select" name="destination">
							<option value="all">All</option>
							<%for(int i = 0; i < stations.length; i++) {%>
					      	<option value="<%=stations[i].getName() %>"><%=stations[i].getName() + "-" + stations[i].getCity() + "-" + stations[i].getState()%></option>
					      	<%} %>
					    </select>
					    <br/>
						<label>Date (yyyy-mm-dd):</label>
						<input type="text" class="form-control" placeholder="Search" name="date">
						<label>Sort by:</label>
						<select class="custom-select" name="sortby">
							<option value="arrival">Arrival Time</option>
					        <option value="departure">Departure Time</option>
					        <option value="fare">Fare</option>
						</select>
					</div>
					<button type="submit" class="btn btn-primary" style="margin-bottom: 20px">Search</button>
				</form>
				</div>
			</div>
			<!-- Search -->
			
			<div class="col-sm-6">
				<div class="card" style="width: 30rem;">
				  <div class="card-header">Your Past Reservations</div>
				  <ul class="list-group list-group-flush">
				  	<% 
				  	for(int i = 0; i < reservations.length; i++) {%>
				  		<%if(reservations[i].isCurrent() == false) {%>
				  		<li class="list-group-item">
				  			<a href="/TrainApp/CustomerViews/sendReservation.jsp?index=<%= i %>" class="btn btn-link"><%= reservations[i].getOrigin() %> &#x2192; <%= reservations[i].getDestination() %></a>
				  		</li>
				  	<%}}%>
				  </ul>
				</div>
				
				<div class="card" style="width: 30rem;">
				  <div class="card-header">Your Current Reservations</div>
				  <ul class="list-group list-group-flush">
				  	<% 
				  	for(int i = 0; i < reservations.length; i++) {%>
				  		<%if(reservations[i].isCurrent() == true) {%>
				  		<li class="list-group-item">
				  			<a href="/TrainApp/CustomerViews/sendReservation.jsp?index=<%= i %>" class="btn btn-link"><%= reservations[i].getOrigin() %> &#x2192; <%= reservations[i].getDestination() %></a>
				  			<a href="/TrainApp/CustomerViews/cancelReservation.jsp?index=<%= i %>" type="button" class="btn btn-danger">Cancel</a>
				  		</li>
				  	<%}}%>
				  </ul>
				</div>
				
			</div>
		</div>

</body>
</html>