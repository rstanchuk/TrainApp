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
			  		<div class="col"><h5 class="card-title">Your SSN</h5></div>
			    	<div class="col"><p class="card-text">${SSN}</p></div>
			  	</div>
			  </div>
			</div>
		</div>
	</div>
	
</body>
</html>