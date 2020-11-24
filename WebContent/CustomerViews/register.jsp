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
	
	
	<form class="col-lg-5 mx-auto" style="margin-top: 50px" action="Register" method="post">
		<div class="row">
			<div class="col"><h3>Registration Page</h3></div>
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
		  <label>Email</label>
		  <input type="email" class="form-control" placeholder="Email" name="email">
		</div>
		<div class="form-group">
		  <label>First Name</label>
		  <input type="text" class="form-control" placeholder="First Name" name="FirstName">
		</div>
		<div class="form-group">
		  <label>Last Name</label>
		  <input type="text" class="form-control" placeholder="Last Name" name="LastName">
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col"><label>Are you a child?</label></div>
				<div class="col">
					<input type="radio" id="child1" name="isChild" value="true">
					<label for="child1">Yes</label>
					<input type="radio" id="child2" name="isChild" value="false" checked>
					<label for="child2">No</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col"><label>Are you a senior?</label></div>
				<div class="col">
					<input type="radio" id="senior1" name="isSenior" value="true">
					<label for="senior">Yes</label>
					<input type="radio" id="senior2" name="isSenior" value="false" checked>
					<label for="senior2">No</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col"><label>Are you disabled?</label></div>
				<div class="col">
					<input type="radio" id="disabled1" name="isDisabled" value="true">
					<label for="disabled1">Yes</label>
					<input type="radio" id="disabled2" name="isDisabled" value="false" checked>
					<label for="disabled2">No</label>
				</div>
			</div>
		</div>
	  
	  
		<input type="submit" name="submit" value="register" class="btn btn-primary" >
	</form>

</body>
</html>