<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<title>Library Dashboard</title>
</head>
<body>
	<div class="container">
		<nav class="navbar justify-content-end">
            <span class="navbar-text">
            	<a href="/logout">Log out</a>
            </span> 
        </nav>
		<h1>Welcome, <c:out value="${eUser.name}"/> </h1>
		<h4>List of books currently checked out:</h4>
		<div class="row">
    		<table class="table table-striped">
        		<thead>
            		<tr>
                		<th>Title</th>
                		<th>Author</th>
                		<th>Subject</th>  
                		<th>Return Date</th>
            		</tr>
        		</thead>
        		<tbody>
            		<c:forEach items="${showBooks}" var="displaybook">
            		<tr>
                		<td><c:out value="${displaybook.book.bookname}"/></td>
                		<td><c:out value="${displaybook.book.author}"/></td>
                		<td><c:out value="${displaybook.book.subject}"/></td>
                		<td><a href="/books/${displaybook.book.code}"><c:out value="${displaybook.returndate}"/></a></td>
                		
            		</tr>
            		</c:forEach>
        		</tbody>
    		</table>
		</div>
		<div class="row">
			<a class="btn-primary btn" href="/books/new">Add a new Book to the Library</a>
			<a class="btn-secondary btn" href="/books/all">Browse and Search all Books</a>
		</div>
		
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	
</body>
</html>