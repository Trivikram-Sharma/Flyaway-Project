<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.flyaway.util.HibernateUtil" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.List" %>
    <%@ page import = "com.flyaway.entity.*" %>
    <%@ page import="com.flyaway.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Airline</title>
<style>
body{text-align: center}
</style>
</head>
<body>
	<%
		int airlineId = Integer.parseInt(request.getParameter("aid"));
		Airline a = AirlineDao.getAirline(airlineId);
	%>
	<fieldset>
		<legend align="center">Modify/Change Airline</legend>
		<form action="ChangeAirline" method="post">
			Airline Id: <input type="text" name="airlineId" value="<%=airlineId %>" readonly><br>
			Airline Name: <input type="text" name="airlineName" id="airlineName" placeholder="<%= a.getAirlineName()%>"><br>
			Scope: <input type="text" name="scope" id="scope" placeholder="<%=a.getScope()%>"><br>
			<input type="submit" name="Submit" value="Modify Airline">
		</form>
	</fieldset>
</body>
</html>