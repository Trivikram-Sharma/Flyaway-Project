
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.flyaway.util.HibernateUtil" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.List" %>
    <%@ page import = "com.flyaway.entity.Place" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Reset</title>
</head>
<body>
	<fieldset>
		<legend align="center">Password Reset</legend>
		<form action="PasswordReset?userName=<%=session.getAttribute("userName")%>" method="post">
			New Password: <input type="password" name="npwd1" id="npwd1"><br />
			Re - Enter New Password: <input type="password" name="npwd2" id="npwd2"><br />
			<input type="Submit" name="submit" value="Change Password">
		</form>
	</fieldset>
</body>
</html>