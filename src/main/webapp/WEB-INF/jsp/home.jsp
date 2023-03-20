<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        	<ul>
        	<li>
            <a href="stu-list.htm">Student List</a>
            </li>
            <li>
            <a href="cou-list.htm">Course List</a>
            </li>
            <li>
            <a href="enroll.htm">Enrollment List</a>
            </li>
            <li>
            <a href="enroll-course.htm">Enroll</a>
            </li>
            <li>
            <a href="lgt.htm">Logout</a>
            </li>
            </ul>
            <br>           
			<b>Welcome ${sessionScope.stdlogin.email_id}</b>
    </body>
</html>
