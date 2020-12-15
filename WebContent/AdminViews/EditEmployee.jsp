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
		Employee empl = (Employee) session.getAttribute("employee");
	%>
	
	<form class="col-lg-5 mx-auto" style="margin-top: 50px" action="EditEmployee" method="post">
		<div class="row">
			<div class="col"><h3>Edit Employee Information</h3></div>
			<div class="col"><p style="color:red; margin-top: 5px">${error}</p></div>
		</div>
		<div class="form-group">
		    <label>SSN</label>
		    <input type="text" class="form-control" placeholder="SSN" name="ssn" value="<%= empl.getSSN()%>" disabled>
	  	</div>
	  	<div class="form-group">
		    <label>UserName</label>
		    <input type="text" class="form-control" placeholder="UserName" name="userName" value="<%= empl.getUserName()%>" disabled>
	  	</div>
	  	<div class="form-group">
		    <label>Password</label>
		    <input type="password" class="form-control" placeholder="Password" name="password" value="<%= empl.getPassword()%>" disabled>
	  	</div>
	  	<div class="form-group">
		    <label>First Name</label>
		    <input type="text" class="form-control" placeholder="FirstName" name="firstName" value="<%= empl.getFirstName()%>">
	  	</div>
	  	<div class="form-group">
		    <label>Last Name</label>
		    <input type="text" class="form-control" placeholder="LastName" name="lastName" value="<%= empl.getLastName()%>">
	  	</div>

	  	<div class="row form-group">
	  		<div class="col"><input type="submit" class="btn btn-primary" name="submit"></div>
	  	</div>
	</form>
	
</body>
</html>