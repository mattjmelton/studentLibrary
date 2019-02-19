<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

</head>
<body>
	<div class="container">
	<h1>Welcome to the Library System</h1>
		<div class="row">
			<div class="col">
				<h4>Register</h4>
    
    			<p><form:errors path="user.*"/></p>
    
    			<form:form method="POST" action="/registration" modelAttribute="user">
        			<div class="form-group">
        				<form:label path="name">Name:</form:label>
            			<form:input path="name" class="form-control" type="text" />
        				
        				<form:label path="regno">Student registration no.:</form:label>
            			<form:input path="regno" class="form-control" type="text" />
        				
        				<form:label path="branch">Branch:</form:label>
            			<form:input path="branch" class="form-control" type="text" />
        				
        				<form:label path="section">Section:</form:label>
            			<form:input path="section" class="form-control" type="text" />
            			
            			<form:label path="semester">Semester:</form:label>
            			<form:input path="semester" class="form-control" type="text" />
            			
        				<form:label path="email">Email:</form:label>
            			<form:input class="form-control" type="email" path="email"/>
        		
            			<form:label path="password">Password:</form:label>
            			<form:password class="form-control" path="password"/>
        
            			<form:label path="passwordConfirmation">Password Confirmation:</form:label>
            			 <form:password class="form-control" path="passwordConfirmation"/>
            			 
        			</div>
            
        			<input class="btn btn-primary" type="submit" value="Register"/>
    			</form:form>
			</div>
			<div class="col">
				<h4>Login</h4>
    			<p><c:out value="${error}" /></p>
    			<form method="post" action="/login">
        			<div class="form-group">
        		
            			<label type="email" for="email">Email</label>
            			<input class="form-control" type="text" id="email" name="email"/>
        	
            			<label for="password">Password</label>
            			<input class="form-control" type="password" id="password" name="password"/>
        	
        			</div>
        			<input class="btn btn-primary"  type="submit" value="Login"/>
    			</form> 
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	
</body>
</html>