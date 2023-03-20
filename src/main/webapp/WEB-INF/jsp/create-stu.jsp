<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Create Student</title>
</head>
<body">
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
            </ul>

<h2>Add a Student</h2>

<form:form modelAttribute="student" method="post">

<table>
<tr>
    <td>Record Type:</td>
    <td><form:radiobutton path="admin" value="true"/>Admin <form:radiobutton path="admin" value="false"/>Regular</td>
</tr>

<tr>
    <td>Student F Name:</td>
    <td><form:input path="first_name" size="40" /></td>
</tr>

<tr>
    <td>Student L Name:</td>
    <td><form:input path="last_name" size="40" /></td>
</tr>

<tr>
    <td>Student Email ID:</td>
    <td><form:input path="email_id" size="40" /></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="pass_word" size="40" /></td>
</tr>

<tr>
    <td colspan="2"><input type="submit" value="Create Student" /></td>
</tr>
</table>

</form:form><br>

<a href="homepage.htm">home page</a>

</body>
</html>