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

	<form class="col-lg-5 mx-auto" style="margin-top: 50px" action="CreateSupportTicket" method="post">
		<div class="row">
			<div class="col"><h3>Support Ticket</h3></div>
			<div class="col"><p style="color:red; margin-top: 5px">${message}</p></div>
		</div>
		<div class="form-group">
		    <label>Username ${username}</label>
	  	</div>
	  	<div class="form-group">
		    <label>Title</label>
		    <input type="text" class="form-control" placeholder="Title" name="title">
	  	</div>
	  	<div class="form-group">
		    <label>Body</label>
		    <textarea class="form-control" placeholder="Body" name="body"></textarea>
		    <small>Only 500 characters</small>
	  	</div>
	  	<div class="row form-group">
	  		<div class="col"><input type="submit" class="btn btn-primary" name="submit"></div>
	  	</div>
	</form>

</body>
</html>