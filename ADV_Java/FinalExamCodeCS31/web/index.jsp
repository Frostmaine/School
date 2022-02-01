<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>CS 3 final Exam</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sqlStatement == null}">
    <c:set var="sqlStatement" value="select * from User" />
</c:if>

<h1>The SQL Gateway</h1>
<h2><%
    String first_name = (String) session.getAttribute("firstName");
    String last_name = (String) session.getAttribute("lastName");
    String birthday = (String) session.getAttribute("birthday");
    out.print(first_name + " " + last_name + " " + birthday);
    %></h2>
<p>Enter an SQL statement and click the Execute button.</p>

<p><b>SQL statement:</b></p>
<form action="sqlGateway" method="post">
    <textarea name="sqlStatement" cols="60" rows="8">${sqlStatement}</textarea>
    <input type="submit" value="Execute">
</form>

<p><b>SQL result:</b></p>
${sqlResult}

</body>
</html>