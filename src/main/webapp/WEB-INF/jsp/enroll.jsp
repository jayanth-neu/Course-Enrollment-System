<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enrollment List</title>
    </head>
    <body">
    <h2>Enrollment List</h2>
        <table border="2">
            <tr>
                <td><b>Enroll ID</b></td>
                <td><b>Student Email ID</b></td>
                <td><b>Course Name</b></td>
            </tr>
            <c:forEach var="e" items="${Enrollment}">
                <tr>
                	<td>${e.enroll_id}</td>
                    <td>${e.student.email_id}</td>
                    <td>${e.course.course_name}</td>                           
                </tr>
            </c:forEach>
        </table><br>
        <a href="homepage.htm">home page</a>
    </body>
</html>