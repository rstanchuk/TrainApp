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
</head>
<body>

	<form class="col-lg-5 mx-auto" style="margin-top: 50px" action="Eregister" method="post">
		<div class="row">
			<div class="col"><h3>Employee Registration Page</h3></div>
			<div class="col"><p style="color:red; margin-top: 5px">${message}</p></div>
		</div>
		<div class="form-group">
		  <label>Username</label>
		  <input type="text" class="form-control" placeholder="Username" name="username">
		</div>
		<div class="form-group">
		  <label>Password</label>
		  <input type="password" class="form-control" placeholder="Password" name="password1">
		</div>
		<div class="form-group">
		  <label>Re-Type Password</label>
		  <input type="password" class="form-control" placeholder="Re-Type Password" name="password2">
		</div>
		<div class="form-group">
		  <label>SSN</label>
		  <input type="text" class="form-control" placeholder="SSN" name="SSN">
		</div>
		<div class="form-group">
		  <label>First Name</label>
		  <input type="text" class="form-control" placeholder="First Name" name="FirstName">
		</div>
		<div class="form-group">
		  <label>Last Name</label>
		  <input type="text" class="form-control" placeholder="Last Name" name="LastName">
		</div>
	  
		<input type="submit" name="submit" value="register" class="btn btn-primary" >
	</form>

</body>
</html>