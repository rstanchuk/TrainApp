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
	    Reservation ID: ${id}
	  </div>
	  <div class="card-body">
	    <h5 class="card-title">${origin} &#x2192; ${dest}</h5>
	    <p class="card-text">
	    	UserName: ${username}
	    	<br />
	    	Transit Line: ${line}
	    	<br />
	    	Origin: ${origin}
	    	<br />
	    	Destination: ${dest}
	    	<br />
	    	Departure Time: ${deptime}
	    	<br />
	    	Arrival Time: ${arrtime}
	    	<br />
	    	Is Trip Round: ${round}
	    	<br />
	    	Price: $${price}
	    </p>
	  </div>
	</div>
</body>
</html>