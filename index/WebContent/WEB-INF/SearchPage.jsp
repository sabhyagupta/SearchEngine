<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Search" method="post">

<input type="text" name="searchword"/>
<input type="submit" value="search">
</form>


Result:
<c:forEach var="result" items="${result}">
   <%--  Rank: ${result.key}   --%>
    <a href=" ${result.value}"> ${result.value}</a>
     <br>
</c:forEach>
</body>
</html>