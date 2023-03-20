<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Create Course</title>
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
            <a href="enroll.htm"> My Enrollment List</a>
            </li>
            </ul>

<h2>Create Course</h2>

<form:form modelAttribute="course" method="post">

<table>

<tr>
    <td>Course Name:</td>
    <td><form:input path="course_name" size="40" /></td>
</tr>

<tr>
    <td>Description:</td>
    <td><form:input path="description" size="40" /></td>
</tr>

<tr>
    <td colspan="2"><input type="submit" value="Add Course" /></td>
</tr>
</table>

</form:form><br>

<a href="homepage.htm">home page</a>

</body>
</html>