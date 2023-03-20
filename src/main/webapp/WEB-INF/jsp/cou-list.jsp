<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course List</title>
    </head>
    <body">
    <h2>Course List</h2><br>
        <table border="2">
            <tr>
                <td><b>Course ID</b></td>
                <td><b>Course Name</b></td>
                <td><b>Description</b></td>
            </tr>
            <c:forEach var="cou" items="${courses}">
                <tr>
                	<td>${cou.course_id}</td>
                    <td>${cou.course_name}</td>
                    <td>${cou.description}</td>                              
                </tr>
            </c:forEach>
        </table><br>
        <a href="homepage.htm">home page</a>
    </body>
</html>
