<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body style="margin-right: 30px; margin-left: 30px; margin-top: 20px;">

	<div class=jumbotron style="background-color: yellow; border: 2px;">
		<h1>
			<center>CS454 SearchEngine Class</center>
		</h1>
		<font face=cambria size=4 color=Blue>
			<p>
			<center>California State University , Los Angeles</center>
			</p>
		</font>
	</div>

	<form action="Search" method="post">

		<input type="text" name="searchword" class="form-control" /> <br>
		<center>
			<input type="submit" value="search" class="btn btn-success">
		</center>
	</form>
	<br>
	<table class="table table-striped">
		<tr>
			<th>Links:</th>
			<th>Results:</th>
		</tr>
		<c:forEach var="result" items="${result}">
			<tr>
				<td>links :</td>
				<td><a href=" ${result.value}"> ${result.value}</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>