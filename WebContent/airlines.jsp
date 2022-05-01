<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.flyaway.entity.*" %>
    <%@ page import="com.flyaway.servlet.*" %>
    <%@ page import="java.util.*" %>
    <%@ page import="com.flyaway.dao.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airlines</title>
<style>
table,th,td {
	border: 2px solid green;
	padding: 2px;
	text-align: center;
	cell-spacing: 2 px;
}

td {
	border: 2 px;
}

body {
	background-color: royal-blue;
	text-align: center
}
</style>
</head>
<body>
<a href="add-airline.html">Add Airline</a>
	<a href="LogoutServlet">Logout</a>
	<a href="admin-homepage.jsp">Admin Home Page</a>
	<br />
	<h5>Please make sure to delete the flights of the airline, before deleting the airline.</h5>
	<%
		List<Airline> airlines = AirlineDao.listAirlines();
	request.setAttribute("airlines",airlines);
	StringBuilder sb = new StringBuilder();
	%>
	<table align="center" style="border : 4px solid green">
		<thead>
			<tr>
				<th>AirlineID</th>
				<th>Airline Name</th>
				<th>Scope</th>
				<th>Flights</th>
				 <th>Modify/Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${airlines}" var="airline">
				<tr>
					<td>${airline.getAirlineId()}</td>
					<td>${airline.getAirlineName()}</td>
					<td>${airline.getScope()}</td>
					<td>${airline.getFlightList()}</td>
					<td colspan="2"><a href="changeairline.jsp?aid=${airline.getAirlineId() }">Modify</a>/<a href="DeleteAirline?aid=${airline.getAirlineId() }">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>