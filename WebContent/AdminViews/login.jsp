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
	<form class="col-lg-5 mx-auto" style="margin-top: 50px" action="AdminLogin" method="post">
		<div class="row">
			<div class="col"><h3>Admin Panel</h3></div>
			<div class="col"><p style="color:red; margin-top: 5px">${message}</p></div>
		</div>
	  	<div class="form-group">
		    <label>Password</label>
		    <input type="password" class="form-control" placeholder="Password" name="password">
	  	</div>
	  	<div class="row form-group">
	  		<div class="col"><input type="submit" class="btn btn-primary" name="submit" value="Login"></div>
	  	</div>
	</form>
</body>
</html>