<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student List</title>
    </head>
    <body">
    <h2>Student List</h2><br>
        <table border="2">
            <tr>
                <td><b>Student ID</b></td>
                <td><b>First Name</b></td>
                <td><b>Last Name</b></td>
                <td><b>Email ID</b></td>
                <td><b>Admin</b></td>
            </tr>
            <c:forEach var="s" items="${AllStudents}">
                <tr>
                	<td>${s.student_id}</td>
                    <td>${s.first_name}</td>
                    <td>${s.last_name}</td> 
                    <td>${s.email_id}</td>
                    <td>${s.admin}</td>                              
                </tr>
            </c:forEach>
        </table><br>
        <a href="homepage.htm">home page</a>
    </body>
</html>