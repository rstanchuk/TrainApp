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
	
	<div class="card">
	  <div class="card-header">
	    Ticket Number: ${id}
	  </div>
	  <div class="card-body">
	    <h5 class="card-title">${username} - ${title}</h5>
	    <p class="card-text">${body}</p>
	  </div>
	</div>
	

</body>
</html>