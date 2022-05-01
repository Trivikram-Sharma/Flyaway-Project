<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.flyaway.util.HibernateUtil" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.List" %>
    <%@ page import = "com.flyaway.entity.Place" %>
    <%@ page import = "javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADMIN HOME PAGE</title>
<style> 
h3{background-color : green;color:white}
header,nav{background-color: green; color:white}
a{color:blue; background-color:silver}
body{background-color : royal-blue;text-align: center}
</style>
</head>
<body>
	<nav>
		<header>
			<b>WELCOME TO FLYAWAY.COM</b>
		</header>
		<a href="change-password.jsp?userName=<%=request.getSession(false).getAttribute("userName")%>">CHANGE PASSWORD</a> ||
		<big><%=request.getSession().getAttribute("userName")%></big> ||
		<a href="LogoutServlet">Logout</a>
	</nav>
	<h3>Admin Home Page</h3>
	<a href="places.jsp">Click Here for master list of places for
		source and destination</a>
	<a href="airlines.jsp">Click Here for master list of Airlines</a>
	<a href="flights.jsp">Click Here for list of Flights</a>
</body>
</html>