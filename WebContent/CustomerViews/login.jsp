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


	<form class="col-lg-5 mx-auto" style="margin-top: 50px" action="Login" method="post">
		<div class="row">
			<div class="col"><h3>Login Page</h3></div>
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
	  	<div class="row form-group">
	  		<div class="col"><input type="submit" class="btn btn-primary" name="submit" value="Login"></div>
			<div class="col"><a href="Register" class="btn btn-link" style="margin-top: 5px; margin-left: 60px">Customer Registration</a></div>
	  	</div>
	</form>
	<div style="margin-top: 20px; text-align: center"><a href="Elogin" class="btn btn-link">Employee Site</a></div>

</body>
</html>