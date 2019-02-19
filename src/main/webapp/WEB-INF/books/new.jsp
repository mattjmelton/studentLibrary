<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Add New Book</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<div class ="row">
			<h1>Add New Book to the Library</h1>
		</div>
		<div class="row">
			<form:form action="/books" method="post" modelAttribute="book">
    			<div class="form-group row">
    			
        			<form:label class="col-sm-4 col-form-label" path="bookname">Title</form:label>
        			<form:errors path="bookname"/>
        			<div class="col-sm-10">
        				<form:input type="text" class="form-control" path="bookname"/>
        			</div>
        		</div>	
    			<div class="form-group row">
        			<form:label class="col-sm-4 col-form-label" path="author">Author</form:label>
        			<form:errors path="author"/>
        			<div class="col-sm-10">
        				<form:input type="text" class="form-control" path="author"/>
    				</div>
    			</div>
    			<div class="form-group row">
        			<form:label class="col-sm-4 col-form-label" path="subject">Subject</form:label>
        			<form:errors path="subject"/>
        			<div class="col-sm-10">
        				<form:input type="text" class="form-control" path="subject"/>
    				</div>
    			</div>
    			<div class="form-group row">
        			<form:label class="col-sm-4 col-form-label" path="publication">Publication</form:label>
        			<form:errors path="publication"/>
        			<div class="col-sm-10">
        				<form:input type="text" class="form-control" path="publication"/>
    				</div>
    			</div>
    			<div class="form-group row">
        			<form:label class="col-sm-4 col-form-label" path="numberOfCopies">Number of Copies</form:label>
        			<form:errors path="numberOfCopies"/>     
        			<div class="col-sm-10">
        				<form:input class="form-control" type="number" path="numberOfCopies"/>
    				</div>
    			</div>
    	   
    			<input class="btn btn-primary" type="submit" value="Submit"/>
			</form:form>    
	
		</div>
	</div>    
	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	
</body>
</html>